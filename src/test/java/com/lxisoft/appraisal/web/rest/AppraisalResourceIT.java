package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.repository.AppraisalRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AppraisalResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AppraisalResourceIT {

    private static final Long DEFAULT_ATTENDANCE = 1L;
    private static final Long UPDATED_ATTENDANCE = 2L;

    private static final Long DEFAULT_PUNCTUALITY = 1L;
    private static final Long UPDATED_PUNCTUALITY = 2L;

    private static final Long DEFAULT_MEETING_TARGETS = 1L;
    private static final Long UPDATED_MEETING_TARGETS = 2L;

    private static final Long DEFAULT_COMPANY_POLICY = 1L;
    private static final Long UPDATED_COMPANY_POLICY = 2L;

    private static final Long DEFAULT_CODE_QUALITY = 1L;
    private static final Long UPDATED_CODE_QUALITY = 2L;

    @Autowired
    private AppraisalRepository appraisalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppraisalMockMvc;

    private Appraisal appraisal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Appraisal createEntity(EntityManager em) {
        Appraisal appraisal = new Appraisal()
            .attendance(DEFAULT_ATTENDANCE)
            .punctuality(DEFAULT_PUNCTUALITY)
            .meetingTargets(DEFAULT_MEETING_TARGETS)
            .companyPolicy(DEFAULT_COMPANY_POLICY)
            .codeQuality(DEFAULT_CODE_QUALITY);
        return appraisal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Appraisal createUpdatedEntity(EntityManager em) {
        Appraisal appraisal = new Appraisal()
            .attendance(UPDATED_ATTENDANCE)
            .punctuality(UPDATED_PUNCTUALITY)
            .meetingTargets(UPDATED_MEETING_TARGETS)
            .companyPolicy(UPDATED_COMPANY_POLICY)
            .codeQuality(UPDATED_CODE_QUALITY);
        return appraisal;
    }

    @BeforeEach
    public void initTest() {
        appraisal = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppraisal() throws Exception {
        int databaseSizeBeforeCreate = appraisalRepository.findAll().size();

        // Create the Appraisal
        restAppraisalMockMvc.perform(post("/api/appraisals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appraisal)))
            .andExpect(status().isCreated());

        // Validate the Appraisal in the database
        List<Appraisal> appraisalList = appraisalRepository.findAll();
        assertThat(appraisalList).hasSize(databaseSizeBeforeCreate + 1);
        Appraisal testAppraisal = appraisalList.get(appraisalList.size() - 1);
        assertThat(testAppraisal.getAttendance()).isEqualTo(DEFAULT_ATTENDANCE);
        assertThat(testAppraisal.getPunctuality()).isEqualTo(DEFAULT_PUNCTUALITY);
        assertThat(testAppraisal.getMeetingTargets()).isEqualTo(DEFAULT_MEETING_TARGETS);
        assertThat(testAppraisal.getCompanyPolicy()).isEqualTo(DEFAULT_COMPANY_POLICY);
        assertThat(testAppraisal.getCodeQuality()).isEqualTo(DEFAULT_CODE_QUALITY);
    }

    @Test
    @Transactional
    public void createAppraisalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appraisalRepository.findAll().size();

        // Create the Appraisal with an existing ID
        appraisal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppraisalMockMvc.perform(post("/api/appraisals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appraisal)))
            .andExpect(status().isBadRequest());

        // Validate the Appraisal in the database
        List<Appraisal> appraisalList = appraisalRepository.findAll();
        assertThat(appraisalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAppraisals() throws Exception {
        // Initialize the database
        appraisalRepository.saveAndFlush(appraisal);

        // Get all the appraisalList
        restAppraisalMockMvc.perform(get("/api/appraisals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appraisal.getId().intValue())))
            .andExpect(jsonPath("$.[*].attendance").value(hasItem(DEFAULT_ATTENDANCE.intValue())))
            .andExpect(jsonPath("$.[*].punctuality").value(hasItem(DEFAULT_PUNCTUALITY.intValue())))
            .andExpect(jsonPath("$.[*].meetingTargets").value(hasItem(DEFAULT_MEETING_TARGETS.intValue())))
            .andExpect(jsonPath("$.[*].companyPolicy").value(hasItem(DEFAULT_COMPANY_POLICY.intValue())))
            .andExpect(jsonPath("$.[*].codeQuality").value(hasItem(DEFAULT_CODE_QUALITY.intValue())));
    }
    
    @Test
    @Transactional
    public void getAppraisal() throws Exception {
        // Initialize the database
        appraisalRepository.saveAndFlush(appraisal);

        // Get the appraisal
        restAppraisalMockMvc.perform(get("/api/appraisals/{id}", appraisal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appraisal.getId().intValue()))
            .andExpect(jsonPath("$.attendance").value(DEFAULT_ATTENDANCE.intValue()))
            .andExpect(jsonPath("$.punctuality").value(DEFAULT_PUNCTUALITY.intValue()))
            .andExpect(jsonPath("$.meetingTargets").value(DEFAULT_MEETING_TARGETS.intValue()))
            .andExpect(jsonPath("$.companyPolicy").value(DEFAULT_COMPANY_POLICY.intValue()))
            .andExpect(jsonPath("$.codeQuality").value(DEFAULT_CODE_QUALITY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAppraisal() throws Exception {
        // Get the appraisal
        restAppraisalMockMvc.perform(get("/api/appraisals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppraisal() throws Exception {
        // Initialize the database
        appraisalRepository.saveAndFlush(appraisal);

        int databaseSizeBeforeUpdate = appraisalRepository.findAll().size();

        // Update the appraisal
        Appraisal updatedAppraisal = appraisalRepository.findById(appraisal.getId()).get();
        // Disconnect from session so that the updates on updatedAppraisal are not directly saved in db
        em.detach(updatedAppraisal);
        updatedAppraisal
            .attendance(UPDATED_ATTENDANCE)
            .punctuality(UPDATED_PUNCTUALITY)
            .meetingTargets(UPDATED_MEETING_TARGETS)
            .companyPolicy(UPDATED_COMPANY_POLICY)
            .codeQuality(UPDATED_CODE_QUALITY);

        restAppraisalMockMvc.perform(put("/api/appraisals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAppraisal)))
            .andExpect(status().isOk());

        // Validate the Appraisal in the database
        List<Appraisal> appraisalList = appraisalRepository.findAll();
        assertThat(appraisalList).hasSize(databaseSizeBeforeUpdate);
        Appraisal testAppraisal = appraisalList.get(appraisalList.size() - 1);
        assertThat(testAppraisal.getAttendance()).isEqualTo(UPDATED_ATTENDANCE);
        assertThat(testAppraisal.getPunctuality()).isEqualTo(UPDATED_PUNCTUALITY);
        assertThat(testAppraisal.getMeetingTargets()).isEqualTo(UPDATED_MEETING_TARGETS);
        assertThat(testAppraisal.getCompanyPolicy()).isEqualTo(UPDATED_COMPANY_POLICY);
        assertThat(testAppraisal.getCodeQuality()).isEqualTo(UPDATED_CODE_QUALITY);
    }

    @Test
    @Transactional
    public void updateNonExistingAppraisal() throws Exception {
        int databaseSizeBeforeUpdate = appraisalRepository.findAll().size();

        // Create the Appraisal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppraisalMockMvc.perform(put("/api/appraisals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appraisal)))
            .andExpect(status().isBadRequest());

        // Validate the Appraisal in the database
        List<Appraisal> appraisalList = appraisalRepository.findAll();
        assertThat(appraisalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppraisal() throws Exception {
        // Initialize the database
        appraisalRepository.saveAndFlush(appraisal);

        int databaseSizeBeforeDelete = appraisalRepository.findAll().size();

        // Delete the appraisal
        restAppraisalMockMvc.perform(delete("/api/appraisals/{id}", appraisal.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Appraisal> appraisalList = appraisalRepository.findAll();
        assertThat(appraisalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
