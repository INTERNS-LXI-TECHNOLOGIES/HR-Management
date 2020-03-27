package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class LateArrivalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LateArrival.class);
        LateArrival lateArrival1 = new LateArrival();
        lateArrival1.setId(1L);
        LateArrival lateArrival2 = new LateArrival();
        lateArrival2.setId(lateArrival1.getId());
        assertThat(lateArrival1).isEqualTo(lateArrival2);
        lateArrival2.setId(2L);
        assertThat(lateArrival1).isNotEqualTo(lateArrival2);
        lateArrival1.setId(null);
        assertThat(lateArrival1).isNotEqualTo(lateArrival2);
    }
}
