package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.HackathonRepository;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.service.UserExtraService;
import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.service.dto.HackDTO;
import com.lxisoft.appraisal.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lxisoft.appraisal.domain.Hackathon}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class HackathonResource {

    private final Logger log = LoggerFactory.getLogger(HackathonResource.class);

    private static final String ENTITY_NAME = "hackathon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HackathonRepository hackathonRepository;
    @Autowired
    UserExtraService userExSer;
    @Autowired
    UserService userSer;
    @Autowired
    UserExtraRepository userExtraRepo;
    public HackathonResource(HackathonRepository hackathonRepository) {
        this.hackathonRepository = hackathonRepository;
    }

    /**
     * {@code POST  /hackathons} : Create a new hackathon.
     *
     * @param hackathon the hackathon to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hackathon, or with status {@code 400 (Bad Request)} if the hackathon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hackathons")
    public ResponseEntity<Hackathon> createHackathon(@RequestBody HackDTO hackDTO) throws URISyntaxException {

        Hackathon hackathon = new Hackathon();
        ArrayList<User> user=(ArrayList<User>) userSer.getAllUsers();
        ArrayList<UserExtra> userExtra=(ArrayList<UserExtra>) userExSer.getAllExtraUsers();

        String name = hackDTO.getName();
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

        LocalDate localDate = LocalDate.now();
        hackathon.setDate(localDate);
        hackathon.setUserExtra(userExtraRepo.findById(id).get());
         Long mark = Long.parseLong(hackDTO.getMark());
         hackathon.setMark(mark);
        log.debug("REST request to save Hackathon : {}", hackathon);
        if (hackathon.getId() != null) {
            throw new BadRequestAlertException("A new hackathon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Hackathon result = hackathonRepository.save(hackathon);
        return ResponseEntity.created(new URI("/api/hackathons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hackathons} : Updates an existing hackathon.
     *
     * @param hackathon the hackathon to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hackathon,
     * or with status {@code 400 (Bad Request)} if the hackathon is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hackathon couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hackathons")
    public ResponseEntity<Hackathon> updateHackathon(@RequestBody Hackathon hackathon) throws URISyntaxException {
        log.debug("REST request to update Hackathon : {}", hackathon);
        if (hackathon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Hackathon result = hackathonRepository.save(hackathon);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hackathon.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /hackathons} : get all the hackathons.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hackathons in body.
     */
    @GetMapping("/hackathons")
    public List<Hackathon> getAllHackathons() {
        log.debug("REST request to get all Hackathons");
        return hackathonRepository.findAll();
    }

    /**
     * {@code GET  /hackathons/:id} : get the "id" hackathon.
     *
     * @param id the id of the hackathon to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hackathon, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hackathons/{id}")
    public ResponseEntity<Hackathon> getHackathon(@PathVariable Long id) {
        log.debug("REST request to get Hackathon : {}", id);
        Optional<Hackathon> hackathon = hackathonRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hackathon);
    }

    /**
     * {@code DELETE  /hackathons/:id} : delete the "id" hackathon.
     *
     * @param id the id of the hackathon to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hackathons/{id}")
    public ResponseEntity<Void> deleteHackathon(@PathVariable Long id) {
        log.debug("REST request to delete Hackathon : {}", id);
        hackathonRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
