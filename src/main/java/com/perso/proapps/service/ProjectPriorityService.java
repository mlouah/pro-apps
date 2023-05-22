package com.perso.proapps.service;

import com.perso.proapps.domain.ProjectPriority;
import com.perso.proapps.repository.ProjectPriorityRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectPriority}.
 */
@Service
@Transactional
public class ProjectPriorityService {

    private final Logger log = LoggerFactory.getLogger(ProjectPriorityService.class);

    private final ProjectPriorityRepository projectPriorityRepository;

    public ProjectPriorityService(ProjectPriorityRepository projectPriorityRepository) {
        this.projectPriorityRepository = projectPriorityRepository;
    }

    /**
     * Save a projectPriority.
     *
     * @param projectPriority the entity to save.
     * @return the persisted entity.
     */
    public ProjectPriority save(ProjectPriority projectPriority) {
        log.debug("Request to save ProjectPriority : {}", projectPriority);
        return projectPriorityRepository.save(projectPriority);
    }

    /**
     * Update a projectPriority.
     *
     * @param projectPriority the entity to save.
     * @return the persisted entity.
     */
    public ProjectPriority update(ProjectPriority projectPriority) {
        log.debug("Request to update ProjectPriority : {}", projectPriority);
        return projectPriorityRepository.save(projectPriority);
    }

    /**
     * Partially update a projectPriority.
     *
     * @param projectPriority the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProjectPriority> partialUpdate(ProjectPriority projectPriority) {
        log.debug("Request to partially update ProjectPriority : {}", projectPriority);

        return projectPriorityRepository
            .findById(projectPriority.getId())
            .map(existingProjectPriority -> {
                if (projectPriority.getCode() != null) {
                    existingProjectPriority.setCode(projectPriority.getCode());
                }
                if (projectPriority.getPriority() != null) {
                    existingProjectPriority.setPriority(projectPriority.getPriority());
                }
                if (projectPriority.getDateCreation() != null) {
                    existingProjectPriority.setDateCreation(projectPriority.getDateCreation());
                }
                if (projectPriority.getDateModify() != null) {
                    existingProjectPriority.setDateModify(projectPriority.getDateModify());
                }
                if (projectPriority.getLastModifyBy() != null) {
                    existingProjectPriority.setLastModifyBy(projectPriority.getLastModifyBy());
                }
                if (projectPriority.getCreatedBy() != null) {
                    existingProjectPriority.setCreatedBy(projectPriority.getCreatedBy());
                }
                if (projectPriority.getNotes() != null) {
                    existingProjectPriority.setNotes(projectPriority.getNotes());
                }

                return existingProjectPriority;
            })
            .map(projectPriorityRepository::save);
    }

    /**
     * Get all the projectPriorities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectPriority> findAll() {
        log.debug("Request to get all ProjectPriorities");
        return projectPriorityRepository.findAll();
    }

    /**
     * Get one projectPriority by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProjectPriority> findOne(Long id) {
        log.debug("Request to get ProjectPriority : {}", id);
        return projectPriorityRepository.findById(id);
    }

    /**
     * Delete the projectPriority by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProjectPriority : {}", id);
        projectPriorityRepository.deleteById(id);
    }
}
