package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class GitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Git.class);
        Git git1 = new Git();
        git1.setId(1L);
        Git git2 = new Git();
        git2.setId(git1.getId());
        assertThat(git1).isEqualTo(git2);
        git2.setId(2L);
        assertThat(git1).isNotEqualTo(git2);
        git1.setId(null);
        assertThat(git1).isNotEqualTo(git2);
    }
}
