package com.perso.proapps.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.perso.proapps.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskStatus.class);
        TaskStatus taskStatus1 = new TaskStatus();
        taskStatus1.setId(1L);
        TaskStatus taskStatus2 = new TaskStatus();
        taskStatus2.setId(taskStatus1.getId());
        assertThat(taskStatus1).isEqualTo(taskStatus2);
        taskStatus2.setId(2L);
        assertThat(taskStatus1).isNotEqualTo(taskStatus2);
        taskStatus1.setId(null);
        assertThat(taskStatus1).isNotEqualTo(taskStatus2);
    }
}
