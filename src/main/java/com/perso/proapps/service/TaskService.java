package com.perso.proapps.service;

import com.perso.proapps.domain.Task;
import com.perso.proapps.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Task}.
 */
@Service
@Transactional
public class TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Save a task.
     *
     * @param task the entity to save.
     * @return the persisted entity.
     */
    public Task save(Task task) {
        log.debug("Request to save Task : {}", task);
        return taskRepository.save(task);
    }

    /**
     * Update a task.
     *
     * @param task the entity to save.
     * @return the persisted entity.
     */
    public Task update(Task task) {
        log.debug("Request to update Task : {}", task);
        return taskRepository.save(task);
    }

    /**
     * Partially update a task.
     *
     * @param task the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Task> partialUpdate(Task task) {
        log.debug("Request to partially update Task : {}", task);

        return taskRepository
            .findById(task.getId())
            .map(existingTask -> {
                if (task.getSubject() != null) {
                    existingTask.setSubject(task.getSubject());
                }
                if (task.getTaskTitle() != null) {
                    existingTask.setTaskTitle(task.getTaskTitle());
                }
                if (task.getDealLine() != null) {
                    existingTask.setDealLine(task.getDealLine());
                }
                if (task.getIsUrgent() != null) {
                    existingTask.setIsUrgent(task.getIsUrgent());
                }
                if (task.getDateCreation() != null) {
                    existingTask.setDateCreation(task.getDateCreation());
                }
                if (task.getDateModify() != null) {
                    existingTask.setDateModify(task.getDateModify());
                }
                if (task.getLastModifyBy() != null) {
                    existingTask.setLastModifyBy(task.getLastModifyBy());
                }
                if (task.getCreatedBy() != null) {
                    existingTask.setCreatedBy(task.getCreatedBy());
                }
                if (task.getNotes() != null) {
                    existingTask.setNotes(task.getNotes());
                }

                return existingTask;
            })
            .map(taskRepository::save);
    }

    /**
     * Get all the tasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        log.debug("Request to get all Tasks");
        return taskRepository.findAll();
    }

    /**
     * Get all the tasks with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Task> findAllWithEagerRelationships(Pageable pageable) {
        return taskRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one task by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Task> findOne(Long id) {
        log.debug("Request to get Task : {}", id);
        return taskRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the task by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.deleteById(id);
    }
}
