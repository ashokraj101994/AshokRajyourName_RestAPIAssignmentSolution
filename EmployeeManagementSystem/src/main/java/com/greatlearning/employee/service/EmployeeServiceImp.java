package com.greatlearning.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.employee.Repository.EmployeeRepository;
import com.greatlearning.employee.Repository.RoleRepository;
import com.greatlearning.employee.Repository.UserRepository;
import com.greatlearning.employee.entiy.Employee;
import com.greatlearning.employee.entiy.Role;
import com.greatlearning.employee.entiy.User;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Employee> employees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);
	}

	@Override
	public Employee findById(int empId) {
		Optional<Employee> employee = employeeRepository.findById(empId);
		Employee employees;
		if (employee.isPresent()) {
			employees = employee.get();
		} else {
			throw new RuntimeException("Employee of Id " + empId + " is not founded");
		}
		return employees;
	}

	@Override
	public void deletebyId(int empId) {
		Optional<Employee> employee = employeeRepository.findById(empId);
		if (employee.isPresent()) {
			employeeRepository.deleteById(empId);
		} else {
			throw new RuntimeException("Employee of Id " + empId + " is not founded");
		}

	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		Employee employee = new Employee();
		employee.setFirstname(firstName);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
				.withIgnorePaths("id", "lastname", "email");
		Example<Employee> example = Example.of(employee, exampleMatcher);

		return employeeRepository.findAll(example);

	}

	@Override
	public List<Employee> sortByFirsNameAsc(Direction direction) {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findAll(Sort.by(direction, "firstname"));

		return employees;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
	}

}
