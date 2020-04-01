package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.repository.ReportStatusRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ReportStatusResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ReportStatusResourceIT {

    private static final Instant DEFAULT_REPORTING_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REPORTING_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private ReportStatusRepository reportStatusRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportStatusMockMvc;

    private ReportStatus reportStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportStatus createEntity(EntityManager em) {
        ReportStatus reportStatus = new ReportStatus()
            .reportingTime(DEFAULT_REPORTING_TIME)
            .type(DEFAULT_TYPE);
        return reportStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportStatus createUpdatedEntity(EntityManager em) {
        ReportStatus reportStatus = new ReportStatus()
            .reportingTime(UPDATED_REPORTING_TIME)
            .type(UPDATED_TYPE);
        return reportStatus;
    }

    @BeforeEach
    public void initTest() {
        reportStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createReportStatus() throws Exception {
        int databaseSizeBeforeCreate = reportStatusRepository.findAll().size();

        // Create the ReportStatus
        restReportStatusMockMvc.perform(post("/api/report-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportStatus)))
            .andExpect(status().isCreated());

        // Validate the ReportStatus in the database
        List<ReportStatus> reportStatusList = reportStatusRepository.findAll();
        assertThat(reportStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ReportStatus testReportStatus = reportStatusList.get(reportStatusList.size() - 1);
        assertThat(testReportStatus.getReportingTime()).isEqualTo(DEFAULT_REPORTING_TIME);
        assertThat(testReportStatus.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createReportStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportStatusRepository.findAll().size();

        // Create the ReportStatus with an existing ID
        reportStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportStatusMockMvc.perform(post("/api/report-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ReportStatus in the database
        List<ReportStatus> reportStatusList = reportStatusRepository.findAll();
        assertThat(reportStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReportStatuses() throws Exception {
        // Initialize the database
        reportStatusRepository.saveAndFlush(reportStatus);

        // Get all the reportStatusList
        restReportStatusMockMvc.perform(get("/api/report-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].reportingTime").value(hasItem(DEFAULT_REPORTING_TIME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getReportStatus() throws Exception {
        // Initialize the database
        reportStatusRepository.saveAndFlush(reportStatus);

        // Get the reportStatus
        restReportStatusMockMvc.perform(get("/api/report-statuses/{id}", reportStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportStatus.getId().intValue()))
            .andExpect(jsonPath("$.reportingTime").value(DEFAULT_REPORTING_TIME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingReportStatus() throws Exception {
        // Get the reportStatus
        restReportStatusMockMvc.perform(get("/api/report-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReportStatus() throws Exception {
        // Initialize the database
        reportStatusRepository.saveAndFlush(reportStatus);

        int databaseSizeBeforeUpdate = reportStatusRepository.findAll().size();

        // Update the reportStatus
        ReportStatus updatedReportStatus = reportStatusRepository.findById(reportStatus.getId()).get();
        // Disconnect from session so that the updates on updatedReportStatus are not directly saved in db
        em.detach(updatedReportStatus);
        updatedReportStatus
            .reportingTime(UPDATED_REPORTING_TIME)
            .type(UPDATED_TYPE);

        restReportStatusMockMvc.perform(put("/api/report-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedReportStatus)))
            .andExpect(status().isOk());

        // Validate the ReportStatus in the database
        List<ReportStatus> reportStatusList = reportStatusRepository.findAll();
        assertThat(reportStatusList).hasSize(databaseSizeBeforeUpdate);
        ReportStatus testReportStatus = reportStatusList.get(reportStatusList.size() - 1);
        assertThat(testReportStatus.getReportingTime()).isEqualTo(UPDATED_REPORTING_TIME);
        assertThat(testReportStatus.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingReportStatus() throws Exception {
        int databaseSizeBeforeUpdate = reportStatusRepository.findAll().size();

        // Create the ReportStatus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportStatusMockMvc.perform(put("/api/report-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ReportStatus in the database
        List<ReportStatus> reportStatusList = reportStatusRepository.findAll();
        assertThat(reportStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReportStatus() throws Exception {
        // Initialize the database
        reportStatusRepository.saveAndFlush(reportStatus);

        int databaseSizeBeforeDelete = reportStatusRepository.findAll().size();

        // Delete the reportStatus
        restReportStatusMockMvc.perform(delete("/api/report-statuses/{id}", reportStatus.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportStatus> reportStatusList = reportStatusRepository.findAll();
        assertThat(reportStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
