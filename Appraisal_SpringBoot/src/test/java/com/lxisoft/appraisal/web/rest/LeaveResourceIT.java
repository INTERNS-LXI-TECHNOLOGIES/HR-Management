package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.repository.LeaveRepository;

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
 * Integration tests for the {@link LeaveResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LeaveResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeaveMockMvc;

    private Leave leave;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Leave createEntity(EntityManager em) {
        Leave leave = new Leave()
            .date(DEFAULT_DATE)
            .type(DEFAULT_TYPE);
        return leave;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Leave createUpdatedEntity(EntityManager em) {
        Leave leave = new Leave()
            .date(UPDATED_DATE)
            .type(UPDATED_TYPE);
        return leave;
    }

    @BeforeEach
    public void initTest() {
        leave = createEntity(em);
    }

    @Test
    @Transactional
    public void createLeave() throws Exception {
        int databaseSizeBeforeCreate = leaveRepository.findAll().size();

        // Create the Leave
        restLeaveMockMvc.perform(post("/api/leaves").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leave)))
            .andExpect(status().isCreated());

        // Validate the Leave in the database
        List<Leave> leaveList = leaveRepository.findAll();
        assertThat(leaveList).hasSize(databaseSizeBeforeCreate + 1);
        Leave testLeave = leaveList.get(leaveList.size() - 1);
        assertThat(testLeave.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testLeave.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createLeaveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leaveRepository.findAll().size();

        // Create the Leave with an existing ID
        leave.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaveMockMvc.perform(post("/api/leaves").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leave)))
            .andExpect(status().isBadRequest());

        // Validate the Leave in the database
        List<Leave> leaveList = leaveRepository.findAll();
        assertThat(leaveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLeaves() throws Exception {
        // Initialize the database
        leaveRepository.saveAndFlush(leave);

        // Get all the leaveList
        restLeaveMockMvc.perform(get("/api/leaves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leave.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getLeave() throws Exception {
        // Initialize the database
        leaveRepository.saveAndFlush(leave);

        // Get the leave
        restLeaveMockMvc.perform(get("/api/leaves/{id}", leave.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leave.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingLeave() throws Exception {
        // Get the leave
        restLeaveMockMvc.perform(get("/api/leaves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLeave() throws Exception {
        // Initialize the database
        leaveRepository.saveAndFlush(leave);

        int databaseSizeBeforeUpdate = leaveRepository.findAll().size();

        // Update the leave
        Leave updatedLeave = leaveRepository.findById(leave.getId()).get();
        // Disconnect from session so that the updates on updatedLeave are not directly saved in db
        em.detach(updatedLeave);
        updatedLeave
            .date(UPDATED_DATE)
            .type(UPDATED_TYPE);

        restLeaveMockMvc.perform(put("/api/leaves").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLeave)))
            .andExpect(status().isOk());

        // Validate the Leave in the database
        List<Leave> leaveList = leaveRepository.findAll();
        assertThat(leaveList).hasSize(databaseSizeBeforeUpdate);
        Leave testLeave = leaveList.get(leaveList.size() - 1);
        assertThat(testLeave.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testLeave.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingLeave() throws Exception {
        int databaseSizeBeforeUpdate = leaveRepository.findAll().size();

        // Create the Leave

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaveMockMvc.perform(put("/api/leaves").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(leave)))
            .andExpect(status().isBadRequest());

        // Validate the Leave in the database
        List<Leave> leaveList = leaveRepository.findAll();
        assertThat(leaveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLeave() throws Exception {
        // Initialize the database
        leaveRepository.saveAndFlush(leave);

        int databaseSizeBeforeDelete = leaveRepository.findAll().size();

        // Delete the leave
        restLeaveMockMvc.perform(delete("/api/leaves/{id}", leave.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Leave> leaveList = leaveRepository.findAll();
        assertThat(leaveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
