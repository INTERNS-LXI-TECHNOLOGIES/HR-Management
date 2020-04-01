package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class ReportStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportStatus.class);
        ReportStatus reportStatus1 = new ReportStatus();
        reportStatus1.setId(1L);
        ReportStatus reportStatus2 = new ReportStatus();
        reportStatus2.setId(reportStatus1.getId());
        assertThat(reportStatus1).isEqualTo(reportStatus2);
        reportStatus2.setId(2L);
        assertThat(reportStatus1).isNotEqualTo(reportStatus2);
        reportStatus1.setId(null);
        assertThat(reportStatus1).isNotEqualTo(reportStatus2);
    }
}
