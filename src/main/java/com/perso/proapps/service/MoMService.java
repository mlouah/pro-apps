package com.perso.proapps.service;

import com.perso.proapps.domain.MoM;
import com.perso.proapps.repository.MoMRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MoM}.
 */
@Service
@Transactional
public class MoMService {

    private final Logger log = LoggerFactory.getLogger(MoMService.class);

    private final MoMRepository moMRepository;

    public MoMService(MoMRepository moMRepository) {
        this.moMRepository = moMRepository;
    }

    /**
     * Save a moM.
     *
     * @param moM the entity to save.
     * @return the persisted entity.
     */
    public MoM save(MoM moM) {
        log.debug("Request to save MoM : {}", moM);
        return moMRepository.save(moM);
    }

    /**
     * Update a moM.
     *
     * @param moM the entity to save.
     * @return the persisted entity.
     */
    public MoM update(MoM moM) {
        log.debug("Request to update MoM : {}", moM);
        return moMRepository.save(moM);
    }

    /**
     * Partially update a moM.
     *
     * @param moM the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MoM> partialUpdate(MoM moM) {
        log.debug("Request to partially update MoM : {}", moM);

        return moMRepository
            .findById(moM.getId())
            .map(existingMoM -> {
                if (moM.getTitle() != null) {
                    existingMoM.setTitle(moM.getTitle());
                }
                if (moM.getMeetingDate() != null) {
                    existingMoM.setMeetingDate(moM.getMeetingDate());
                }
                if (moM.getMinutesWritingDate() != null) {
                    existingMoM.setMinutesWritingDate(moM.getMinutesWritingDate());
                }
                if (moM.getMeetingObjectif() != null) {
                    existingMoM.setMeetingObjectif(moM.getMeetingObjectif());
                }
                if (moM.getMeetingDocReferences() != null) {
                    existingMoM.setMeetingDocReferences(moM.getMeetingDocReferences());
                }
                if (moM.getIsMoMActionsClosed() != null) {
                    existingMoM.setIsMoMActionsClosed(moM.getIsMoMActionsClosed());
                }
                if (moM.getSubjectDecisions() != null) {
                    existingMoM.setSubjectDecisions(moM.getSubjectDecisions());
                }
                if (moM.getMeetingPhotoCapture() != null) {
                    existingMoM.setMeetingPhotoCapture(moM.getMeetingPhotoCapture());
                }
                if (moM.getMeetingPhotoCaptureContentType() != null) {
                    existingMoM.setMeetingPhotoCaptureContentType(moM.getMeetingPhotoCaptureContentType());
                }
                if (moM.getDateCreation() != null) {
                    existingMoM.setDateCreation(moM.getDateCreation());
                }
                if (moM.getDateModify() != null) {
                    existingMoM.setDateModify(moM.getDateModify());
                }
                if (moM.getLastModifyBy() != null) {
                    existingMoM.setLastModifyBy(moM.getLastModifyBy());
                }
                if (moM.getCreatedBy() != null) {
                    existingMoM.setCreatedBy(moM.getCreatedBy());
                }
                if (moM.getAttendees() != null) {
                    existingMoM.setAttendees(moM.getAttendees());
                }
                if (moM.getNotes() != null) {
                    existingMoM.setNotes(moM.getNotes());
                }

                return existingMoM;
            })
            .map(moMRepository::save);
    }

    /**
     * Get all the moMS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MoM> findAll() {
        log.debug("Request to get all MoMS");
        return moMRepository.findAll();
    }

    /**
     * Get all the moMS with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MoM> findAllWithEagerRelationships(Pageable pageable) {
        return moMRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one moM by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MoM> findOne(Long id) {
        log.debug("Request to get MoM : {}", id);
        return moMRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the moM by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MoM : {}", id);
        moMRepository.deleteById(id);
    }
}
