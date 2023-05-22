package com.perso.proapps.web.rest;

import com.perso.proapps.domain.ProjectStatus;
import com.perso.proapps.repository.ProjectStatusRepository;
import com.perso.proapps.service.ProjectStatusService;
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
 * REST controller for managing {@link com.perso.proapps.domain.ProjectStatus}.
 */
@RestController
@RequestMapping("/api")
public class ProjectStatusResource {

    private final Logger log = LoggerFactory.getLogger(ProjectStatusResource.class);

    private static final String ENTITY_NAME = "projectStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectStatusService projectStatusService;

    private final ProjectStatusRepository projectStatusRepository;

    public ProjectStatusResource(ProjectStatusService projectStatusService, ProjectStatusRepository projectStatusRepository) {
        this.projectStatusService = projectStatusService;
        this.projectStatusRepository = projectStatusRepository;
    }

    /**
     * {@code POST  /project-statuses} : Create a new projectStatus.
     *
     * @param projectStatus the projectStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectStatus, or with status {@code 400 (Bad Request)} if the projectStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-statuses")
    public ResponseEntity<ProjectStatus> createProjectStatus(@Valid @RequestBody ProjectStatus projectStatus) throws URISyntaxException {
        log.debug("REST request to save ProjectStatus : {}", projectStatus);
        if (projectStatus.getId() != null) {
            throw new BadRequestAlertException("A new projectStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectStatus result = projectStatusService.save(projectStatus);
        return ResponseEntity
            .created(new URI("/api/project-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-statuses/:id} : Updates an existing projectStatus.
     *
     * @param id the id of the projectStatus to save.
     * @param projectStatus the projectStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectStatus,
     * or with status {@code 400 (Bad Request)} if the projectStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-statuses/{id}")
    public ResponseEntity<ProjectStatus> updateProjectStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectStatus projectStatus
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectStatus : {}, {}", id, projectStatus);
        if (projectStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectStatus result = projectStatusService.update(projectStatus);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectStatus.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-statuses/:id} : Partial updates given fields of an existing projectStatus, field will ignore if it is null
     *
     * @param id the id of the projectStatus to save.
     * @param projectStatus the projectStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectStatus,
     * or with status {@code 400 (Bad Request)} if the projectStatus is not valid,
     * or with status {@code 404 (Not Found)} if the projectStatus is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-statuses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectStatus> partialUpdateProjectStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectStatus projectStatus
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectStatus partially : {}, {}", id, projectStatus);
        if (projectStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectStatus> result = projectStatusService.partialUpdate(projectStatus);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectStatus.getId().toString())
        );
    }

    /**
     * {@code GET  /project-statuses} : get all the projectStatuses.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectStatuses in body.
     */
    @GetMapping("/project-statuses")
    public List<ProjectStatus> getAllProjectStatuses(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all ProjectStatuses");
        return projectStatusService.findAll();
    }

    /**
     * {@code GET  /project-statuses/:id} : get the "id" projectStatus.
     *
     * @param id the id of the projectStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-statuses/{id}")
    public ResponseEntity<ProjectStatus> getProjectStatus(@PathVariable Long id) {
        log.debug("REST request to get ProjectStatus : {}", id);
        Optional<ProjectStatus> projectStatus = projectStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectStatus);
    }

    /**
     * {@code DELETE  /project-statuses/:id} : delete the "id" projectStatus.
     *
     * @param id the id of the projectStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-statuses/{id}")
    public ResponseEntity<Void> deleteProjectStatus(@PathVariable Long id) {
        log.debug("REST request to delete ProjectStatus : {}", id);
        projectStatusService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
