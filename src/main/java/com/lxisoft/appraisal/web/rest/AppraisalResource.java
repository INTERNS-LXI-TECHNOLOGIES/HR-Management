package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.repository.AppraisalRepository;
import com.lxisoft.appraisal.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lxisoft.appraisal.domain.Appraisal}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AppraisalResource {

    private final Logger log = LoggerFactory.getLogger(AppraisalResource.class);

    private static final String ENTITY_NAME = "appraisal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppraisalRepository appraisalRepository;

    public AppraisalResource(AppraisalRepository appraisalRepository) {
        this.appraisalRepository = appraisalRepository;
    }

    /**
     * {@code POST  /appraisals} : Create a new appraisal.
     *
     * @param appraisal the appraisal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appraisal, or with status {@code 400 (Bad Request)} if the appraisal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/appraisals")
    public ResponseEntity<Appraisal> createAppraisal(@RequestBody Appraisal appraisal) throws URISyntaxException {
        log.debug("REST request to save Appraisal : {}", appraisal);
        if (appraisal.getId() != null) {
            throw new BadRequestAlertException("A new appraisal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Appraisal result = appraisalRepository.save(appraisal);
        return ResponseEntity.created(new URI("/api/appraisals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /appraisals} : Updates an existing appraisal.
     *
     * @param appraisal the appraisal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appraisal,
     * or with status {@code 400 (Bad Request)} if the appraisal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appraisal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/appraisals")
    public ResponseEntity<Appraisal> updateAppraisal(@RequestBody Appraisal appraisal) throws URISyntaxException {
        log.debug("REST request to update Appraisal : {}", appraisal);
        if (appraisal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Appraisal result = appraisalRepository.save(appraisal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appraisal.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /appraisals} : get all the appraisals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appraisals in body.
     */
    @GetMapping("/appraisals")
    public List<Appraisal> getAllAppraisals() {
        log.debug("REST request to get all Appraisals");
        return appraisalRepository.findAll();
    }

    /**
     * {@code GET  /appraisals/:id} : get the "id" appraisal.
     *
     * @param id the id of the appraisal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appraisal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/appraisals/{id}")
    public ResponseEntity<Appraisal> getAppraisal(@PathVariable Long id) {
        log.debug("REST request to get Appraisal : {}", id);
        Optional<Appraisal> appraisal = appraisalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(appraisal);
    }

    /**
     * {@code DELETE  /appraisals/:id} : delete the "id" appraisal.
     *
     * @param id the id of the appraisal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/appraisals/{id}")
    public ResponseEntity<Void> deleteAppraisal(@PathVariable Long id) {
        log.debug("REST request to delete Appraisal : {}", id);
        appraisalRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
