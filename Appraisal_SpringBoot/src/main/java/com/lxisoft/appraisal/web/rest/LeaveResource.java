package com.lxisoft.appraisal.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Null;

import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.LeaveRepository;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.service.UserExtraService;
import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.service.dto.LeaveDTO;
import com.lxisoft.appraisal.web.rest.errors.BadRequestAlertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.lxisoft.appraisal.domain.Leave}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LeaveResource {

    private final Logger log = LoggerFactory.getLogger(LeaveResource.class);

    private static final String ENTITY_NAME = "leave";



    @Value("${jhipster.clientApp.name}")
    private String applicationName;
   @Autowired
    private final LeaveRepository leaveRepository;
    @Autowired
    UserExtraService userExSer;
    @Autowired
    UserService userSer;
    @Autowired
    UserExtraRepository userExtraRepo;
    public LeaveResource(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    /**
     * {@code POST  /leaves} : Create a new leave.
     *
     * @param leave the leave to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leave, or with status {@code 400 (Bad Request)} if the leave has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leaves")
    public ResponseEntity<Leave> createLeave(@RequestBody LeaveDTO leaveDTO) throws URISyntaxException
     {

        ArrayList<User> user=(ArrayList<User>) userSer.getAllUsers();
        ArrayList<UserExtra> userExtra=(ArrayList<UserExtra>) userExSer.getAllExtraUsers();

        String name = leaveDTO.getName();
        long id= 0;
        for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			User u=user.get(i);

			if(name.equals(m))
			{
				 id=user.get(i).getId();

			}
        }

         Leave leave = new Leave();
         leave.setDate(LocalDate.parse(leaveDTO.getleaveDate()));
         leave.setType(leaveDTO.getType());
         leave.setUserExtra(userExtraRepo.findById(id).get());

        log.debug("REST request to save Leave : {}", leave);
        if (leave.getId() != null) {
            throw new BadRequestAlertException("A new leave cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Leave result = leaveRepository.save(leave);
        return ResponseEntity.created(new URI("/api/leaves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leaves} : Updates an existing leave.
     *
     * @param leave the leave to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leave,
     * or with status {@code 400 (Bad Request)} if the leave is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leave couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leaves")
    public ResponseEntity<Leave> updateLeave(@RequestBody LeaveDTO leaveDTO) throws URISyntaxException {

        Leave leave = new Leave();

        log.debug("REST request to update Leave : {}", leave);
        if (leave.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Leave result = leaveRepository.save(leave);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, leave.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /leaves} : get all the leaves.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaves in body.
     */
    @GetMapping("/leaves")
    public List<Leave> getAllLeaves() {
        log.debug("REST request to get all Leaves");
        return leaveRepository.findAll();
    }

    /**
     * {@code GET  /leaves/:id} : get the "id" leave.
     *
     * @param id the id of the leave to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leave, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leaves/{id}")
    public ResponseEntity<Leave> getLeave(@PathVariable Long id) {
        log.debug("REST request to get Leave : {}", id);
        Optional<Leave> leave = leaveRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leave);
    }

    /**
     * {@code DELETE  /leaves/:id} : delete the "id" leave.
     *
     * @param id the id of the leave to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leaves/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        log.debug("REST request to delete Leave : {}", id);
        leaveRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
