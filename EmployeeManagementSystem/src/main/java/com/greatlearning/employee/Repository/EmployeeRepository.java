package com.greatlearning.employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employee.entiy.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
