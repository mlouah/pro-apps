package com.perso.proapps.service;

import com.perso.proapps.domain.ProjectCategory;
import com.perso.proapps.repository.ProjectCategoryRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectCategory}.
 */
@Service
@Transactional
public class ProjectCategoryService {

    private final Logger log = LoggerFactory.getLogger(ProjectCategoryService.class);

    private final ProjectCategoryRepository projectCategoryRepository;

    public ProjectCategoryService(ProjectCategoryRepository projectCategoryRepository) {
        this.projectCategoryRepository = projectCategoryRepository;
    }

    /**
     * Save a projectCategory.
     *
     * @param projectCategory the entity to save.
     * @return the persisted entity.
     */
    public ProjectCategory save(ProjectCategory projectCategory) {
        log.debug("Request to save ProjectCategory : {}", projectCategory);
        return projectCategoryRepository.save(projectCategory);
    }

    /**
     * Update a projectCategory.
     *
     * @param projectCategory the entity to save.
     * @return the persisted entity.
     */
    public ProjectCategory update(ProjectCategory projectCategory) {
        log.debug("Request to update ProjectCategory : {}", projectCategory);
        return projectCategoryRepository.save(projectCategory);
    }

    /**
     * Partially update a projectCategory.
     *
     * @param projectCategory the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProjectCategory> partialUpdate(ProjectCategory projectCategory) {
        log.debug("Request to partially update ProjectCategory : {}", projectCategory);

        return projectCategoryRepository
            .findById(projectCategory.getId())
            .map(existingProjectCategory -> {
                if (projectCategory.getCode() != null) {
                    existingProjectCategory.setCode(projectCategory.getCode());
                }
                if (projectCategory.getCategory() != null) {
                    existingProjectCategory.setCategory(projectCategory.getCategory());
                }
                if (projectCategory.getDateCreation() != null) {
                    existingProjectCategory.setDateCreation(projectCategory.getDateCreation());
                }
                if (projectCategory.getDateModify() != null) {
                    existingProjectCategory.setDateModify(projectCategory.getDateModify());
                }
                if (projectCategory.getLastModifyBy() != null) {
                    existingProjectCategory.setLastModifyBy(projectCategory.getLastModifyBy());
                }
                if (projectCategory.getCreatedBy() != null) {
                    existingProjectCategory.setCreatedBy(projectCategory.getCreatedBy());
                }
                if (projectCategory.getNotes() != null) {
                    existingProjectCategory.setNotes(projectCategory.getNotes());
                }

                return existingProjectCategory;
            })
            .map(projectCategoryRepository::save);
    }

    /**
     * Get all the projectCategories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectCategory> findAll() {
        log.debug("Request to get all ProjectCategories");
        return projectCategoryRepository.findAll();
    }

    /**
     * Get one projectCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProjectCategory> findOne(Long id) {
        log.debug("Request to get ProjectCategory : {}", id);
        return projectCategoryRepository.findById(id);
    }

    /**
     * Delete the projectCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProjectCategory : {}", id);
        projectCategoryRepository.deleteById(id);
    }
}
