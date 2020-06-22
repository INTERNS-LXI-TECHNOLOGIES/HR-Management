package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.Jira;
import com.lxisoft.appraisal.repository.JiraRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link JiraResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class JiraResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_HOUR = 1F;
    private static final Float UPDATED_HOUR = 2F;

    @Autowired
    private JiraRepository jiraRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJiraMockMvc;

    private Jira jira;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Jira createEntity(EntityManager em) {
        Jira jira = new Jira()
            .date(DEFAULT_DATE)
            .hour(DEFAULT_HOUR);
        return jira;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Jira createUpdatedEntity(EntityManager em) {
        Jira jira = new Jira()
            .date(UPDATED_DATE)
            .hour(UPDATED_HOUR);
        return jira;
    }

    @BeforeEach
    public void initTest() {
        jira = createEntity(em);
    }

    @Test
    @Transactional
    public void createJira() throws Exception {
        int databaseSizeBeforeCreate = jiraRepository.findAll().size();
        // Create the Jira
        restJiraMockMvc.perform(post("/api/jiras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jira)))
            .andExpect(status().isCreated());

        // Validate the Jira in the database
        List<Jira> jiraList = jiraRepository.findAll();
        assertThat(jiraList).hasSize(databaseSizeBeforeCreate + 1);
        Jira testJira = jiraList.get(jiraList.size() - 1);
        assertThat(testJira.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testJira.getHour()).isEqualTo(DEFAULT_HOUR);
    }

    @Test
    @Transactional
    public void createJiraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jiraRepository.findAll().size();

        // Create the Jira with an existing ID
        jira.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJiraMockMvc.perform(post("/api/jiras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jira)))
            .andExpect(status().isBadRequest());

        // Validate the Jira in the database
        List<Jira> jiraList = jiraRepository.findAll();
        assertThat(jiraList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllJiras() throws Exception {
        // Initialize the database
        jiraRepository.saveAndFlush(jira);

        // Get all the jiraList
        restJiraMockMvc.perform(get("/api/jiras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jira.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].hour").value(hasItem(DEFAULT_HOUR.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getJira() throws Exception {
        // Initialize the database
        jiraRepository.saveAndFlush(jira);

        // Get the jira
        restJiraMockMvc.perform(get("/api/jiras/{id}", jira.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jira.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.hour").value(DEFAULT_HOUR.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingJira() throws Exception {
        // Get the jira
        restJiraMockMvc.perform(get("/api/jiras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJira() throws Exception {
        // Initialize the database
        jiraRepository.saveAndFlush(jira);

        int databaseSizeBeforeUpdate = jiraRepository.findAll().size();

        // Update the jira
        Jira updatedJira = jiraRepository.findById(jira.getId()).get();
        // Disconnect from session so that the updates on updatedJira are not directly saved in db
        em.detach(updatedJira);
        updatedJira
            .date(UPDATED_DATE)
            .hour(UPDATED_HOUR);

        restJiraMockMvc.perform(put("/api/jiras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedJira)))
            .andExpect(status().isOk());

        // Validate the Jira in the database
        List<Jira> jiraList = jiraRepository.findAll();
        assertThat(jiraList).hasSize(databaseSizeBeforeUpdate);
        Jira testJira = jiraList.get(jiraList.size() - 1);
        assertThat(testJira.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testJira.getHour()).isEqualTo(UPDATED_HOUR);
    }

    @Test
    @Transactional
    public void updateNonExistingJira() throws Exception {
        int databaseSizeBeforeUpdate = jiraRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJiraMockMvc.perform(put("/api/jiras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jira)))
            .andExpect(status().isBadRequest());

        // Validate the Jira in the database
        List<Jira> jiraList = jiraRepository.findAll();
        assertThat(jiraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJira() throws Exception {
        // Initialize the database
        jiraRepository.saveAndFlush(jira);

        int databaseSizeBeforeDelete = jiraRepository.findAll().size();

        // Delete the jira
        restJiraMockMvc.perform(delete("/api/jiras/{id}", jira.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Jira> jiraList = jiraRepository.findAll();
        assertThat(jiraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
