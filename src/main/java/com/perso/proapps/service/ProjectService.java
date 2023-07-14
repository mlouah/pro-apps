package com.perso.proapps.service;

import com.perso.proapps.domain.Project;
import com.perso.proapps.repository.ProjectRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Project}.
 */
@Service
@Transactional
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Save a project.
     *
     * @param project the entity to save.
     * @return the persisted entity.
     */
    public Project save(Project project) {
        log.debug("Request to save Project : {}", project);
        return projectRepository.save(project);
    }

    /**
     * Update a project.
     *
     * @param project the entity to save.
     * @return the persisted entity.
     */
    public Project update(Project project) {
        log.debug("Request to update Project : {}", project);
        return projectRepository.save(project);
    }

    /**
     * Partially update a project.
     *
     * @param project the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Project> partialUpdate(Project project) {
        log.debug("Request to partially update Project : {}", project);

        return projectRepository
            .findById(project.getId())
            .map(existingProject -> {
                if (project.getName() != null) {
                    existingProject.setName(project.getName());
                }
                if (project.getCode() != null) {
                    existingProject.setCode(project.getCode());
                }
                if (project.getStartDate() != null) {
                    existingProject.setStartDate(project.getStartDate());
                }
                if (project.getRealEndtDate() != null) {
                    existingProject.setRealEndtDate(project.getRealEndtDate());
                }
                if (project.getProjectDescription() != null) {
                    existingProject.setProjectDescription(project.getProjectDescription());
                }
                if (project.getInitialEndDate() != null) {
                    existingProject.setInitialEndDate(project.getInitialEndDate());
                }
                if (project.getInitialCost() != null) {
                    existingProject.setInitialCost(project.getInitialCost());
                }
                if (project.getInitialWorkLoad() != null) {
                    existingProject.setInitialWorkLoad(project.getInitialWorkLoad());
                }
                if (project.getDateCreation() != null) {
                    existingProject.setDateCreation(project.getDateCreation());
                }
                if (project.getDateModify() != null) {
                    existingProject.setDateModify(project.getDateModify());
                }
                if (project.getLastModifyBy() != null) {
                    existingProject.setLastModifyBy(project.getLastModifyBy());
                }
                if (project.getCreatedBy() != null) {
                    existingProject.setCreatedBy(project.getCreatedBy());
                }
                if (project.getNotes() != null) {
                    existingProject.setNotes(project.getNotes());
                }
                if (project.getObjectives() != null) {
                    existingProject.setObjectives(project.getObjectives());
                }
                if (project.getTodo() != null) {
                    existingProject.setTodo(project.getTodo());
                }
                if (project.getProgress() != null) {
                    existingProject.setProgress(project.getProgress());
                }

                return existingProject;
            })
            .map(projectRepository::save);
    }

    /**
     * Get all the projects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        log.debug("Request to get all Projects");
        return projectRepository.findAll();
    }

    /**
     * Get all the projects with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Project> findAllWithEagerRelationships(Pageable pageable) {
        return projectRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one project by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Project> findOne(Long id) {
        log.debug("Request to get Project : {}", id);
        return projectRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the project by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.deleteById(id);
    }
}
