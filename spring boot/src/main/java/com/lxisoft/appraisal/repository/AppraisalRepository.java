package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.UserExtra;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Appraisal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppraisalRepository extends JpaRepository<Appraisal, Long> {
	@Query("SELECT u FROM Appraisal u WHERE u.userExtra=?1")
	public List<Appraisal> getOneByUserExtraId(UserExtra userExtra);
}
