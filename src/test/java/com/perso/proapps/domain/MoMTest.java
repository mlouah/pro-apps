package com.perso.proapps.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.perso.proapps.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MoMTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MoM.class);
        MoM moM1 = new MoM();
        moM1.setId(1L);
        MoM moM2 = new MoM();
        moM2.setId(moM1.getId());
        assertThat(moM1).isEqualTo(moM2);
        moM2.setId(2L);
        assertThat(moM1).isNotEqualTo(moM2);
        moM1.setId(null);
        assertThat(moM1).isNotEqualTo(moM2);
    }
}
