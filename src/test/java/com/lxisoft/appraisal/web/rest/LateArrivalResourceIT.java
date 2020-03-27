package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.repository.LateArrivalRepository;

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
 * Integration tests for the {@link LateArrivalResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LateArrivalResourceIT {

    private static final Instant DEFAULT_REACHED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REACHED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private LateArrivalRepository lateArrivalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLateArrivalMockMvc;

    private LateArrival lateArrival;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LateArrival createEntity(EntityManager em) {
        LateArrival lateArrival = new LateArrival()
            .reachedTime(DEFAULT_REACHED_TIME)
            .type(DEFAULT_TYPE);
        return lateArrival;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LateArrival createUpdatedEntity(EntityManager em) {
        LateArrival lateArrival = new LateArrival()
            .reachedTime(UPDATED_REACHED_TIME)
            .type(UPDATED_TYPE);
        return lateArrival;
    }

    @BeforeEach
    public void initTest() {
        lateArrival = createEntity(em);
    }

    @Test
    @Transactional
    public void createLateArrival() throws Exception {
        int databaseSizeBeforeCreate = lateArrivalRepository.findAll().size();

        // Create the LateArrival
        restLateArrivalMockMvc.perform(post("/api/late-arrivals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lateArrival)))
            .andExpect(status().isCreated());

        // Validate the LateArrival in the database
        List<LateArrival> lateArrivalList = lateArrivalRepository.findAll();
        assertThat(lateArrivalList).hasSize(databaseSizeBeforeCreate + 1);
        LateArrival testLateArrival = lateArrivalList.get(lateArrivalList.size() - 1);
        assertThat(testLateArrival.getReachedTime()).isEqualTo(DEFAULT_REACHED_TIME);
        assertThat(testLateArrival.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createLateArrivalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lateArrivalRepository.findAll().size();

        // Create the LateArrival with an existing ID
        lateArrival.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLateArrivalMockMvc.perform(post("/api/late-arrivals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lateArrival)))
            .andExpect(status().isBadRequest());

        // Validate the LateArrival in the database
        List<LateArrival> lateArrivalList = lateArrivalRepository.findAll();
        assertThat(lateArrivalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLateArrivals() throws Exception {
        // Initialize the database
        lateArrivalRepository.saveAndFlush(lateArrival);

        // Get all the lateArrivalList
        restLateArrivalMockMvc.perform(get("/api/late-arrivals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lateArrival.getId().intValue())))
            .andExpect(jsonPath("$.[*].reachedTime").value(hasItem(DEFAULT_REACHED_TIME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getLateArrival() throws Exception {
        // Initialize the database
        lateArrivalRepository.saveAndFlush(lateArrival);

        // Get the lateArrival
        restLateArrivalMockMvc.perform(get("/api/late-arrivals/{id}", lateArrival.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lateArrival.getId().intValue()))
            .andExpect(jsonPath("$.reachedTime").value(DEFAULT_REACHED_TIME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingLateArrival() throws Exception {
        // Get the lateArrival
        restLateArrivalMockMvc.perform(get("/api/late-arrivals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLateArrival() throws Exception {
        // Initialize the database
        lateArrivalRepository.saveAndFlush(lateArrival);

        int databaseSizeBeforeUpdate = lateArrivalRepository.findAll().size();

        // Update the lateArrival
        LateArrival updatedLateArrival = lateArrivalRepository.findById(lateArrival.getId()).get();
        // Disconnect from session so that the updates on updatedLateArrival are not directly saved in db
        em.detach(updatedLateArrival);
        updatedLateArrival
            .reachedTime(UPDATED_REACHED_TIME)
            .type(UPDATED_TYPE);

        restLateArrivalMockMvc.perform(put("/api/late-arrivals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLateArrival)))
            .andExpect(status().isOk());

        // Validate the LateArrival in the database
        List<LateArrival> lateArrivalList = lateArrivalRepository.findAll();
        assertThat(lateArrivalList).hasSize(databaseSizeBeforeUpdate);
        LateArrival testLateArrival = lateArrivalList.get(lateArrivalList.size() - 1);
        assertThat(testLateArrival.getReachedTime()).isEqualTo(UPDATED_REACHED_TIME);
        assertThat(testLateArrival.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingLateArrival() throws Exception {
        int databaseSizeBeforeUpdate = lateArrivalRepository.findAll().size();

        // Create the LateArrival

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLateArrivalMockMvc.perform(put("/api/late-arrivals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lateArrival)))
            .andExpect(status().isBadRequest());

        // Validate the LateArrival in the database
        List<LateArrival> lateArrivalList = lateArrivalRepository.findAll();
        assertThat(lateArrivalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLateArrival() throws Exception {
        // Initialize the database
        lateArrivalRepository.saveAndFlush(lateArrival);

        int databaseSizeBeforeDelete = lateArrivalRepository.findAll().size();

        // Delete the lateArrival
        restLateArrivalMockMvc.perform(delete("/api/late-arrivals/{id}", lateArrival.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LateArrival> lateArrivalList = lateArrivalRepository.findAll();
        assertThat(lateArrivalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
