package com.perso.proapps.web.rest;

import com.perso.proapps.domain.TaskStatus;
import com.perso.proapps.repository.TaskStatusRepository;
import com.perso.proapps.service.TaskStatusService;
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
 * REST controller for managing {@link com.perso.proapps.domain.TaskStatus}.
 */
@RestController
@RequestMapping("/api")
public class TaskStatusResource {

    private final Logger log = LoggerFactory.getLogger(TaskStatusResource.class);

    private static final String ENTITY_NAME = "taskStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskStatusService taskStatusService;

    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusResource(TaskStatusService taskStatusService, TaskStatusRepository taskStatusRepository) {
        this.taskStatusService = taskStatusService;
        this.taskStatusRepository = taskStatusRepository;
    }

    /**
     * {@code POST  /task-statuses} : Create a new taskStatus.
     *
     * @param taskStatus the taskStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskStatus, or with status {@code 400 (Bad Request)} if the taskStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/task-statuses")
    public ResponseEntity<TaskStatus> createTaskStatus(@Valid @RequestBody TaskStatus taskStatus) throws URISyntaxException {
        log.debug("REST request to save TaskStatus : {}", taskStatus);
        if (taskStatus.getId() != null) {
            throw new BadRequestAlertException("A new taskStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskStatus result = taskStatusService.save(taskStatus);
        return ResponseEntity
            .created(new URI("/api/task-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /task-statuses/:id} : Updates an existing taskStatus.
     *
     * @param id the id of the taskStatus to save.
     * @param taskStatus the taskStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskStatus,
     * or with status {@code 400 (Bad Request)} if the taskStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/task-statuses/{id}")
    public ResponseEntity<TaskStatus> updateTaskStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TaskStatus taskStatus
    ) throws URISyntaxException {
        log.debug("REST request to update TaskStatus : {}, {}", id, taskStatus);
        if (taskStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaskStatus result = taskStatusService.update(taskStatus);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taskStatus.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /task-statuses/:id} : Partial updates given fields of an existing taskStatus, field will ignore if it is null
     *
     * @param id the id of the taskStatus to save.
     * @param taskStatus the taskStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskStatus,
     * or with status {@code 400 (Bad Request)} if the taskStatus is not valid,
     * or with status {@code 404 (Not Found)} if the taskStatus is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/task-statuses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskStatus> partialUpdateTaskStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TaskStatus taskStatus
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaskStatus partially : {}, {}", id, taskStatus);
        if (taskStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskStatus> result = taskStatusService.partialUpdate(taskStatus);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taskStatus.getId().toString())
        );
    }

    /**
     * {@code GET  /task-statuses} : get all the taskStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskStatuses in body.
     */
    @GetMapping("/task-statuses")
    public List<TaskStatus> getAllTaskStatuses() {
        log.debug("REST request to get all TaskStatuses");
        return taskStatusService.findAll();
    }

    /**
     * {@code GET  /task-statuses/:id} : get the "id" taskStatus.
     *
     * @param id the id of the taskStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-statuses/{id}")
    public ResponseEntity<TaskStatus> getTaskStatus(@PathVariable Long id) {
        log.debug("REST request to get TaskStatus : {}", id);
        Optional<TaskStatus> taskStatus = taskStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskStatus);
    }

    /**
     * {@code DELETE  /task-statuses/:id} : delete the "id" taskStatus.
     *
     * @param id the id of the taskStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-statuses/{id}")
    public ResponseEntity<Void> deleteTaskStatus(@PathVariable Long id) {
        log.debug("REST request to delete TaskStatus : {}", id);
        taskStatusService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
