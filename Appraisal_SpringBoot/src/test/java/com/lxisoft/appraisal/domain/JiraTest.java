package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class JiraTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Jira.class);
        Jira jira1 = new Jira();
        jira1.setId(1L);
        Jira jira2 = new Jira();
        jira2.setId(jira1.getId());
        assertThat(jira1).isEqualTo(jira2);
        jira2.setId(2L);
        assertThat(jira1).isNotEqualTo(jira2);
        jira1.setId(null);
        assertThat(jira1).isNotEqualTo(jira2);
    }
}
