package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.GitRepository;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.service.UserExtraService;
import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.service.dto.GitDTO;
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
 * REST controller for managing {@link com.lxisoft.appraisal.domain.Git}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GitResource {

    private final Logger log = LoggerFactory.getLogger(GitResource.class);

    private static final String ENTITY_NAME = "git";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GitRepository gitRepository;
    @Autowired
    UserExtraService userExSer;
    @Autowired
    UserService userSer;
    @Autowired
    UserExtraRepository userExtraRepo;
    public GitResource(GitRepository gitRepository) {
        this.gitRepository = gitRepository;
    }

    /**
     * {@code POST  /gits} : Create a new git.
     *
     * @param git the git to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new git, or with status {@code 400 (Bad Request)} if the git has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gits")
    public ResponseEntity<Git> createGit(@RequestBody GitDTO gitDTO) throws URISyntaxException {


        ArrayList<User> user=(ArrayList<User>) userSer.getAllUsers();
        ArrayList<UserExtra> userExtra=(ArrayList<UserExtra>) userExSer.getAllExtraUsers();

        String name = gitDTO.getName();
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
        Git git = new Git();
        LocalDate localDate = LocalDate.now();
        git.setDate(localDate);
        git.setUserExtra(userExtraRepo.findById(id).get());
         Long mark = Long.parseLong(gitDTO.getMark());
        git.setMark(mark);
        log.debug("REST request to save Git : {}", git);
        if (git.getId() != null) {
            throw new BadRequestAlertException("A new git cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Git result = gitRepository.save(git);
        return ResponseEntity.created(new URI("/api/gits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gits} : Updates an existing git.
     *
     * @param git the git to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated git,
     * or with status {@code 400 (Bad Request)} if the git is not valid,
     * or with status {@code 500 (Internal Server Error)} if the git couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gits")
    public ResponseEntity<Git> updateGit(@RequestBody Git git) throws URISyntaxException {
        log.debug("REST request to update Git : {}", git);
        if (git.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Git result = gitRepository.save(git);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, git.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gits} : get all the gits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gits in body.
     */
    @GetMapping("/gits")
    public List<Git> getAllGits() {
        log.debug("REST request to get all Gits");
        return gitRepository.findAll();
    }

    /**
     * {@code GET  /gits/:id} : get the "id" git.
     *
     * @param id the id of the git to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the git, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gits/{id}")
    public ResponseEntity<Git> getGit(@PathVariable Long id) {
        log.debug("REST request to get Git : {}", id);
        Optional<Git> git = gitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(git);
    }

    /**
     * {@code DELETE  /gits/:id} : delete the "id" git.
     *
     * @param id the id of the git to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gits/{id}")
    public ResponseEntity<Void> deleteGit(@PathVariable Long id) {
        log.debug("REST request to delete Git : {}", id);
        gitRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
