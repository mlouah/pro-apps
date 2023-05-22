package com.perso.proapps.service;

import com.perso.proapps.domain.ProjectStatusCode;
import com.perso.proapps.repository.ProjectStatusCodeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectStatusCode}.
 */
@Service
@Transactional
public class ProjectStatusCodeService {

    private final Logger log = LoggerFactory.getLogger(ProjectStatusCodeService.class);

    private final ProjectStatusCodeRepository projectStatusCodeRepository;

    public ProjectStatusCodeService(ProjectStatusCodeRepository projectStatusCodeRepository) {
        this.projectStatusCodeRepository = projectStatusCodeRepository;
    }

    /**
     * Save a projectStatusCode.
     *
     * @param projectStatusCode the entity to save.
     * @return the persisted entity.
     */
    public ProjectStatusCode save(ProjectStatusCode projectStatusCode) {
        log.debug("Request to save ProjectStatusCode : {}", projectStatusCode);
        return projectStatusCodeRepository.save(projectStatusCode);
    }

    /**
     * Update a projectStatusCode.
     *
     * @param projectStatusCode the entity to save.
     * @return the persisted entity.
     */
    public ProjectStatusCode update(ProjectStatusCode projectStatusCode) {
        log.debug("Request to update ProjectStatusCode : {}", projectStatusCode);
        return projectStatusCodeRepository.save(projectStatusCode);
    }

    /**
     * Partially update a projectStatusCode.
     *
     * @param projectStatusCode the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProjectStatusCode> partialUpdate(ProjectStatusCode projectStatusCode) {
        log.debug("Request to partially update ProjectStatusCode : {}", projectStatusCode);

        return projectStatusCodeRepository
            .findById(projectStatusCode.getId())
            .map(existingProjectStatusCode -> {
                if (projectStatusCode.getCode() != null) {
                    existingProjectStatusCode.setCode(projectStatusCode.getCode());
                }
                if (projectStatusCode.getStatus() != null) {
                    existingProjectStatusCode.setStatus(projectStatusCode.getStatus());
                }
                if (projectStatusCode.getDateCreation() != null) {
                    existingProjectStatusCode.setDateCreation(projectStatusCode.getDateCreation());
                }
                if (projectStatusCode.getDateModify() != null) {
                    existingProjectStatusCode.setDateModify(projectStatusCode.getDateModify());
                }
                if (projectStatusCode.getLastModifyBy() != null) {
                    existingProjectStatusCode.setLastModifyBy(projectStatusCode.getLastModifyBy());
                }
                if (projectStatusCode.getCreatedBy() != null) {
                    existingProjectStatusCode.setCreatedBy(projectStatusCode.getCreatedBy());
                }
                if (projectStatusCode.getNotes() != null) {
                    existingProjectStatusCode.setNotes(projectStatusCode.getNotes());
                }

                return existingProjectStatusCode;
            })
            .map(projectStatusCodeRepository::save);
    }

    /**
     * Get all the projectStatusCodes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectStatusCode> findAll() {
        log.debug("Request to get all ProjectStatusCodes");
        return projectStatusCodeRepository.findAll();
    }

    /**
     * Get one projectStatusCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProjectStatusCode> findOne(Long id) {
        log.debug("Request to get ProjectStatusCode : {}", id);
        return projectStatusCodeRepository.findById(id);
    }

    /**
     * Delete the projectStatusCode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProjectStatusCode : {}", id);
        projectStatusCodeRepository.deleteById(id);
    }
}
