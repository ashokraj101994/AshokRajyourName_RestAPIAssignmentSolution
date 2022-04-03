package com.greatlearning.employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employee.entiy.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
