package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.repository.HackathonRepository;

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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link HackathonResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class HackathonResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_MARK = 1L;
    private static final Long UPDATED_MARK = 2L;

    @Autowired
    private HackathonRepository hackathonRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHackathonMockMvc;

    private Hackathon hackathon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hackathon createEntity(EntityManager em) {
        Hackathon hackathon = new Hackathon()
            .date(DEFAULT_DATE)
            .mark(DEFAULT_MARK);
        return hackathon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hackathon createUpdatedEntity(EntityManager em) {
        Hackathon hackathon = new Hackathon()
            .date(UPDATED_DATE)
            .mark(UPDATED_MARK);
        return hackathon;
    }

    @BeforeEach
    public void initTest() {
        hackathon = createEntity(em);
    }

    @Test
    @Transactional
    public void createHackathon() throws Exception {
        int databaseSizeBeforeCreate = hackathonRepository.findAll().size();

        // Create the Hackathon
        restHackathonMockMvc.perform(post("/api/hackathons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hackathon)))
            .andExpect(status().isCreated());

        // Validate the Hackathon in the database
        List<Hackathon> hackathonList = hackathonRepository.findAll();
        assertThat(hackathonList).hasSize(databaseSizeBeforeCreate + 1);
        Hackathon testHackathon = hackathonList.get(hackathonList.size() - 1);
        assertThat(testHackathon.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testHackathon.getMark()).isEqualTo(DEFAULT_MARK);
    }

    @Test
    @Transactional
    public void createHackathonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hackathonRepository.findAll().size();

        // Create the Hackathon with an existing ID
        hackathon.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHackathonMockMvc.perform(post("/api/hackathons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hackathon)))
            .andExpect(status().isBadRequest());

        // Validate the Hackathon in the database
        List<Hackathon> hackathonList = hackathonRepository.findAll();
        assertThat(hackathonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHackathons() throws Exception {
        // Initialize the database
        hackathonRepository.saveAndFlush(hackathon);

        // Get all the hackathonList
        restHackathonMockMvc.perform(get("/api/hackathons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hackathon.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].mark").value(hasItem(DEFAULT_MARK.intValue())));
    }
    
    @Test
    @Transactional
    public void getHackathon() throws Exception {
        // Initialize the database
        hackathonRepository.saveAndFlush(hackathon);

        // Get the hackathon
        restHackathonMockMvc.perform(get("/api/hackathons/{id}", hackathon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hackathon.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.mark").value(DEFAULT_MARK.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHackathon() throws Exception {
        // Get the hackathon
        restHackathonMockMvc.perform(get("/api/hackathons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHackathon() throws Exception {
        // Initialize the database
        hackathonRepository.saveAndFlush(hackathon);

        int databaseSizeBeforeUpdate = hackathonRepository.findAll().size();

        // Update the hackathon
        Hackathon updatedHackathon = hackathonRepository.findById(hackathon.getId()).get();
        // Disconnect from session so that the updates on updatedHackathon are not directly saved in db
        em.detach(updatedHackathon);
        updatedHackathon
            .date(UPDATED_DATE)
            .mark(UPDATED_MARK);

        restHackathonMockMvc.perform(put("/api/hackathons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedHackathon)))
            .andExpect(status().isOk());

        // Validate the Hackathon in the database
        List<Hackathon> hackathonList = hackathonRepository.findAll();
        assertThat(hackathonList).hasSize(databaseSizeBeforeUpdate);
        Hackathon testHackathon = hackathonList.get(hackathonList.size() - 1);
        assertThat(testHackathon.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testHackathon.getMark()).isEqualTo(UPDATED_MARK);
    }

    @Test
    @Transactional
    public void updateNonExistingHackathon() throws Exception {
        int databaseSizeBeforeUpdate = hackathonRepository.findAll().size();

        // Create the Hackathon

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHackathonMockMvc.perform(put("/api/hackathons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hackathon)))
            .andExpect(status().isBadRequest());

        // Validate the Hackathon in the database
        List<Hackathon> hackathonList = hackathonRepository.findAll();
        assertThat(hackathonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHackathon() throws Exception {
        // Initialize the database
        hackathonRepository.saveAndFlush(hackathon);

        int databaseSizeBeforeDelete = hackathonRepository.findAll().size();

        // Delete the hackathon
        restHackathonMockMvc.perform(delete("/api/hackathons/{id}", hackathon.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Hackathon> hackathonList = hackathonRepository.findAll();
        assertThat(hackathonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
