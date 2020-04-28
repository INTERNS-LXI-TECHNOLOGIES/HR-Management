package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.UsersDataBean;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UsersDataBean entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersDataBeanRepository extends JpaRepository<UsersDataBean, Long> {
}
