package com.perso.proapps.web.rest;

import com.perso.proapps.domain.MoM;
import com.perso.proapps.repository.MoMRepository;
import com.perso.proapps.service.MoMService;
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
 * REST controller for managing {@link com.perso.proapps.domain.MoM}.
 */
@RestController
@RequestMapping("/api")
public class MoMResource {

    private final Logger log = LoggerFactory.getLogger(MoMResource.class);

    private static final String ENTITY_NAME = "moM";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MoMService moMService;

    private final MoMRepository moMRepository;

    public MoMResource(MoMService moMService, MoMRepository moMRepository) {
        this.moMService = moMService;
        this.moMRepository = moMRepository;
    }

    /**
     * {@code POST  /mo-ms} : Create a new moM.
     *
     * @param moM the moM to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new moM, or with status {@code 400 (Bad Request)} if the moM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mo-ms")
    public ResponseEntity<MoM> createMoM(@Valid @RequestBody MoM moM) throws URISyntaxException {
        log.debug("REST request to save MoM : {}", moM);
        if (moM.getId() != null) {
            throw new BadRequestAlertException("A new moM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MoM result = moMService.save(moM);
        return ResponseEntity
            .created(new URI("/api/mo-ms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mo-ms/:id} : Updates an existing moM.
     *
     * @param id the id of the moM to save.
     * @param moM the moM to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated moM,
     * or with status {@code 400 (Bad Request)} if the moM is not valid,
     * or with status {@code 500 (Internal Server Error)} if the moM couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mo-ms/{id}")
    public ResponseEntity<MoM> updateMoM(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody MoM moM)
        throws URISyntaxException {
        log.debug("REST request to update MoM : {}, {}", id, moM);
        if (moM.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, moM.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!moMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MoM result = moMService.update(moM);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, moM.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /mo-ms/:id} : Partial updates given fields of an existing moM, field will ignore if it is null
     *
     * @param id the id of the moM to save.
     * @param moM the moM to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated moM,
     * or with status {@code 400 (Bad Request)} if the moM is not valid,
     * or with status {@code 404 (Not Found)} if the moM is not found,
     * or with status {@code 500 (Internal Server Error)} if the moM couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/mo-ms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MoM> partialUpdateMoM(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody MoM moM)
        throws URISyntaxException {
        log.debug("REST request to partial update MoM partially : {}, {}", id, moM);
        if (moM.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, moM.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!moMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MoM> result = moMService.partialUpdate(moM);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, moM.getId().toString())
        );
    }

    /**
     * {@code GET  /mo-ms} : get all the moMS.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of moMS in body.
     */
    @GetMapping("/mo-ms")
    public List<MoM> getAllMoMS(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all MoMS");
        return moMService.findAll();
    }

    /**
     * {@code GET  /mo-ms/:id} : get the "id" moM.
     *
     * @param id the id of the moM to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the moM, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mo-ms/{id}")
    public ResponseEntity<MoM> getMoM(@PathVariable Long id) {
        log.debug("REST request to get MoM : {}", id);
        Optional<MoM> moM = moMService.findOne(id);
        return ResponseUtil.wrapOrNotFound(moM);
    }

    /**
     * {@code DELETE  /mo-ms/:id} : delete the "id" moM.
     *
     * @param id the id of the moM to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mo-ms/{id}")
    public ResponseEntity<Void> deleteMoM(@PathVariable Long id) {
        log.debug("REST request to delete MoM : {}", id);
        moMService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
