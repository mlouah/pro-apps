package com.perso.proapps.web.rest;

import com.perso.proapps.domain.ProjectStatusCode;
import com.perso.proapps.repository.ProjectStatusCodeRepository;
import com.perso.proapps.service.ProjectStatusCodeService;
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
 * REST controller for managing {@link com.perso.proapps.domain.ProjectStatusCode}.
 */
@RestController
@RequestMapping("/api")
public class ProjectStatusCodeResource {

    private final Logger log = LoggerFactory.getLogger(ProjectStatusCodeResource.class);

    private static final String ENTITY_NAME = "projectStatusCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectStatusCodeService projectStatusCodeService;

    private final ProjectStatusCodeRepository projectStatusCodeRepository;

    public ProjectStatusCodeResource(
        ProjectStatusCodeService projectStatusCodeService,
        ProjectStatusCodeRepository projectStatusCodeRepository
    ) {
        this.projectStatusCodeService = projectStatusCodeService;
        this.projectStatusCodeRepository = projectStatusCodeRepository;
    }

    /**
     * {@code POST  /project-status-codes} : Create a new projectStatusCode.
     *
     * @param projectStatusCode the projectStatusCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectStatusCode, or with status {@code 400 (Bad Request)} if the projectStatusCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-status-codes")
    public ResponseEntity<ProjectStatusCode> createProjectStatusCode(@Valid @RequestBody ProjectStatusCode projectStatusCode)
        throws URISyntaxException {
        log.debug("REST request to save ProjectStatusCode : {}", projectStatusCode);
        if (projectStatusCode.getId() != null) {
            throw new BadRequestAlertException("A new projectStatusCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectStatusCode result = projectStatusCodeService.save(projectStatusCode);
        return ResponseEntity
            .created(new URI("/api/project-status-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-status-codes/:id} : Updates an existing projectStatusCode.
     *
     * @param id the id of the projectStatusCode to save.
     * @param projectStatusCode the projectStatusCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectStatusCode,
     * or with status {@code 400 (Bad Request)} if the projectStatusCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectStatusCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-status-codes/{id}")
    public ResponseEntity<ProjectStatusCode> updateProjectStatusCode(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectStatusCode projectStatusCode
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectStatusCode : {}, {}", id, projectStatusCode);
        if (projectStatusCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectStatusCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectStatusCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectStatusCode result = projectStatusCodeService.update(projectStatusCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectStatusCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-status-codes/:id} : Partial updates given fields of an existing projectStatusCode, field will ignore if it is null
     *
     * @param id the id of the projectStatusCode to save.
     * @param projectStatusCode the projectStatusCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectStatusCode,
     * or with status {@code 400 (Bad Request)} if the projectStatusCode is not valid,
     * or with status {@code 404 (Not Found)} if the projectStatusCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectStatusCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-status-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectStatusCode> partialUpdateProjectStatusCode(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectStatusCode projectStatusCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectStatusCode partially : {}, {}", id, projectStatusCode);
        if (projectStatusCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectStatusCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectStatusCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectStatusCode> result = projectStatusCodeService.partialUpdate(projectStatusCode);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectStatusCode.getId().toString())
        );
    }

    /**
     * {@code GET  /project-status-codes} : get all the projectStatusCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectStatusCodes in body.
     */
    @GetMapping("/project-status-codes")
    public List<ProjectStatusCode> getAllProjectStatusCodes() {
        log.debug("REST request to get all ProjectStatusCodes");
        return projectStatusCodeService.findAll();
    }

    /**
     * {@code GET  /project-status-codes/:id} : get the "id" projectStatusCode.
     *
     * @param id the id of the projectStatusCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectStatusCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-status-codes/{id}")
    public ResponseEntity<ProjectStatusCode> getProjectStatusCode(@PathVariable Long id) {
        log.debug("REST request to get ProjectStatusCode : {}", id);
        Optional<ProjectStatusCode> projectStatusCode = projectStatusCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectStatusCode);
    }

    /**
     * {@code DELETE  /project-status-codes/:id} : delete the "id" projectStatusCode.
     *
     * @param id the id of the projectStatusCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-status-codes/{id}")
    public ResponseEntity<Void> deleteProjectStatusCode(@PathVariable Long id) {
        log.debug("REST request to delete ProjectStatusCode : {}", id);
        projectStatusCodeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
