package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.UserExtra;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Git entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GitRepository extends JpaRepository<Git, Long> {
	List<Git> findByUserExtraId(Long userex);
	List<Git> findByUserExtraAndDateBetween(UserExtra userEx,LocalDate second ,LocalDate first);
	List<Git> findAllByDate(LocalDate localDate);
}
