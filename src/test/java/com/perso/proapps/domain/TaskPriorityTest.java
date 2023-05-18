package com.perso.proapps.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.perso.proapps.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskPriorityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskPriority.class);
        TaskPriority taskPriority1 = new TaskPriority();
        taskPriority1.setId(1L);
        TaskPriority taskPriority2 = new TaskPriority();
        taskPriority2.setId(taskPriority1.getId());
        assertThat(taskPriority1).isEqualTo(taskPriority2);
        taskPriority2.setId(2L);
        assertThat(taskPriority1).isNotEqualTo(taskPriority2);
        taskPriority1.setId(null);
        assertThat(taskPriority1).isNotEqualTo(taskPriority2);
    }
}
