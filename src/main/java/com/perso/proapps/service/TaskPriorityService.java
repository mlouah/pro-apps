package com.perso.proapps.service;

import com.perso.proapps.domain.TaskPriority;
import com.perso.proapps.repository.TaskPriorityRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskPriority}.
 */
@Service
@Transactional
public class TaskPriorityService {

    private final Logger log = LoggerFactory.getLogger(TaskPriorityService.class);

    private final TaskPriorityRepository taskPriorityRepository;

    public TaskPriorityService(TaskPriorityRepository taskPriorityRepository) {
        this.taskPriorityRepository = taskPriorityRepository;
    }

    /**
     * Save a taskPriority.
     *
     * @param taskPriority the entity to save.
     * @return the persisted entity.
     */
    public TaskPriority save(TaskPriority taskPriority) {
        log.debug("Request to save TaskPriority : {}", taskPriority);
        return taskPriorityRepository.save(taskPriority);
    }

    /**
     * Update a taskPriority.
     *
     * @param taskPriority the entity to save.
     * @return the persisted entity.
     */
    public TaskPriority update(TaskPriority taskPriority) {
        log.debug("Request to update TaskPriority : {}", taskPriority);
        return taskPriorityRepository.save(taskPriority);
    }

    /**
     * Partially update a taskPriority.
     *
     * @param taskPriority the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaskPriority> partialUpdate(TaskPriority taskPriority) {
        log.debug("Request to partially update TaskPriority : {}", taskPriority);

        return taskPriorityRepository
            .findById(taskPriority.getId())
            .map(existingTaskPriority -> {
                if (taskPriority.getCode() != null) {
                    existingTaskPriority.setCode(taskPriority.getCode());
                }
                if (taskPriority.getPriority() != null) {
                    existingTaskPriority.setPriority(taskPriority.getPriority());
                }
                if (taskPriority.getTaskPriorityPicto() != null) {
                    existingTaskPriority.setTaskPriorityPicto(taskPriority.getTaskPriorityPicto());
                }
                if (taskPriority.getTaskPriorityPictoContentType() != null) {
                    existingTaskPriority.setTaskPriorityPictoContentType(taskPriority.getTaskPriorityPictoContentType());
                }
                if (taskPriority.getNotes() != null) {
                    existingTaskPriority.setNotes(taskPriority.getNotes());
                }

                return existingTaskPriority;
            })
            .map(taskPriorityRepository::save);
    }

    /**
     * Get all the taskPriorities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TaskPriority> findAll() {
        log.debug("Request to get all TaskPriorities");
        return taskPriorityRepository.findAll();
    }

    /**
     * Get one taskPriority by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaskPriority> findOne(Long id) {
        log.debug("Request to get TaskPriority : {}", id);
        return taskPriorityRepository.findById(id);
    }

    /**
     * Delete the taskPriority by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TaskPriority : {}", id);
        taskPriorityRepository.deleteById(id);
    }
}
