package com.lxisoft.appraisal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.appraisal.web.rest.TestUtil;

public class UsersDataBeanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsersDataBean.class);
        UsersDataBean usersDataBean1 = new UsersDataBean();
        usersDataBean1.setId(1L);
        UsersDataBean usersDataBean2 = new UsersDataBean();
        usersDataBean2.setId(usersDataBean1.getId());
        assertThat(usersDataBean1).isEqualTo(usersDataBean2);
        usersDataBean2.setId(2L);
        assertThat(usersDataBean1).isNotEqualTo(usersDataBean2);
        usersDataBean1.setId(null);
        assertThat(usersDataBean1).isNotEqualTo(usersDataBean2);
    }
}
