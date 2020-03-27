package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.repository.GitRepository;

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
 * Integration tests for the {@link GitResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class GitResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_MARK = 1L;
    private static final Long UPDATED_MARK = 2L;

    @Autowired
    private GitRepository gitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGitMockMvc;

    private Git git;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Git createEntity(EntityManager em) {
        Git git = new Git()
            .date(DEFAULT_DATE)
            .mark(DEFAULT_MARK);
        return git;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Git createUpdatedEntity(EntityManager em) {
        Git git = new Git()
            .date(UPDATED_DATE)
            .mark(UPDATED_MARK);
        return git;
    }

    @BeforeEach
    public void initTest() {
        git = createEntity(em);
    }

    @Test
    @Transactional
    public void createGit() throws Exception {
        int databaseSizeBeforeCreate = gitRepository.findAll().size();

        // Create the Git
        restGitMockMvc.perform(post("/api/gits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(git)))
            .andExpect(status().isCreated());

        // Validate the Git in the database
        List<Git> gitList = gitRepository.findAll();
        assertThat(gitList).hasSize(databaseSizeBeforeCreate + 1);
        Git testGit = gitList.get(gitList.size() - 1);
        assertThat(testGit.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testGit.getMark()).isEqualTo(DEFAULT_MARK);
    }

    @Test
    @Transactional
    public void createGitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gitRepository.findAll().size();

        // Create the Git with an existing ID
        git.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGitMockMvc.perform(post("/api/gits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(git)))
            .andExpect(status().isBadRequest());

        // Validate the Git in the database
        List<Git> gitList = gitRepository.findAll();
        assertThat(gitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGits() throws Exception {
        // Initialize the database
        gitRepository.saveAndFlush(git);

        // Get all the gitList
        restGitMockMvc.perform(get("/api/gits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(git.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].mark").value(hasItem(DEFAULT_MARK.intValue())));
    }
    
    @Test
    @Transactional
    public void getGit() throws Exception {
        // Initialize the database
        gitRepository.saveAndFlush(git);

        // Get the git
        restGitMockMvc.perform(get("/api/gits/{id}", git.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(git.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.mark").value(DEFAULT_MARK.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGit() throws Exception {
        // Get the git
        restGitMockMvc.perform(get("/api/gits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGit() throws Exception {
        // Initialize the database
        gitRepository.saveAndFlush(git);

        int databaseSizeBeforeUpdate = gitRepository.findAll().size();

        // Update the git
        Git updatedGit = gitRepository.findById(git.getId()).get();
        // Disconnect from session so that the updates on updatedGit are not directly saved in db
        em.detach(updatedGit);
        updatedGit
            .date(UPDATED_DATE)
            .mark(UPDATED_MARK);

        restGitMockMvc.perform(put("/api/gits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGit)))
            .andExpect(status().isOk());

        // Validate the Git in the database
        List<Git> gitList = gitRepository.findAll();
        assertThat(gitList).hasSize(databaseSizeBeforeUpdate);
        Git testGit = gitList.get(gitList.size() - 1);
        assertThat(testGit.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testGit.getMark()).isEqualTo(UPDATED_MARK);
    }

    @Test
    @Transactional
    public void updateNonExistingGit() throws Exception {
        int databaseSizeBeforeUpdate = gitRepository.findAll().size();

        // Create the Git

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGitMockMvc.perform(put("/api/gits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(git)))
            .andExpect(status().isBadRequest());

        // Validate the Git in the database
        List<Git> gitList = gitRepository.findAll();
        assertThat(gitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGit() throws Exception {
        // Initialize the database
        gitRepository.saveAndFlush(git);

        int databaseSizeBeforeDelete = gitRepository.findAll().size();

        // Delete the git
        restGitMockMvc.perform(delete("/api/gits/{id}", git.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Git> gitList = gitRepository.findAll();
        assertThat(gitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
