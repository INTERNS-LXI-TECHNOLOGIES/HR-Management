package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.LateArrival;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the LateArrival entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LateArrivalRepository extends JpaRepository<LateArrival, Long> {

    @Query("select lateArrival from LateArrival lateArrival where lateArrival.user.login = ?#{principal.username}")
    List<LateArrival> findByUserIsCurrentUser();
}
