package com.perso.proapps.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.perso.proapps.IntegrationTest;
import com.perso.proapps.domain.TaskStatus;
import com.perso.proapps.repository.TaskStatusRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link TaskStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskStatusResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final byte[] DEFAULT_STATUS_PICTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_STATUS_PICTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_STATUS_PICTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_STATUS_PICTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/task-statuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskStatusMockMvc;

    private TaskStatus taskStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskStatus createEntity(EntityManager em) {
        TaskStatus taskStatus = new TaskStatus()
            .code(DEFAULT_CODE)
            .status(DEFAULT_STATUS)
            .statusPicto(DEFAULT_STATUS_PICTO)
            .statusPictoContentType(DEFAULT_STATUS_PICTO_CONTENT_TYPE)
            .notes(DEFAULT_NOTES);
        return taskStatus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskStatus createUpdatedEntity(EntityManager em) {
        TaskStatus taskStatus = new TaskStatus()
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS)
            .statusPicto(UPDATED_STATUS_PICTO)
            .statusPictoContentType(UPDATED_STATUS_PICTO_CONTENT_TYPE)
            .notes(UPDATED_NOTES);
        return taskStatus;
    }

    @BeforeEach
    public void initTest() {
        taskStatus = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskStatus() throws Exception {
        int databaseSizeBeforeCreate = taskStatusRepository.findAll().size();
        // Create the TaskStatus
        restTaskStatusMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskStatus)))
            .andExpect(status().isCreated());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeCreate + 1);
        TaskStatus testTaskStatus = taskStatusList.get(taskStatusList.size() - 1);
        assertThat(testTaskStatus.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTaskStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskStatus.getStatusPicto()).isEqualTo(DEFAULT_STATUS_PICTO);
        assertThat(testTaskStatus.getStatusPictoContentType()).isEqualTo(DEFAULT_STATUS_PICTO_CONTENT_TYPE);
        assertThat(testTaskStatus.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    void createTaskStatusWithExistingId() throws Exception {
        // Create the TaskStatus with an existing ID
        taskStatus.setId(1L);

        int databaseSizeBeforeCreate = taskStatusRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskStatusMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskStatus)))
            .andExpect(status().isBadRequest());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskStatusRepository.findAll().size();
        // set the field null
        taskStatus.setCode(null);

        // Create the TaskStatus, which fails.

        restTaskStatusMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskStatus)))
            .andExpect(status().isBadRequest());

        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTaskStatuses() throws Exception {
        // Initialize the database
        taskStatusRepository.saveAndFlush(taskStatus);

        // Get all the taskStatusList
        restTaskStatusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusPictoContentType").value(hasItem(DEFAULT_STATUS_PICTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].statusPicto").value(hasItem(Base64Utils.encodeToString(DEFAULT_STATUS_PICTO))))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)));
    }

    @Test
    @Transactional
    void getTaskStatus() throws Exception {
        // Initialize the database
        taskStatusRepository.saveAndFlush(taskStatus);

        // Get the taskStatus
        restTaskStatusMockMvc
            .perform(get(ENTITY_API_URL_ID, taskStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taskStatus.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusPictoContentType").value(DEFAULT_STATUS_PICTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.statusPicto").value(Base64Utils.encodeToString(DEFAULT_STATUS_PICTO)))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES));
    }

    @Test
    @Transactional
    void getNonExistingTaskStatus() throws Exception {
        // Get the taskStatus
        restTaskStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaskStatus() throws Exception {
        // Initialize the database
        taskStatusRepository.saveAndFlush(taskStatus);

        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();

        // Update the taskStatus
        TaskStatus updatedTaskStatus = taskStatusRepository.findById(taskStatus.getId()).get();
        // Disconnect from session so that the updates on updatedTaskStatus are not directly saved in db
        em.detach(updatedTaskStatus);
        updatedTaskStatus
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS)
            .statusPicto(UPDATED_STATUS_PICTO)
            .statusPictoContentType(UPDATED_STATUS_PICTO_CONTENT_TYPE)
            .notes(UPDATED_NOTES);

        restTaskStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTaskStatus.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTaskStatus))
            )
            .andExpect(status().isOk());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
        TaskStatus testTaskStatus = taskStatusList.get(taskStatusList.size() - 1);
        assertThat(testTaskStatus.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaskStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskStatus.getStatusPicto()).isEqualTo(UPDATED_STATUS_PICTO);
        assertThat(testTaskStatus.getStatusPictoContentType()).isEqualTo(UPDATED_STATUS_PICTO_CONTENT_TYPE);
        assertThat(testTaskStatus.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    void putNonExistingTaskStatus() throws Exception {
        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();
        taskStatus.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskStatus.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskStatus() throws Exception {
        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();
        taskStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskStatus() throws Exception {
        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();
        taskStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskStatusMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskStatus)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskStatusWithPatch() throws Exception {
        // Initialize the database
        taskStatusRepository.saveAndFlush(taskStatus);

        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();

        // Update the taskStatus using partial update
        TaskStatus partialUpdatedTaskStatus = new TaskStatus();
        partialUpdatedTaskStatus.setId(taskStatus.getId());

        restTaskStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskStatus))
            )
            .andExpect(status().isOk());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
        TaskStatus testTaskStatus = taskStatusList.get(taskStatusList.size() - 1);
        assertThat(testTaskStatus.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTaskStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaskStatus.getStatusPicto()).isEqualTo(DEFAULT_STATUS_PICTO);
        assertThat(testTaskStatus.getStatusPictoContentType()).isEqualTo(DEFAULT_STATUS_PICTO_CONTENT_TYPE);
        assertThat(testTaskStatus.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    void fullUpdateTaskStatusWithPatch() throws Exception {
        // Initialize the database
        taskStatusRepository.saveAndFlush(taskStatus);

        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();

        // Update the taskStatus using partial update
        TaskStatus partialUpdatedTaskStatus = new TaskStatus();
        partialUpdatedTaskStatus.setId(taskStatus.getId());

        partialUpdatedTaskStatus
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS)
            .statusPicto(UPDATED_STATUS_PICTO)
            .statusPictoContentType(UPDATED_STATUS_PICTO_CONTENT_TYPE)
            .notes(UPDATED_NOTES);

        restTaskStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskStatus))
            )
            .andExpect(status().isOk());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
        TaskStatus testTaskStatus = taskStatusList.get(taskStatusList.size() - 1);
        assertThat(testTaskStatus.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaskStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaskStatus.getStatusPicto()).isEqualTo(UPDATED_STATUS_PICTO);
        assertThat(testTaskStatus.getStatusPictoContentType()).isEqualTo(UPDATED_STATUS_PICTO_CONTENT_TYPE);
        assertThat(testTaskStatus.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    void patchNonExistingTaskStatus() throws Exception {
        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();
        taskStatus.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskStatus() throws Exception {
        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();
        taskStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskStatus() throws Exception {
        int databaseSizeBeforeUpdate = taskStatusRepository.findAll().size();
        taskStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskStatusMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(taskStatus))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskStatus in the database
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskStatus() throws Exception {
        // Initialize the database
        taskStatusRepository.saveAndFlush(taskStatus);

        int databaseSizeBeforeDelete = taskStatusRepository.findAll().size();

        // Delete the taskStatus
        restTaskStatusMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskStatus.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskStatus> taskStatusList = taskStatusRepository.findAll();
        assertThat(taskStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
