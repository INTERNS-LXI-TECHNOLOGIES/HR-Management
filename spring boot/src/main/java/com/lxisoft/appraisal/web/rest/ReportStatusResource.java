package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.repository.ReportStatusRepository;
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
 * REST controller for managing {@link com.lxisoft.appraisal.domain.ReportStatus}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ReportStatusResource {

    private final Logger log = LoggerFactory.getLogger(ReportStatusResource.class);

    private static final String ENTITY_NAME = "reportStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportStatusRepository reportStatusRepository;

    public ReportStatusResource(ReportStatusRepository reportStatusRepository) {
        this.reportStatusRepository = reportStatusRepository;
    }

    /**
     * {@code POST  /report-statuses} : Create a new reportStatus.
     *
     * @param reportStatus the reportStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportStatus, or with status {@code 400 (Bad Request)} if the reportStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/report-statuses")
    public ResponseEntity<ReportStatus> createReportStatus(@RequestBody ReportStatus reportStatus) throws URISyntaxException {
        log.debug("REST request to save ReportStatus : {}", reportStatus);
        if (reportStatus.getId() != null) {
            throw new BadRequestAlertException("A new reportStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportStatus result = reportStatusRepository.save(reportStatus);
        return ResponseEntity.created(new URI("/api/report-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-statuses} : Updates an existing reportStatus.
     *
     * @param reportStatus the reportStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportStatus,
     * or with status {@code 400 (Bad Request)} if the reportStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/report-statuses")
    public ResponseEntity<ReportStatus> updateReportStatus(@RequestBody ReportStatus reportStatus) throws URISyntaxException {
        log.debug("REST request to update ReportStatus : {}", reportStatus);
        if (reportStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportStatus result = reportStatusRepository.save(reportStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reportStatus.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /report-statuses} : get all the reportStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportStatuses in body.
     */
    @GetMapping("/report-statuses")
    public List<ReportStatus> getAllReportStatuses() {
        log.debug("REST request to get all ReportStatuses");
        return reportStatusRepository.findAll();
    }

    /**
     * {@code GET  /report-statuses/:id} : get the "id" reportStatus.
     *
     * @param id the id of the reportStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/report-statuses/{id}")
    public ResponseEntity<ReportStatus> getReportStatus(@PathVariable Long id) {
        log.debug("REST request to get ReportStatus : {}", id);
        Optional<ReportStatus> reportStatus = reportStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(reportStatus);
    }

    /**
     * {@code DELETE  /report-statuses/:id} : delete the "id" reportStatus.
     *
     * @param id the id of the reportStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/report-statuses/{id}")
    public ResponseEntity<Void> deleteReportStatus(@PathVariable Long id) {
        log.debug("REST request to delete ReportStatus : {}", id);
        reportStatusRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
