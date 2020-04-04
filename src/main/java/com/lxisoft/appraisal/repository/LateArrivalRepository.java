package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.LateArrival;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LateArrival entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LateArrivalRepository extends JpaRepository<LateArrival, Long> {
	public	List<LateArrival> findByUserExtraId(Long user);
}
