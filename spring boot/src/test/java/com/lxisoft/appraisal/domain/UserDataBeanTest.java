package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class UserDataBeanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserDataBean.class);
        UserDataBean userDataBean1 = new UserDataBean();
        userDataBean1.setId(1L);
        UserDataBean userDataBean2 = new UserDataBean();
        userDataBean2.setId(userDataBean1.getId());
        assertThat(userDataBean1).isEqualTo(userDataBean2);
        userDataBean2.setId(2L);
        assertThat(userDataBean1).isNotEqualTo(userDataBean2);
        userDataBean1.setId(null);
        assertThat(userDataBean1).isNotEqualTo(userDataBean2);
    }
}
