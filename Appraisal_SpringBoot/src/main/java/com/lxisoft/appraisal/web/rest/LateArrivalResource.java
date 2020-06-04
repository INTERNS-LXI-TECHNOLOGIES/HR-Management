package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.repository.LateArrivalRepository;
import com.lxisoft.appraisal.service.dto.LateDTO;
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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lxisoft.appraisal.domain.LateArrival}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LateArrivalResource {

    private final Logger log = LoggerFactory.getLogger(LateArrivalResource.class);

    private static final String ENTITY_NAME = "lateArrival";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LateArrivalRepository lateArrivalRepository;

    public LateArrivalResource(LateArrivalRepository lateArrivalRepository) {
        this.lateArrivalRepository = lateArrivalRepository;
    }

    /**
     * {@code POST  /late-arrivals} : Create a new lateArrival.
     *
     * @param lateArrival the lateArrival to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lateArrival, or with status {@code 400 (Bad Request)} if the lateArrival has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/late-arrivals")
    public ResponseEntity<LateArrival> createLateArrival(@RequestBody LateDTO lateDTO) throws URISyntaxException
    {
        LateArrival lateArrival = new LateArrival();
        lateArrival.setReachedTime(Instant.parse(lateDTO.getReachedTime()));
        lateArrival.setType(lateDTO.getType());
        log.debug("REST request to save LateArrival : {}", lateArrival);
        if (lateArrival.getId() != null) {
            throw new BadRequestAlertException("A new lateArrival cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LateArrival result = lateArrivalRepository.save(lateArrival);
        return ResponseEntity.created(new URI("/api/late-arrivals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /late-arrivals} : Updates an existing lateArrival.
     *
     * @param lateArrival the lateArrival to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lateArrival,
     * or with status {@code 400 (Bad Request)} if the lateArrival is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lateArrival couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/late-arrivals")
    public ResponseEntity<LateArrival> updateLateArrival(@RequestBody LateArrival lateArrival) throws URISyntaxException {
        log.debug("REST request to update LateArrival : {}", lateArrival);
        if (lateArrival.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LateArrival result = lateArrivalRepository.save(lateArrival);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lateArrival.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /late-arrivals} : get all the lateArrivals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lateArrivals in body.
     */
    @GetMapping("/late-arrivals")
    public List<LateArrival> getAllLateArrivals() {
        log.debug("REST request to get all LateArrivals");
        return lateArrivalRepository.findAll();
    }

    /**
     * {@code GET  /late-arrivals/:id} : get the "id" lateArrival.
     *
     * @param id the id of the lateArrival to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lateArrival, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/late-arrivals/{id}")
    public ResponseEntity<LateArrival> getLateArrival(@PathVariable Long id) {
        log.debug("REST request to get LateArrival : {}", id);
        Optional<LateArrival> lateArrival = lateArrivalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lateArrival);
    }

    /**
     * {@code DELETE  /late-arrivals/:id} : delete the "id" lateArrival.
     *
     * @param id the id of the lateArrival to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/late-arrivals/{id}")
    public ResponseEntity<Void> deleteLateArrival(@PathVariable Long id) {
        log.debug("REST request to delete LateArrival : {}", id);
        lateArrivalRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
