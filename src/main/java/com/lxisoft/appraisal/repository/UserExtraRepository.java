package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.UserExtra;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data  repository for the UserExtra entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional
public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {
	 Optional<UserExtra> findById(long id);
}
