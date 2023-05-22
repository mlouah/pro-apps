package com.perso.proapps.service;

import com.perso.proapps.domain.ProjectStatus;
import com.perso.proapps.repository.ProjectStatusRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectStatus}.
 */
@Service
@Transactional
public class ProjectStatusService {

    private final Logger log = LoggerFactory.getLogger(ProjectStatusService.class);

    private final ProjectStatusRepository projectStatusRepository;

    public ProjectStatusService(ProjectStatusRepository projectStatusRepository) {
        this.projectStatusRepository = projectStatusRepository;
    }

    /**
     * Save a projectStatus.
     *
     * @param projectStatus the entity to save.
     * @return the persisted entity.
     */
    public ProjectStatus save(ProjectStatus projectStatus) {
        log.debug("Request to save ProjectStatus : {}", projectStatus);
        return projectStatusRepository.save(projectStatus);
    }

    /**
     * Update a projectStatus.
     *
     * @param projectStatus the entity to save.
     * @return the persisted entity.
     */
    public ProjectStatus update(ProjectStatus projectStatus) {
        log.debug("Request to update ProjectStatus : {}", projectStatus);
        return projectStatusRepository.save(projectStatus);
    }

    /**
     * Partially update a projectStatus.
     *
     * @param projectStatus the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProjectStatus> partialUpdate(ProjectStatus projectStatus) {
        log.debug("Request to partially update ProjectStatus : {}", projectStatus);

        return projectStatusRepository
            .findById(projectStatus.getId())
            .map(existingProjectStatus -> {
                if (projectStatus.getStatusDate() != null) {
                    existingProjectStatus.setStatusDate(projectStatus.getStatusDate());
                }
                if (projectStatus.getStatusTitle() != null) {
                    existingProjectStatus.setStatusTitle(projectStatus.getStatusTitle());
                }
                if (projectStatus.getUpdatedProgress() != null) {
                    existingProjectStatus.setUpdatedProgress(projectStatus.getUpdatedProgress());
                }
                if (projectStatus.getUpdatedWorkloadEAC() != null) {
                    existingProjectStatus.setUpdatedWorkloadEAC(projectStatus.getUpdatedWorkloadEAC());
                }
                if (projectStatus.getUpdatedEndDate() != null) {
                    existingProjectStatus.setUpdatedEndDate(projectStatus.getUpdatedEndDate());
                }
                if (projectStatus.getUpdatedCost() != null) {
                    existingProjectStatus.setUpdatedCost(projectStatus.getUpdatedCost());
                }
                if (projectStatus.getDateCreation() != null) {
                    existingProjectStatus.setDateCreation(projectStatus.getDateCreation());
                }
                if (projectStatus.getDateModify() != null) {
                    existingProjectStatus.setDateModify(projectStatus.getDateModify());
                }
                if (projectStatus.getLastModifyBy() != null) {
                    existingProjectStatus.setLastModifyBy(projectStatus.getLastModifyBy());
                }
                if (projectStatus.getCreatedBy() != null) {
                    existingProjectStatus.setCreatedBy(projectStatus.getCreatedBy());
                }
                if (projectStatus.getNotes() != null) {
                    existingProjectStatus.setNotes(projectStatus.getNotes());
                }

                return existingProjectStatus;
            })
            .map(projectStatusRepository::save);
    }

    /**
     * Get all the projectStatuses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectStatus> findAll() {
        log.debug("Request to get all ProjectStatuses");
        return projectStatusRepository.findAll();
    }

    /**
     * Get all the projectStatuses with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ProjectStatus> findAllWithEagerRelationships(Pageable pageable) {
        return projectStatusRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one projectStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProjectStatus> findOne(Long id) {
        log.debug("Request to get ProjectStatus : {}", id);
        return projectStatusRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the projectStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProjectStatus : {}", id);
        projectStatusRepository.deleteById(id);
    }
}
