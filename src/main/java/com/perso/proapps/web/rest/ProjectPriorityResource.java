package com.perso.proapps.web.rest;

import com.perso.proapps.domain.ProjectPriority;
import com.perso.proapps.repository.ProjectPriorityRepository;
import com.perso.proapps.service.ProjectPriorityService;
import com.perso.proapps.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.perso.proapps.domain.ProjectPriority}.
 */
@RestController
@RequestMapping("/api")
public class ProjectPriorityResource {

    private final Logger log = LoggerFactory.getLogger(ProjectPriorityResource.class);

    private static final String ENTITY_NAME = "projectPriority";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectPriorityService projectPriorityService;

    private final ProjectPriorityRepository projectPriorityRepository;

    public ProjectPriorityResource(ProjectPriorityService projectPriorityService, ProjectPriorityRepository projectPriorityRepository) {
        this.projectPriorityService = projectPriorityService;
        this.projectPriorityRepository = projectPriorityRepository;
    }

    /**
     * {@code POST  /project-priorities} : Create a new projectPriority.
     *
     * @param projectPriority the projectPriority to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectPriority, or with status {@code 400 (Bad Request)} if the projectPriority has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-priorities")
    public ResponseEntity<ProjectPriority> createProjectPriority(@Valid @RequestBody ProjectPriority projectPriority)
        throws URISyntaxException {
        log.debug("REST request to save ProjectPriority : {}", projectPriority);
        if (projectPriority.getId() != null) {
            throw new BadRequestAlertException("A new projectPriority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectPriority result = projectPriorityService.save(projectPriority);
        return ResponseEntity
            .created(new URI("/api/project-priorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-priorities/:id} : Updates an existing projectPriority.
     *
     * @param id the id of the projectPriority to save.
     * @param projectPriority the projectPriority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectPriority,
     * or with status {@code 400 (Bad Request)} if the projectPriority is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectPriority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-priorities/{id}")
    public ResponseEntity<ProjectPriority> updateProjectPriority(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectPriority projectPriority
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectPriority : {}, {}", id, projectPriority);
        if (projectPriority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectPriority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectPriorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectPriority result = projectPriorityService.update(projectPriority);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectPriority.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-priorities/:id} : Partial updates given fields of an existing projectPriority, field will ignore if it is null
     *
     * @param id the id of the projectPriority to save.
     * @param projectPriority the projectPriority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectPriority,
     * or with status {@code 400 (Bad Request)} if the projectPriority is not valid,
     * or with status {@code 404 (Not Found)} if the projectPriority is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectPriority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-priorities/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectPriority> partialUpdateProjectPriority(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectPriority projectPriority
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectPriority partially : {}, {}", id, projectPriority);
        if (projectPriority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectPriority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectPriorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectPriority> result = projectPriorityService.partialUpdate(projectPriority);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectPriority.getId().toString())
        );
    }

    /**
     * {@code GET  /project-priorities} : get all the projectPriorities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectPriorities in body.
     */
    @GetMapping("/project-priorities")
    public List<ProjectPriority> getAllProjectPriorities() {
        log.debug("REST request to get all ProjectPriorities");
        return projectPriorityService.findAll();
    }

    /**
     * {@code GET  /project-priorities/:id} : get the "id" projectPriority.
     *
     * @param id the id of the projectPriority to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectPriority, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-priorities/{id}")
    public ResponseEntity<ProjectPriority> getProjectPriority(@PathVariable Long id) {
        log.debug("REST request to get ProjectPriority : {}", id);
        Optional<ProjectPriority> projectPriority = projectPriorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectPriority);
    }

    /**
     * {@code DELETE  /project-priorities/:id} : delete the "id" projectPriority.
     *
     * @param id the id of the projectPriority to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-priorities/{id}")
    public ResponseEntity<Void> deleteProjectPriority(@PathVariable Long id) {
        log.debug("REST request to delete ProjectPriority : {}", id);
        projectPriorityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
