package com.perso.proapps.web.rest;

import com.perso.proapps.domain.ProjectCategory;
import com.perso.proapps.repository.ProjectCategoryRepository;
import com.perso.proapps.service.ProjectCategoryService;
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
 * REST controller for managing {@link com.perso.proapps.domain.ProjectCategory}.
 */
@RestController
@RequestMapping("/api")
public class ProjectCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ProjectCategoryResource.class);

    private static final String ENTITY_NAME = "projectCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectCategoryService projectCategoryService;

    private final ProjectCategoryRepository projectCategoryRepository;

    public ProjectCategoryResource(ProjectCategoryService projectCategoryService, ProjectCategoryRepository projectCategoryRepository) {
        this.projectCategoryService = projectCategoryService;
        this.projectCategoryRepository = projectCategoryRepository;
    }

    /**
     * {@code POST  /project-categories} : Create a new projectCategory.
     *
     * @param projectCategory the projectCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectCategory, or with status {@code 400 (Bad Request)} if the projectCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-categories")
    public ResponseEntity<ProjectCategory> createProjectCategory(@Valid @RequestBody ProjectCategory projectCategory)
        throws URISyntaxException {
        log.debug("REST request to save ProjectCategory : {}", projectCategory);
        if (projectCategory.getId() != null) {
            throw new BadRequestAlertException("A new projectCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectCategory result = projectCategoryService.save(projectCategory);
        return ResponseEntity
            .created(new URI("/api/project-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-categories/:id} : Updates an existing projectCategory.
     *
     * @param id the id of the projectCategory to save.
     * @param projectCategory the projectCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectCategory,
     * or with status {@code 400 (Bad Request)} if the projectCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-categories/{id}")
    public ResponseEntity<ProjectCategory> updateProjectCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectCategory projectCategory
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectCategory : {}, {}", id, projectCategory);
        if (projectCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectCategory result = projectCategoryService.update(projectCategory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectCategory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-categories/:id} : Partial updates given fields of an existing projectCategory, field will ignore if it is null
     *
     * @param id the id of the projectCategory to save.
     * @param projectCategory the projectCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectCategory,
     * or with status {@code 400 (Bad Request)} if the projectCategory is not valid,
     * or with status {@code 404 (Not Found)} if the projectCategory is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectCategory> partialUpdateProjectCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectCategory projectCategory
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectCategory partially : {}, {}", id, projectCategory);
        if (projectCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectCategory> result = projectCategoryService.partialUpdate(projectCategory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectCategory.getId().toString())
        );
    }

    /**
     * {@code GET  /project-categories} : get all the projectCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectCategories in body.
     */
    @GetMapping("/project-categories")
    public List<ProjectCategory> getAllProjectCategories() {
        log.debug("REST request to get all ProjectCategories");
        return projectCategoryService.findAll();
    }

    /**
     * {@code GET  /project-categories/:id} : get the "id" projectCategory.
     *
     * @param id the id of the projectCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-categories/{id}")
    public ResponseEntity<ProjectCategory> getProjectCategory(@PathVariable Long id) {
        log.debug("REST request to get ProjectCategory : {}", id);
        Optional<ProjectCategory> projectCategory = projectCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectCategory);
    }

    /**
     * {@code DELETE  /project-categories/:id} : delete the "id" projectCategory.
     *
     * @param id the id of the projectCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-categories/{id}")
    public ResponseEntity<Void> deleteProjectCategory(@PathVariable Long id) {
        log.debug("REST request to delete ProjectCategory : {}", id);
        projectCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
