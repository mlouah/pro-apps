package com.perso.proapps.web.rest;

import com.perso.proapps.domain.TaskPriority;
import com.perso.proapps.repository.TaskPriorityRepository;
import com.perso.proapps.service.TaskPriorityService;
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
 * REST controller for managing {@link com.perso.proapps.domain.TaskPriority}.
 */
@RestController
@RequestMapping("/api")
public class TaskPriorityResource {

    private final Logger log = LoggerFactory.getLogger(TaskPriorityResource.class);

    private static final String ENTITY_NAME = "taskPriority";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskPriorityService taskPriorityService;

    private final TaskPriorityRepository taskPriorityRepository;

    public TaskPriorityResource(TaskPriorityService taskPriorityService, TaskPriorityRepository taskPriorityRepository) {
        this.taskPriorityService = taskPriorityService;
        this.taskPriorityRepository = taskPriorityRepository;
    }

    /**
     * {@code POST  /task-priorities} : Create a new taskPriority.
     *
     * @param taskPriority the taskPriority to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskPriority, or with status {@code 400 (Bad Request)} if the taskPriority has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/task-priorities")
    public ResponseEntity<TaskPriority> createTaskPriority(@Valid @RequestBody TaskPriority taskPriority) throws URISyntaxException {
        log.debug("REST request to save TaskPriority : {}", taskPriority);
        if (taskPriority.getId() != null) {
            throw new BadRequestAlertException("A new taskPriority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskPriority result = taskPriorityService.save(taskPriority);
        return ResponseEntity
            .created(new URI("/api/task-priorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /task-priorities/:id} : Updates an existing taskPriority.
     *
     * @param id the id of the taskPriority to save.
     * @param taskPriority the taskPriority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskPriority,
     * or with status {@code 400 (Bad Request)} if the taskPriority is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskPriority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/task-priorities/{id}")
    public ResponseEntity<TaskPriority> updateTaskPriority(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TaskPriority taskPriority
    ) throws URISyntaxException {
        log.debug("REST request to update TaskPriority : {}, {}", id, taskPriority);
        if (taskPriority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskPriority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskPriorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaskPriority result = taskPriorityService.update(taskPriority);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taskPriority.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /task-priorities/:id} : Partial updates given fields of an existing taskPriority, field will ignore if it is null
     *
     * @param id the id of the taskPriority to save.
     * @param taskPriority the taskPriority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskPriority,
     * or with status {@code 400 (Bad Request)} if the taskPriority is not valid,
     * or with status {@code 404 (Not Found)} if the taskPriority is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskPriority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/task-priorities/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskPriority> partialUpdateTaskPriority(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TaskPriority taskPriority
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaskPriority partially : {}, {}", id, taskPriority);
        if (taskPriority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskPriority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskPriorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskPriority> result = taskPriorityService.partialUpdate(taskPriority);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taskPriority.getId().toString())
        );
    }

    /**
     * {@code GET  /task-priorities} : get all the taskPriorities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskPriorities in body.
     */
    @GetMapping("/task-priorities")
    public List<TaskPriority> getAllTaskPriorities() {
        log.debug("REST request to get all TaskPriorities");
        return taskPriorityService.findAll();
    }

    /**
     * {@code GET  /task-priorities/:id} : get the "id" taskPriority.
     *
     * @param id the id of the taskPriority to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskPriority, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-priorities/{id}")
    public ResponseEntity<TaskPriority> getTaskPriority(@PathVariable Long id) {
        log.debug("REST request to get TaskPriority : {}", id);
        Optional<TaskPriority> taskPriority = taskPriorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskPriority);
    }

    /**
     * {@code DELETE  /task-priorities/:id} : delete the "id" taskPriority.
     *
     * @param id the id of the taskPriority to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-priorities/{id}")
    public ResponseEntity<Void> deleteTaskPriority(@PathVariable Long id) {
        log.debug("REST request to delete TaskPriority : {}", id);
        taskPriorityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
