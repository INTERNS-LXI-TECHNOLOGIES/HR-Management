package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.domain.UsersDataBean;
import com.lxisoft.appraisal.service.UsersDataBeanService;
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
 * REST controller for managing {@link com.lxisoft.appraisal.domain.UsersDataBean}.
 */
@RestController
@RequestMapping("/api")
public class UsersDataBeanResource {

    private final Logger log = LoggerFactory.getLogger(UsersDataBeanResource.class);

    private static final String ENTITY_NAME = "usersDataBean";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsersDataBeanService usersDataBeanService;

    public UsersDataBeanResource(UsersDataBeanService usersDataBeanService) {
        this.usersDataBeanService = usersDataBeanService;
    }

    /**
     * {@code POST  /users-data-beans} : Create a new usersDataBean.
     *
     * @param usersDataBean the usersDataBean to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usersDataBean, or with status {@code 400 (Bad Request)} if the usersDataBean has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/users-data-beans")
    public ResponseEntity<UsersDataBean> createUsersDataBean(@RequestBody UsersDataBean usersDataBean) throws URISyntaxException {
        log.debug("REST request to save UsersDataBean : {}", usersDataBean);
        if (usersDataBean.getId() != null) {
            throw new BadRequestAlertException("A new usersDataBean cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsersDataBean result = usersDataBeanService.save(usersDataBean);
        return ResponseEntity.created(new URI("/api/users-data-beans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /users-data-beans} : Updates an existing usersDataBean.
     *
     * @param usersDataBean the usersDataBean to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usersDataBean,
     * or with status {@code 400 (Bad Request)} if the usersDataBean is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usersDataBean couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/users-data-beans")
    public ResponseEntity<UsersDataBean> updateUsersDataBean(@RequestBody UsersDataBean usersDataBean) throws URISyntaxException {
        log.debug("REST request to update UsersDataBean : {}", usersDataBean);
        if (usersDataBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsersDataBean result = usersDataBeanService.save(usersDataBean);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, usersDataBean.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /users-data-beans} : get all the usersDataBeans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usersDataBeans in body.
     */
    @GetMapping("/users-data-beans")
    public List<UsersDataBean> getAllUsersDataBeans() {
        log.debug("REST request to get all UsersDataBeans");
        return usersDataBeanService.findAll();
    }

    /**
     * {@code GET  /users-data-beans/:id} : get the "id" usersDataBean.
     *
     * @param id the id of the usersDataBean to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usersDataBean, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/users-data-beans/{id}")
    public ResponseEntity<UsersDataBean> getUsersDataBean(@PathVariable Long id) {
        log.debug("REST request to get UsersDataBean : {}", id);
        Optional<UsersDataBean> usersDataBean = usersDataBeanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usersDataBean);
    }

    /**
     * {@code DELETE  /users-data-beans/:id} : delete the "id" usersDataBean.
     *
     * @param id the id of the usersDataBean to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/users-data-beans/{id}")
    public ResponseEntity<Void> deleteUsersDataBean(@PathVariable Long id) {
        log.debug("REST request to delete UsersDataBean : {}", id);
        usersDataBeanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
