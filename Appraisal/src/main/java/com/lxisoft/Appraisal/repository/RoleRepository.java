package com.lxisoft.Appraisal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lxisoft.Appraisal.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
