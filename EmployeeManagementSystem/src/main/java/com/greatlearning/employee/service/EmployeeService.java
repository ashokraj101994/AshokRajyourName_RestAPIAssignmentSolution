package com.greatlearning.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.employee.entiy.Employee;
import com.greatlearning.employee.entiy.Role;
import com.greatlearning.employee.entiy.User;

@Service
public interface EmployeeService {
	public List<Employee> employees();

	public void save(Employee employee);

	public Employee findById(int empId);

	public void deletebyId(int empId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirsNameAsc(Direction direcion);

	public void saveUser(User user);

	public void saveRole(Role role);

}
