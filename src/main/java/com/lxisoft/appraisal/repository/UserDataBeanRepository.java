package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.UserDataBean;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserDataBean entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserDataBeanRepository extends JpaRepository<UserDataBean, Long> {
}
