package com.perso.proapps.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.perso.proapps.IntegrationTest;
import com.perso.proapps.domain.TaskPriority;
import com.perso.proapps.repository.TaskPriorityRepository;
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
 * Integration tests for the {@link TaskPriorityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskPriorityResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIORITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITY = "BBBBBBBBBB";

    private static final byte[] DEFAULT_TASK_PRIORITY_PICTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TASK_PRIORITY_PICTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TASK_PRIORITY_PICTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/task-priorities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskPriorityRepository taskPriorityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskPriorityMockMvc;

    private TaskPriority taskPriority;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskPriority createEntity(EntityManager em) {
        TaskPriority taskPriority = new TaskPriority()
            .code(DEFAULT_CODE)
            .priority(DEFAULT_PRIORITY)
            .taskPriorityPicto(DEFAULT_TASK_PRIORITY_PICTO)
            .taskPriorityPictoContentType(DEFAULT_TASK_PRIORITY_PICTO_CONTENT_TYPE)
            .notes(DEFAULT_NOTES);
        return taskPriority;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskPriority createUpdatedEntity(EntityManager em) {
        TaskPriority taskPriority = new TaskPriority()
            .code(UPDATED_CODE)
            .priority(UPDATED_PRIORITY)
            .taskPriorityPicto(UPDATED_TASK_PRIORITY_PICTO)
            .taskPriorityPictoContentType(UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE)
            .notes(UPDATED_NOTES);
        return taskPriority;
    }

    @BeforeEach
    public void initTest() {
        taskPriority = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskPriority() throws Exception {
        int databaseSizeBeforeCreate = taskPriorityRepository.findAll().size();
        // Create the TaskPriority
        restTaskPriorityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskPriority)))
            .andExpect(status().isCreated());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeCreate + 1);
        TaskPriority testTaskPriority = taskPriorityList.get(taskPriorityList.size() - 1);
        assertThat(testTaskPriority.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTaskPriority.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testTaskPriority.getTaskPriorityPicto()).isEqualTo(DEFAULT_TASK_PRIORITY_PICTO);
        assertThat(testTaskPriority.getTaskPriorityPictoContentType()).isEqualTo(DEFAULT_TASK_PRIORITY_PICTO_CONTENT_TYPE);
        assertThat(testTaskPriority.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    void createTaskPriorityWithExistingId() throws Exception {
        // Create the TaskPriority with an existing ID
        taskPriority.setId(1L);

        int databaseSizeBeforeCreate = taskPriorityRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskPriorityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskPriority)))
            .andExpect(status().isBadRequest());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskPriorityRepository.findAll().size();
        // set the field null
        taskPriority.setCode(null);

        // Create the TaskPriority, which fails.

        restTaskPriorityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskPriority)))
            .andExpect(status().isBadRequest());

        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTaskPriorities() throws Exception {
        // Initialize the database
        taskPriorityRepository.saveAndFlush(taskPriority);

        // Get all the taskPriorityList
        restTaskPriorityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskPriority.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].taskPriorityPictoContentType").value(hasItem(DEFAULT_TASK_PRIORITY_PICTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].taskPriorityPicto").value(hasItem(Base64Utils.encodeToString(DEFAULT_TASK_PRIORITY_PICTO))))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)));
    }

    @Test
    @Transactional
    void getTaskPriority() throws Exception {
        // Initialize the database
        taskPriorityRepository.saveAndFlush(taskPriority);

        // Get the taskPriority
        restTaskPriorityMockMvc
            .perform(get(ENTITY_API_URL_ID, taskPriority.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taskPriority.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.taskPriorityPictoContentType").value(DEFAULT_TASK_PRIORITY_PICTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.taskPriorityPicto").value(Base64Utils.encodeToString(DEFAULT_TASK_PRIORITY_PICTO)))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES));
    }

    @Test
    @Transactional
    void getNonExistingTaskPriority() throws Exception {
        // Get the taskPriority
        restTaskPriorityMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaskPriority() throws Exception {
        // Initialize the database
        taskPriorityRepository.saveAndFlush(taskPriority);

        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();

        // Update the taskPriority
        TaskPriority updatedTaskPriority = taskPriorityRepository.findById(taskPriority.getId()).get();
        // Disconnect from session so that the updates on updatedTaskPriority are not directly saved in db
        em.detach(updatedTaskPriority);
        updatedTaskPriority
            .code(UPDATED_CODE)
            .priority(UPDATED_PRIORITY)
            .taskPriorityPicto(UPDATED_TASK_PRIORITY_PICTO)
            .taskPriorityPictoContentType(UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE)
            .notes(UPDATED_NOTES);

        restTaskPriorityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTaskPriority.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTaskPriority))
            )
            .andExpect(status().isOk());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
        TaskPriority testTaskPriority = taskPriorityList.get(taskPriorityList.size() - 1);
        assertThat(testTaskPriority.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaskPriority.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testTaskPriority.getTaskPriorityPicto()).isEqualTo(UPDATED_TASK_PRIORITY_PICTO);
        assertThat(testTaskPriority.getTaskPriorityPictoContentType()).isEqualTo(UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE);
        assertThat(testTaskPriority.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    void putNonExistingTaskPriority() throws Exception {
        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();
        taskPriority.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskPriorityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskPriority.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskPriority))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskPriority() throws Exception {
        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();
        taskPriority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskPriorityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskPriority))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskPriority() throws Exception {
        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();
        taskPriority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskPriorityMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskPriority)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskPriorityWithPatch() throws Exception {
        // Initialize the database
        taskPriorityRepository.saveAndFlush(taskPriority);

        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();

        // Update the taskPriority using partial update
        TaskPriority partialUpdatedTaskPriority = new TaskPriority();
        partialUpdatedTaskPriority.setId(taskPriority.getId());

        partialUpdatedTaskPriority
            .code(UPDATED_CODE)
            .taskPriorityPicto(UPDATED_TASK_PRIORITY_PICTO)
            .taskPriorityPictoContentType(UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE);

        restTaskPriorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskPriority.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskPriority))
            )
            .andExpect(status().isOk());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
        TaskPriority testTaskPriority = taskPriorityList.get(taskPriorityList.size() - 1);
        assertThat(testTaskPriority.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaskPriority.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testTaskPriority.getTaskPriorityPicto()).isEqualTo(UPDATED_TASK_PRIORITY_PICTO);
        assertThat(testTaskPriority.getTaskPriorityPictoContentType()).isEqualTo(UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE);
        assertThat(testTaskPriority.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    void fullUpdateTaskPriorityWithPatch() throws Exception {
        // Initialize the database
        taskPriorityRepository.saveAndFlush(taskPriority);

        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();

        // Update the taskPriority using partial update
        TaskPriority partialUpdatedTaskPriority = new TaskPriority();
        partialUpdatedTaskPriority.setId(taskPriority.getId());

        partialUpdatedTaskPriority
            .code(UPDATED_CODE)
            .priority(UPDATED_PRIORITY)
            .taskPriorityPicto(UPDATED_TASK_PRIORITY_PICTO)
            .taskPriorityPictoContentType(UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE)
            .notes(UPDATED_NOTES);

        restTaskPriorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskPriority.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskPriority))
            )
            .andExpect(status().isOk());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
        TaskPriority testTaskPriority = taskPriorityList.get(taskPriorityList.size() - 1);
        assertThat(testTaskPriority.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaskPriority.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testTaskPriority.getTaskPriorityPicto()).isEqualTo(UPDATED_TASK_PRIORITY_PICTO);
        assertThat(testTaskPriority.getTaskPriorityPictoContentType()).isEqualTo(UPDATED_TASK_PRIORITY_PICTO_CONTENT_TYPE);
        assertThat(testTaskPriority.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    void patchNonExistingTaskPriority() throws Exception {
        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();
        taskPriority.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskPriorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskPriority.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskPriority))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskPriority() throws Exception {
        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();
        taskPriority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskPriorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskPriority))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskPriority() throws Exception {
        int databaseSizeBeforeUpdate = taskPriorityRepository.findAll().size();
        taskPriority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskPriorityMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(taskPriority))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskPriority in the database
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskPriority() throws Exception {
        // Initialize the database
        taskPriorityRepository.saveAndFlush(taskPriority);

        int databaseSizeBeforeDelete = taskPriorityRepository.findAll().size();

        // Delete the taskPriority
        restTaskPriorityMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskPriority.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskPriority> taskPriorityList = taskPriorityRepository.findAll();
        assertThat(taskPriorityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
