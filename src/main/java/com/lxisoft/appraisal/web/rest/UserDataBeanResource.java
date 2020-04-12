package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.service.UserDataBeanService;
import com.lxisoft.appraisal.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lxisoft.appraisal.domain.UserDataBean}.
 */
@RestController
@RequestMapping("/api")
public class UserDataBeanResource {

    private final Logger log = LoggerFactory.getLogger(UserDataBeanResource.class);

    private static final String ENTITY_NAME = "userDataBean";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserDataBeanService userDataBeanService;

    public UserDataBeanResource(UserDataBeanService userDataBeanService) {
        this.userDataBeanService = userDataBeanService;
    }

    /**
     * {@code POST  /user-data-beans} : Create a new userDataBean.
     *
     * @param userDataBean the userDataBean to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userDataBean, or with status {@code 400 (Bad Request)} if the userDataBean has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-data-beans")
    public ResponseEntity<UserDataBean> createUserDataBean(@RequestBody UserDataBean userDataBean) throws URISyntaxException {
        log.debug("REST request to save UserDataBean : {}", userDataBean);
        if (userDataBean.getId() != null) {
            throw new BadRequestAlertException("A new userDataBean cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserDataBean result = userDataBeanService.save(userDataBean);
        return ResponseEntity.created(new URI("/api/user-data-beans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-data-beans} : Updates an existing userDataBean.
     *
     * @param userDataBean the userDataBean to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDataBean,
     * or with status {@code 400 (Bad Request)} if the userDataBean is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userDataBean couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-data-beans")
    public ResponseEntity<UserDataBean> updateUserDataBean(@RequestBody UserDataBean userDataBean) throws URISyntaxException {
        log.debug("REST request to update UserDataBean : {}", userDataBean);
        if (userDataBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserDataBean result = userDataBeanService.save(userDataBean);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userDataBean.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-data-beans} : get all the userDataBeans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userDataBeans in body.
     */
    @GetMapping("/user-data-beans")
    public List<UserDataBean> getAllUserDataBeans() {
        log.debug("REST request to get all UserDataBeans");
        return userDataBeanService.findAll();
    }

    /**
     * {@code GET  /user-data-beans/:id} : get the "id" userDataBean.
     *
     * @param id the id of the userDataBean to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDataBean, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-data-beans/{id}")
    public ResponseEntity<UserDataBean> getUserDataBean(@PathVariable Long id) {
        log.debug("REST request to get UserDataBean : {}", id);
        Optional<UserDataBean> userDataBean = userDataBeanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDataBean);
    }

    /**
     * {@code DELETE  /user-data-beans/:id} : delete the "id" userDataBean.
     *
     * @param id the id of the userDataBean to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-data-beans/{id}")
    public ResponseEntity<Void> deleteUserDataBean(@PathVariable Long id) {
        log.debug("REST request to delete UserDataBean : {}", id);
        userDataBeanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
