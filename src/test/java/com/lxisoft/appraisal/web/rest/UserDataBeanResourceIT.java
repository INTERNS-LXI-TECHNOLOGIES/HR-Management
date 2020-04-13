package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.repository.UserDataBeanRepository;
import com.lxisoft.appraisal.service.UserDataBeanService;

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
 * Integration tests for the {@link UserDataBeanResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserDataBeanResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    @Autowired
    private UserDataBeanRepository userDataBeanRepository;

    @Autowired
    private UserDataBeanService userDataBeanService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserDataBeanMockMvc;

    private UserDataBean userDataBean;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserDataBean createEntity(EntityManager em) {
        UserDataBean userDataBean = new UserDataBean()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .company(DEFAULT_COMPANY);
        return userDataBean;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserDataBean createUpdatedEntity(EntityManager em) {
        UserDataBean userDataBean = new UserDataBean()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .company(UPDATED_COMPANY);
        return userDataBean;
    }

    @BeforeEach
    public void initTest() {
        userDataBean = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserDataBean() throws Exception {
        int databaseSizeBeforeCreate = userDataBeanRepository.findAll().size();

        // Create the UserDataBean
        restUserDataBeanMockMvc.perform(post("/api/user-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userDataBean)))
            .andExpect(status().isCreated());

        // Validate the UserDataBean in the database
        List<UserDataBean> userDataBeanList = userDataBeanRepository.findAll();
        assertThat(userDataBeanList).hasSize(databaseSizeBeforeCreate + 1);
        UserDataBean testUserDataBean = userDataBeanList.get(userDataBeanList.size() - 1);
        assertThat(testUserDataBean.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUserDataBean.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserDataBean.getCompany()).isEqualTo(DEFAULT_COMPANY);
    }

    @Test
    @Transactional
    public void createUserDataBeanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userDataBeanRepository.findAll().size();

        // Create the UserDataBean with an existing ID
        userDataBean.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserDataBeanMockMvc.perform(post("/api/user-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userDataBean)))
            .andExpect(status().isBadRequest());

        // Validate the UserDataBean in the database
        List<UserDataBean> userDataBeanList = userDataBeanRepository.findAll();
        assertThat(userDataBeanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserDataBeans() throws Exception {
        // Initialize the database
        userDataBeanRepository.saveAndFlush(userDataBean);

        // Get all the userDataBeanList
        restUserDataBeanMockMvc.perform(get("/api/user-data-beans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userDataBean.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)));
    }
    
    @Test
    @Transactional
    public void getUserDataBean() throws Exception {
        // Initialize the database
        userDataBeanRepository.saveAndFlush(userDataBean);

        // Get the userDataBean
        restUserDataBeanMockMvc.perform(get("/api/user-data-beans/{id}", userDataBean.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userDataBean.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY));
    }

    @Test
    @Transactional
    public void getNonExistingUserDataBean() throws Exception {
        // Get the userDataBean
        restUserDataBeanMockMvc.perform(get("/api/user-data-beans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserDataBean() throws Exception {
        // Initialize the database
        userDataBeanService.save(userDataBean);

        int databaseSizeBeforeUpdate = userDataBeanRepository.findAll().size();

        // Update the userDataBean
        UserDataBean updatedUserDataBean = userDataBeanRepository.findById(userDataBean.getId()).get();
        // Disconnect from session so that the updates on updatedUserDataBean are not directly saved in db
        em.detach(updatedUserDataBean);
        updatedUserDataBean
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .company(UPDATED_COMPANY);

        restUserDataBeanMockMvc.perform(put("/api/user-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserDataBean)))
            .andExpect(status().isOk());

        // Validate the UserDataBean in the database
        List<UserDataBean> userDataBeanList = userDataBeanRepository.findAll();
        assertThat(userDataBeanList).hasSize(databaseSizeBeforeUpdate);
        UserDataBean testUserDataBean = userDataBeanList.get(userDataBeanList.size() - 1);
        assertThat(testUserDataBean.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserDataBean.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserDataBean.getCompany()).isEqualTo(UPDATED_COMPANY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserDataBean() throws Exception {
        int databaseSizeBeforeUpdate = userDataBeanRepository.findAll().size();

        // Create the UserDataBean

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserDataBeanMockMvc.perform(put("/api/user-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userDataBean)))
            .andExpect(status().isBadRequest());

        // Validate the UserDataBean in the database
        List<UserDataBean> userDataBeanList = userDataBeanRepository.findAll();
        assertThat(userDataBeanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserDataBean() throws Exception {
        // Initialize the database
        userDataBeanService.save(userDataBean);

        int databaseSizeBeforeDelete = userDataBeanRepository.findAll().size();

        // Delete the userDataBean
        restUserDataBeanMockMvc.perform(delete("/api/user-data-beans/{id}", userDataBean.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserDataBean> userDataBeanList = userDataBeanRepository.findAll();
        assertThat(userDataBeanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
