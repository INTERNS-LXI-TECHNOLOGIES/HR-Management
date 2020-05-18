package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class HackathonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hackathon.class);
        Hackathon hackathon1 = new Hackathon();
        hackathon1.setId(1L);
        Hackathon hackathon2 = new Hackathon();
        hackathon2.setId(hackathon1.getId());
        assertThat(hackathon1).isEqualTo(hackathon2);
        hackathon2.setId(2L);
        assertThat(hackathon1).isNotEqualTo(hackathon2);
        hackathon1.setId(null);
        assertThat(hackathon1).isNotEqualTo(hackathon2);
    }
}
