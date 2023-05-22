package com.perso.proapps.service;

import com.perso.proapps.domain.TaskStatus;
import com.perso.proapps.repository.TaskStatusRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskStatus}.
 */
@Service
@Transactional
public class TaskStatusService {

    private final Logger log = LoggerFactory.getLogger(TaskStatusService.class);

    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    /**
     * Save a taskStatus.
     *
     * @param taskStatus the entity to save.
     * @return the persisted entity.
     */
    public TaskStatus save(TaskStatus taskStatus) {
        log.debug("Request to save TaskStatus : {}", taskStatus);
        return taskStatusRepository.save(taskStatus);
    }

    /**
     * Update a taskStatus.
     *
     * @param taskStatus the entity to save.
     * @return the persisted entity.
     */
    public TaskStatus update(TaskStatus taskStatus) {
        log.debug("Request to update TaskStatus : {}", taskStatus);
        return taskStatusRepository.save(taskStatus);
    }

    /**
     * Partially update a taskStatus.
     *
     * @param taskStatus the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaskStatus> partialUpdate(TaskStatus taskStatus) {
        log.debug("Request to partially update TaskStatus : {}", taskStatus);

        return taskStatusRepository
            .findById(taskStatus.getId())
            .map(existingTaskStatus -> {
                if (taskStatus.getCode() != null) {
                    existingTaskStatus.setCode(taskStatus.getCode());
                }
                if (taskStatus.getStatus() != null) {
                    existingTaskStatus.setStatus(taskStatus.getStatus());
                }
                if (taskStatus.getDateCreation() != null) {
                    existingTaskStatus.setDateCreation(taskStatus.getDateCreation());
                }
                if (taskStatus.getDateModify() != null) {
                    existingTaskStatus.setDateModify(taskStatus.getDateModify());
                }
                if (taskStatus.getLastModifyBy() != null) {
                    existingTaskStatus.setLastModifyBy(taskStatus.getLastModifyBy());
                }
                if (taskStatus.getCreatedBy() != null) {
                    existingTaskStatus.setCreatedBy(taskStatus.getCreatedBy());
                }
                if (taskStatus.getNotes() != null) {
                    existingTaskStatus.setNotes(taskStatus.getNotes());
                }

                return existingTaskStatus;
            })
            .map(taskStatusRepository::save);
    }

    /**
     * Get all the taskStatuses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TaskStatus> findAll() {
        log.debug("Request to get all TaskStatuses");
        return taskStatusRepository.findAll();
    }

    /**
     * Get one taskStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaskStatus> findOne(Long id) {
        log.debug("Request to get TaskStatus : {}", id);
        return taskStatusRepository.findById(id);
    }

    /**
     * Delete the taskStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TaskStatus : {}", id);
        taskStatusRepository.deleteById(id);
    }
}
