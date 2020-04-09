package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class AppraisalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Appraisal.class);
        Appraisal appraisal1 = new Appraisal();
        appraisal1.setId(1L);
        Appraisal appraisal2 = new Appraisal();
        appraisal2.setId(appraisal1.getId());
        assertThat(appraisal1).isEqualTo(appraisal2);
        appraisal2.setId(2L);
        assertThat(appraisal1).isNotEqualTo(appraisal2);
        appraisal1.setId(null);
        assertThat(appraisal1).isNotEqualTo(appraisal2);
    }
}
