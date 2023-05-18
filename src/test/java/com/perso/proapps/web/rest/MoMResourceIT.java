package com.perso.proapps.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.perso.proapps.IntegrationTest;
import com.perso.proapps.domain.MoM;
import com.perso.proapps.repository.MoMRepository;
import com.perso.proapps.service.MoMService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link MoMResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class MoMResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MEETING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MEETING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DOC_WRITING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOC_WRITING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DEAD_LINE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEAD_LINE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MEETING_OBJECTIF = "AAAAAAAAAA";
    private static final String UPDATED_MEETING_OBJECTIF = "BBBBBBBBBB";

    private static final String DEFAULT_MEETING_DOC_REFERENCES = "AAAAAAAAAA";
    private static final String UPDATED_MEETING_DOC_REFERENCES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MO_M_ACTIONS_CLOSED = false;
    private static final Boolean UPDATED_IS_MO_M_ACTIONS_CLOSED = true;

    private static final String DEFAULT_DECISIONS = "AAAAAAAAAA";
    private static final String UPDATED_DECISIONS = "BBBBBBBBBB";

    private static final String DEFAULT_ATTENDEES = "AAAAAAAAAA";
    private static final String UPDATED_ATTENDEES = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_MODIFY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_MODIFY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFY_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFY_BY = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final byte[] DEFAULT_MEETING_PHOTO_CAPTURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_MEETING_PHOTO_CAPTURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_MEETING_PHOTO_CAPTURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/mo-ms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MoMRepository moMRepository;

    @Mock
    private MoMRepository moMRepositoryMock;

    @Mock
    private MoMService moMServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMoMMockMvc;

    private MoM moM;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MoM createEntity(EntityManager em) {
        MoM moM = new MoM()
            .title(DEFAULT_TITLE)
            .meetingDate(DEFAULT_MEETING_DATE)
            .docWritingDate(DEFAULT_DOC_WRITING_DATE)
            .deadLine(DEFAULT_DEAD_LINE)
            .meetingObjectif(DEFAULT_MEETING_OBJECTIF)
            .meetingDocReferences(DEFAULT_MEETING_DOC_REFERENCES)
            .isMoMActionsClosed(DEFAULT_IS_MO_M_ACTIONS_CLOSED)
            .decisions(DEFAULT_DECISIONS)
            .attendees(DEFAULT_ATTENDEES)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateModify(DEFAULT_DATE_MODIFY)
            .lastModifyBy(DEFAULT_LAST_MODIFY_BY)
            .createdBy(DEFAULT_CREATED_BY)
            .meetingPhotoCapture(DEFAULT_MEETING_PHOTO_CAPTURE)
            .meetingPhotoCaptureContentType(DEFAULT_MEETING_PHOTO_CAPTURE_CONTENT_TYPE)
            .notes(DEFAULT_NOTES);
        return moM;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MoM createUpdatedEntity(EntityManager em) {
        MoM moM = new MoM()
            .title(UPDATED_TITLE)
            .meetingDate(UPDATED_MEETING_DATE)
            .docWritingDate(UPDATED_DOC_WRITING_DATE)
            .deadLine(UPDATED_DEAD_LINE)
            .meetingObjectif(UPDATED_MEETING_OBJECTIF)
            .meetingDocReferences(UPDATED_MEETING_DOC_REFERENCES)
            .isMoMActionsClosed(UPDATED_IS_MO_M_ACTIONS_CLOSED)
            .decisions(UPDATED_DECISIONS)
            .attendees(UPDATED_ATTENDEES)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModify(UPDATED_DATE_MODIFY)
            .lastModifyBy(UPDATED_LAST_MODIFY_BY)
            .createdBy(UPDATED_CREATED_BY)
            .meetingPhotoCapture(UPDATED_MEETING_PHOTO_CAPTURE)
            .meetingPhotoCaptureContentType(UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE)
            .notes(UPDATED_NOTES);
        return moM;
    }

    @BeforeEach
    public void initTest() {
        moM = createEntity(em);
    }

    @Test
    @Transactional
    void createMoM() throws Exception {
        int databaseSizeBeforeCreate = moMRepository.findAll().size();
        // Create the MoM
        restMoMMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(moM)))
            .andExpect(status().isCreated());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeCreate + 1);
        MoM testMoM = moMList.get(moMList.size() - 1);
        assertThat(testMoM.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMoM.getMeetingDate()).isEqualTo(DEFAULT_MEETING_DATE);
        assertThat(testMoM.getDocWritingDate()).isEqualTo(DEFAULT_DOC_WRITING_DATE);
        assertThat(testMoM.getDeadLine()).isEqualTo(DEFAULT_DEAD_LINE);
        assertThat(testMoM.getMeetingObjectif()).isEqualTo(DEFAULT_MEETING_OBJECTIF);
        assertThat(testMoM.getMeetingDocReferences()).isEqualTo(DEFAULT_MEETING_DOC_REFERENCES);
        assertThat(testMoM.getIsMoMActionsClosed()).isEqualTo(DEFAULT_IS_MO_M_ACTIONS_CLOSED);
        assertThat(testMoM.getDecisions()).isEqualTo(DEFAULT_DECISIONS);
        assertThat(testMoM.getAttendees()).isEqualTo(DEFAULT_ATTENDEES);
        assertThat(testMoM.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testMoM.getDateModify()).isEqualTo(DEFAULT_DATE_MODIFY);
        assertThat(testMoM.getLastModifyBy()).isEqualTo(DEFAULT_LAST_MODIFY_BY);
        assertThat(testMoM.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMoM.getMeetingPhotoCapture()).isEqualTo(DEFAULT_MEETING_PHOTO_CAPTURE);
        assertThat(testMoM.getMeetingPhotoCaptureContentType()).isEqualTo(DEFAULT_MEETING_PHOTO_CAPTURE_CONTENT_TYPE);
        assertThat(testMoM.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    void createMoMWithExistingId() throws Exception {
        // Create the MoM with an existing ID
        moM.setId(1L);

        int databaseSizeBeforeCreate = moMRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMoMMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(moM)))
            .andExpect(status().isBadRequest());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = moMRepository.findAll().size();
        // set the field null
        moM.setTitle(null);

        // Create the MoM, which fails.

        restMoMMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(moM)))
            .andExpect(status().isBadRequest());

        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMoMS() throws Exception {
        // Initialize the database
        moMRepository.saveAndFlush(moM);

        // Get all the moMList
        restMoMMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(moM.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].meetingDate").value(hasItem(DEFAULT_MEETING_DATE.toString())))
            .andExpect(jsonPath("$.[*].docWritingDate").value(hasItem(DEFAULT_DOC_WRITING_DATE.toString())))
            .andExpect(jsonPath("$.[*].deadLine").value(hasItem(DEFAULT_DEAD_LINE.toString())))
            .andExpect(jsonPath("$.[*].meetingObjectif").value(hasItem(DEFAULT_MEETING_OBJECTIF.toString())))
            .andExpect(jsonPath("$.[*].meetingDocReferences").value(hasItem(DEFAULT_MEETING_DOC_REFERENCES.toString())))
            .andExpect(jsonPath("$.[*].isMoMActionsClosed").value(hasItem(DEFAULT_IS_MO_M_ACTIONS_CLOSED.booleanValue())))
            .andExpect(jsonPath("$.[*].decisions").value(hasItem(DEFAULT_DECISIONS.toString())))
            .andExpect(jsonPath("$.[*].attendees").value(hasItem(DEFAULT_ATTENDEES.toString())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].dateModify").value(hasItem(DEFAULT_DATE_MODIFY.toString())))
            .andExpect(jsonPath("$.[*].lastModifyBy").value(hasItem(DEFAULT_LAST_MODIFY_BY)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].meetingPhotoCaptureContentType").value(hasItem(DEFAULT_MEETING_PHOTO_CAPTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].meetingPhotoCapture").value(hasItem(Base64Utils.encodeToString(DEFAULT_MEETING_PHOTO_CAPTURE))))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMoMSWithEagerRelationshipsIsEnabled() throws Exception {
        when(moMServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMoMMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(moMServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMoMSWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(moMServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMoMMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(moMRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getMoM() throws Exception {
        // Initialize the database
        moMRepository.saveAndFlush(moM);

        // Get the moM
        restMoMMockMvc
            .perform(get(ENTITY_API_URL_ID, moM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(moM.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.meetingDate").value(DEFAULT_MEETING_DATE.toString()))
            .andExpect(jsonPath("$.docWritingDate").value(DEFAULT_DOC_WRITING_DATE.toString()))
            .andExpect(jsonPath("$.deadLine").value(DEFAULT_DEAD_LINE.toString()))
            .andExpect(jsonPath("$.meetingObjectif").value(DEFAULT_MEETING_OBJECTIF.toString()))
            .andExpect(jsonPath("$.meetingDocReferences").value(DEFAULT_MEETING_DOC_REFERENCES.toString()))
            .andExpect(jsonPath("$.isMoMActionsClosed").value(DEFAULT_IS_MO_M_ACTIONS_CLOSED.booleanValue()))
            .andExpect(jsonPath("$.decisions").value(DEFAULT_DECISIONS.toString()))
            .andExpect(jsonPath("$.attendees").value(DEFAULT_ATTENDEES.toString()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.dateModify").value(DEFAULT_DATE_MODIFY.toString()))
            .andExpect(jsonPath("$.lastModifyBy").value(DEFAULT_LAST_MODIFY_BY))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.meetingPhotoCaptureContentType").value(DEFAULT_MEETING_PHOTO_CAPTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.meetingPhotoCapture").value(Base64Utils.encodeToString(DEFAULT_MEETING_PHOTO_CAPTURE)))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMoM() throws Exception {
        // Get the moM
        restMoMMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMoM() throws Exception {
        // Initialize the database
        moMRepository.saveAndFlush(moM);

        int databaseSizeBeforeUpdate = moMRepository.findAll().size();

        // Update the moM
        MoM updatedMoM = moMRepository.findById(moM.getId()).get();
        // Disconnect from session so that the updates on updatedMoM are not directly saved in db
        em.detach(updatedMoM);
        updatedMoM
            .title(UPDATED_TITLE)
            .meetingDate(UPDATED_MEETING_DATE)
            .docWritingDate(UPDATED_DOC_WRITING_DATE)
            .deadLine(UPDATED_DEAD_LINE)
            .meetingObjectif(UPDATED_MEETING_OBJECTIF)
            .meetingDocReferences(UPDATED_MEETING_DOC_REFERENCES)
            .isMoMActionsClosed(UPDATED_IS_MO_M_ACTIONS_CLOSED)
            .decisions(UPDATED_DECISIONS)
            .attendees(UPDATED_ATTENDEES)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModify(UPDATED_DATE_MODIFY)
            .lastModifyBy(UPDATED_LAST_MODIFY_BY)
            .createdBy(UPDATED_CREATED_BY)
            .meetingPhotoCapture(UPDATED_MEETING_PHOTO_CAPTURE)
            .meetingPhotoCaptureContentType(UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE)
            .notes(UPDATED_NOTES);

        restMoMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMoM.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMoM))
            )
            .andExpect(status().isOk());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
        MoM testMoM = moMList.get(moMList.size() - 1);
        assertThat(testMoM.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMoM.getMeetingDate()).isEqualTo(UPDATED_MEETING_DATE);
        assertThat(testMoM.getDocWritingDate()).isEqualTo(UPDATED_DOC_WRITING_DATE);
        assertThat(testMoM.getDeadLine()).isEqualTo(UPDATED_DEAD_LINE);
        assertThat(testMoM.getMeetingObjectif()).isEqualTo(UPDATED_MEETING_OBJECTIF);
        assertThat(testMoM.getMeetingDocReferences()).isEqualTo(UPDATED_MEETING_DOC_REFERENCES);
        assertThat(testMoM.getIsMoMActionsClosed()).isEqualTo(UPDATED_IS_MO_M_ACTIONS_CLOSED);
        assertThat(testMoM.getDecisions()).isEqualTo(UPDATED_DECISIONS);
        assertThat(testMoM.getAttendees()).isEqualTo(UPDATED_ATTENDEES);
        assertThat(testMoM.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testMoM.getDateModify()).isEqualTo(UPDATED_DATE_MODIFY);
        assertThat(testMoM.getLastModifyBy()).isEqualTo(UPDATED_LAST_MODIFY_BY);
        assertThat(testMoM.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMoM.getMeetingPhotoCapture()).isEqualTo(UPDATED_MEETING_PHOTO_CAPTURE);
        assertThat(testMoM.getMeetingPhotoCaptureContentType()).isEqualTo(UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE);
        assertThat(testMoM.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    void putNonExistingMoM() throws Exception {
        int databaseSizeBeforeUpdate = moMRepository.findAll().size();
        moM.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMoMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, moM.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(moM))
            )
            .andExpect(status().isBadRequest());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMoM() throws Exception {
        int databaseSizeBeforeUpdate = moMRepository.findAll().size();
        moM.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMoMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(moM))
            )
            .andExpect(status().isBadRequest());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMoM() throws Exception {
        int databaseSizeBeforeUpdate = moMRepository.findAll().size();
        moM.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMoMMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(moM)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMoMWithPatch() throws Exception {
        // Initialize the database
        moMRepository.saveAndFlush(moM);

        int databaseSizeBeforeUpdate = moMRepository.findAll().size();

        // Update the moM using partial update
        MoM partialUpdatedMoM = new MoM();
        partialUpdatedMoM.setId(moM.getId());

        partialUpdatedMoM
            .title(UPDATED_TITLE)
            .meetingDate(UPDATED_MEETING_DATE)
            .meetingObjectif(UPDATED_MEETING_OBJECTIF)
            .meetingDocReferences(UPDATED_MEETING_DOC_REFERENCES)
            .decisions(UPDATED_DECISIONS)
            .attendees(UPDATED_ATTENDEES)
            .lastModifyBy(UPDATED_LAST_MODIFY_BY)
            .meetingPhotoCapture(UPDATED_MEETING_PHOTO_CAPTURE)
            .meetingPhotoCaptureContentType(UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE);

        restMoMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMoM.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMoM))
            )
            .andExpect(status().isOk());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
        MoM testMoM = moMList.get(moMList.size() - 1);
        assertThat(testMoM.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMoM.getMeetingDate()).isEqualTo(UPDATED_MEETING_DATE);
        assertThat(testMoM.getDocWritingDate()).isEqualTo(DEFAULT_DOC_WRITING_DATE);
        assertThat(testMoM.getDeadLine()).isEqualTo(DEFAULT_DEAD_LINE);
        assertThat(testMoM.getMeetingObjectif()).isEqualTo(UPDATED_MEETING_OBJECTIF);
        assertThat(testMoM.getMeetingDocReferences()).isEqualTo(UPDATED_MEETING_DOC_REFERENCES);
        assertThat(testMoM.getIsMoMActionsClosed()).isEqualTo(DEFAULT_IS_MO_M_ACTIONS_CLOSED);
        assertThat(testMoM.getDecisions()).isEqualTo(UPDATED_DECISIONS);
        assertThat(testMoM.getAttendees()).isEqualTo(UPDATED_ATTENDEES);
        assertThat(testMoM.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testMoM.getDateModify()).isEqualTo(DEFAULT_DATE_MODIFY);
        assertThat(testMoM.getLastModifyBy()).isEqualTo(UPDATED_LAST_MODIFY_BY);
        assertThat(testMoM.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMoM.getMeetingPhotoCapture()).isEqualTo(UPDATED_MEETING_PHOTO_CAPTURE);
        assertThat(testMoM.getMeetingPhotoCaptureContentType()).isEqualTo(UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE);
        assertThat(testMoM.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    void fullUpdateMoMWithPatch() throws Exception {
        // Initialize the database
        moMRepository.saveAndFlush(moM);

        int databaseSizeBeforeUpdate = moMRepository.findAll().size();

        // Update the moM using partial update
        MoM partialUpdatedMoM = new MoM();
        partialUpdatedMoM.setId(moM.getId());

        partialUpdatedMoM
            .title(UPDATED_TITLE)
            .meetingDate(UPDATED_MEETING_DATE)
            .docWritingDate(UPDATED_DOC_WRITING_DATE)
            .deadLine(UPDATED_DEAD_LINE)
            .meetingObjectif(UPDATED_MEETING_OBJECTIF)
            .meetingDocReferences(UPDATED_MEETING_DOC_REFERENCES)
            .isMoMActionsClosed(UPDATED_IS_MO_M_ACTIONS_CLOSED)
            .decisions(UPDATED_DECISIONS)
            .attendees(UPDATED_ATTENDEES)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModify(UPDATED_DATE_MODIFY)
            .lastModifyBy(UPDATED_LAST_MODIFY_BY)
            .createdBy(UPDATED_CREATED_BY)
            .meetingPhotoCapture(UPDATED_MEETING_PHOTO_CAPTURE)
            .meetingPhotoCaptureContentType(UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE)
            .notes(UPDATED_NOTES);

        restMoMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMoM.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMoM))
            )
            .andExpect(status().isOk());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
        MoM testMoM = moMList.get(moMList.size() - 1);
        assertThat(testMoM.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMoM.getMeetingDate()).isEqualTo(UPDATED_MEETING_DATE);
        assertThat(testMoM.getDocWritingDate()).isEqualTo(UPDATED_DOC_WRITING_DATE);
        assertThat(testMoM.getDeadLine()).isEqualTo(UPDATED_DEAD_LINE);
        assertThat(testMoM.getMeetingObjectif()).isEqualTo(UPDATED_MEETING_OBJECTIF);
        assertThat(testMoM.getMeetingDocReferences()).isEqualTo(UPDATED_MEETING_DOC_REFERENCES);
        assertThat(testMoM.getIsMoMActionsClosed()).isEqualTo(UPDATED_IS_MO_M_ACTIONS_CLOSED);
        assertThat(testMoM.getDecisions()).isEqualTo(UPDATED_DECISIONS);
        assertThat(testMoM.getAttendees()).isEqualTo(UPDATED_ATTENDEES);
        assertThat(testMoM.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testMoM.getDateModify()).isEqualTo(UPDATED_DATE_MODIFY);
        assertThat(testMoM.getLastModifyBy()).isEqualTo(UPDATED_LAST_MODIFY_BY);
        assertThat(testMoM.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMoM.getMeetingPhotoCapture()).isEqualTo(UPDATED_MEETING_PHOTO_CAPTURE);
        assertThat(testMoM.getMeetingPhotoCaptureContentType()).isEqualTo(UPDATED_MEETING_PHOTO_CAPTURE_CONTENT_TYPE);
        assertThat(testMoM.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    void patchNonExistingMoM() throws Exception {
        int databaseSizeBeforeUpdate = moMRepository.findAll().size();
        moM.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMoMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, moM.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(moM))
            )
            .andExpect(status().isBadRequest());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMoM() throws Exception {
        int databaseSizeBeforeUpdate = moMRepository.findAll().size();
        moM.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMoMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(moM))
            )
            .andExpect(status().isBadRequest());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMoM() throws Exception {
        int databaseSizeBeforeUpdate = moMRepository.findAll().size();
        moM.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMoMMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(moM)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MoM in the database
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMoM() throws Exception {
        // Initialize the database
        moMRepository.saveAndFlush(moM);

        int databaseSizeBeforeDelete = moMRepository.findAll().size();

        // Delete the moM
        restMoMMockMvc.perform(delete(ENTITY_API_URL_ID, moM.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MoM> moMList = moMRepository.findAll();
        assertThat(moMList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
