package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import com.lxisoft.appraisal.domain.UsersDataBean;
import com.lxisoft.appraisal.repository.UsersDataBeanRepository;
import com.lxisoft.appraisal.service.UsersDataBeanService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UsersDataBeanResource} REST controller.
 */
@SpringBootTest(classes = AppraisalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UsersDataBeanResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_ATTENDENCE = 1L;
    private static final Long UPDATED_ATTENDENCE = 2L;

    private static final Long DEFAULT_PUNCTUALITY = 1L;
    private static final Long UPDATED_PUNCTUALITY = 2L;

    private static final Long DEFAULT_MEETING_TARGETS = 1L;
    private static final Long UPDATED_MEETING_TARGETS = 2L;

    private static final Long DEFAULT_COMPANY_POLICY = 1L;
    private static final Long UPDATED_COMPANY_POLICY = 2L;

    private static final Long DEFAULT_CODE_QUALITY = 1L;
    private static final Long UPDATED_CODE_QUALITY = 2L;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private UsersDataBeanRepository usersDataBeanRepository;

    @Autowired
    private UsersDataBeanService usersDataBeanService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsersDataBeanMockMvc;

    private UsersDataBean usersDataBean;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsersDataBean createEntity(EntityManager em) {
        UsersDataBean usersDataBean = new UsersDataBean()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .company(DEFAULT_COMPANY)
            .position(DEFAULT_POSITION)
            .email(DEFAULT_EMAIL)
            .attendence(DEFAULT_ATTENDENCE)
            .punctuality(DEFAULT_PUNCTUALITY)
            .meetingTargets(DEFAULT_MEETING_TARGETS)
            .companyPolicy(DEFAULT_COMPANY_POLICY)
            .codeQuality(DEFAULT_CODE_QUALITY)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return usersDataBean;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsersDataBean createUpdatedEntity(EntityManager em) {
        UsersDataBean usersDataBean = new UsersDataBean()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .company(UPDATED_COMPANY)
            .position(UPDATED_POSITION)
            .email(UPDATED_EMAIL)
            .attendence(UPDATED_ATTENDENCE)
            .punctuality(UPDATED_PUNCTUALITY)
            .meetingTargets(UPDATED_MEETING_TARGETS)
            .companyPolicy(UPDATED_COMPANY_POLICY)
            .codeQuality(UPDATED_CODE_QUALITY)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return usersDataBean;
    }

    @BeforeEach
    public void initTest() {
        usersDataBean = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsersDataBean() throws Exception {
        int databaseSizeBeforeCreate = usersDataBeanRepository.findAll().size();

        // Create the UsersDataBean
        restUsersDataBeanMockMvc.perform(post("/api/users-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usersDataBean)))
            .andExpect(status().isCreated());

        // Validate the UsersDataBean in the database
        List<UsersDataBean> usersDataBeanList = usersDataBeanRepository.findAll();
        assertThat(usersDataBeanList).hasSize(databaseSizeBeforeCreate + 1);
        UsersDataBean testUsersDataBean = usersDataBeanList.get(usersDataBeanList.size() - 1);
        assertThat(testUsersDataBean.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUsersDataBean.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUsersDataBean.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testUsersDataBean.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testUsersDataBean.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUsersDataBean.getAttendence()).isEqualTo(DEFAULT_ATTENDENCE);
        assertThat(testUsersDataBean.getPunctuality()).isEqualTo(DEFAULT_PUNCTUALITY);
        assertThat(testUsersDataBean.getMeetingTargets()).isEqualTo(DEFAULT_MEETING_TARGETS);
        assertThat(testUsersDataBean.getCompanyPolicy()).isEqualTo(DEFAULT_COMPANY_POLICY);
        assertThat(testUsersDataBean.getCodeQuality()).isEqualTo(DEFAULT_CODE_QUALITY);
        assertThat(testUsersDataBean.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testUsersDataBean.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createUsersDataBeanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usersDataBeanRepository.findAll().size();

        // Create the UsersDataBean with an existing ID
        usersDataBean.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsersDataBeanMockMvc.perform(post("/api/users-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usersDataBean)))
            .andExpect(status().isBadRequest());

        // Validate the UsersDataBean in the database
        List<UsersDataBean> usersDataBeanList = usersDataBeanRepository.findAll();
        assertThat(usersDataBeanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUsersDataBeans() throws Exception {
        // Initialize the database
        usersDataBeanRepository.saveAndFlush(usersDataBean);

        // Get all the usersDataBeanList
        restUsersDataBeanMockMvc.perform(get("/api/users-data-beans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usersDataBean.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].attendence").value(hasItem(DEFAULT_ATTENDENCE.intValue())))
            .andExpect(jsonPath("$.[*].punctuality").value(hasItem(DEFAULT_PUNCTUALITY.intValue())))
            .andExpect(jsonPath("$.[*].meetingTargets").value(hasItem(DEFAULT_MEETING_TARGETS.intValue())))
            .andExpect(jsonPath("$.[*].companyPolicy").value(hasItem(DEFAULT_COMPANY_POLICY.intValue())))
            .andExpect(jsonPath("$.[*].codeQuality").value(hasItem(DEFAULT_CODE_QUALITY.intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getUsersDataBean() throws Exception {
        // Initialize the database
        usersDataBeanRepository.saveAndFlush(usersDataBean);

        // Get the usersDataBean
        restUsersDataBeanMockMvc.perform(get("/api/users-data-beans/{id}", usersDataBean.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usersDataBean.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.attendence").value(DEFAULT_ATTENDENCE.intValue()))
            .andExpect(jsonPath("$.punctuality").value(DEFAULT_PUNCTUALITY.intValue()))
            .andExpect(jsonPath("$.meetingTargets").value(DEFAULT_MEETING_TARGETS.intValue()))
            .andExpect(jsonPath("$.companyPolicy").value(DEFAULT_COMPANY_POLICY.intValue()))
            .andExpect(jsonPath("$.codeQuality").value(DEFAULT_CODE_QUALITY.intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }

    @Test
    @Transactional
    public void getNonExistingUsersDataBean() throws Exception {
        // Get the usersDataBean
        restUsersDataBeanMockMvc.perform(get("/api/users-data-beans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsersDataBean() throws Exception {
        // Initialize the database
        usersDataBeanService.save(usersDataBean);

        int databaseSizeBeforeUpdate = usersDataBeanRepository.findAll().size();

        // Update the usersDataBean
        UsersDataBean updatedUsersDataBean = usersDataBeanRepository.findById(usersDataBean.getId()).get();
        // Disconnect from session so that the updates on updatedUsersDataBean are not directly saved in db
        em.detach(updatedUsersDataBean);
        updatedUsersDataBean
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .company(UPDATED_COMPANY)
            .position(UPDATED_POSITION)
            .email(UPDATED_EMAIL)
            .attendence(UPDATED_ATTENDENCE)
            .punctuality(UPDATED_PUNCTUALITY)
            .meetingTargets(UPDATED_MEETING_TARGETS)
            .companyPolicy(UPDATED_COMPANY_POLICY)
            .codeQuality(UPDATED_CODE_QUALITY)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);

        restUsersDataBeanMockMvc.perform(put("/api/users-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsersDataBean)))
            .andExpect(status().isOk());

        // Validate the UsersDataBean in the database
        List<UsersDataBean> usersDataBeanList = usersDataBeanRepository.findAll();
        assertThat(usersDataBeanList).hasSize(databaseSizeBeforeUpdate);
        UsersDataBean testUsersDataBean = usersDataBeanList.get(usersDataBeanList.size() - 1);
        assertThat(testUsersDataBean.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUsersDataBean.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUsersDataBean.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testUsersDataBean.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testUsersDataBean.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUsersDataBean.getAttendence()).isEqualTo(UPDATED_ATTENDENCE);
        assertThat(testUsersDataBean.getPunctuality()).isEqualTo(UPDATED_PUNCTUALITY);
        assertThat(testUsersDataBean.getMeetingTargets()).isEqualTo(UPDATED_MEETING_TARGETS);
        assertThat(testUsersDataBean.getCompanyPolicy()).isEqualTo(UPDATED_COMPANY_POLICY);
        assertThat(testUsersDataBean.getCodeQuality()).isEqualTo(UPDATED_CODE_QUALITY);
        assertThat(testUsersDataBean.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testUsersDataBean.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingUsersDataBean() throws Exception {
        int databaseSizeBeforeUpdate = usersDataBeanRepository.findAll().size();

        // Create the UsersDataBean

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsersDataBeanMockMvc.perform(put("/api/users-data-beans").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usersDataBean)))
            .andExpect(status().isBadRequest());

        // Validate the UsersDataBean in the database
        List<UsersDataBean> usersDataBeanList = usersDataBeanRepository.findAll();
        assertThat(usersDataBeanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsersDataBean() throws Exception {
        // Initialize the database
        usersDataBeanService.save(usersDataBean);

        int databaseSizeBeforeDelete = usersDataBeanRepository.findAll().size();

        // Delete the usersDataBean
        restUsersDataBeanMockMvc.perform(delete("/api/users-data-beans/{id}", usersDataBean.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UsersDataBean> usersDataBeanList = usersDataBeanRepository.findAll();
        assertThat(usersDataBeanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
