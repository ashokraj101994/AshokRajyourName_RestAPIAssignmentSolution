package com.greatlearning.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employee.entiy.Employee;
import com.greatlearning.employee.entiy.Role;
import com.greatlearning.employee.entiy.User;
import com.greatlearning.employee.service.EmployeeServiceImp;

@Component
@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	private EmployeeServiceImp employeeService;

	@Autowired
	public EmployeeController(EmployeeServiceImp employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public List<Employee> findAll() {
		return employeeService.employees();
	}

	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);

		employeeService.save(employee);

		return employee;
	}

	@GetMapping("/find")
	public Employee findById(@RequestParam(value = "empId") int empId) {
		Employee employee = employeeService.findById(empId);
		if (employee == null) {
			throw new RuntimeException("Employee Id " + empId + " is not founded");
		}
		return employee;
	}

	@DeleteMapping("/delete")
	public String deletebyId(@RequestParam(value = "empId") int empId) {
		employeeService.deletebyId(empId);
		return "Employee with id " + empId + " has been deleted";
	}

	@PutMapping("/update")
	public void update(@RequestBody Employee employee) {
		employeeService.save(employee);

	}

	@GetMapping("/search")
	public List<Employee> searchByFirstName(@RequestParam(value = "firstname") String firtsname) {
		List<Employee> employee = employeeService.searchByFirstName(firtsname);
		return employee;
	}

	@GetMapping("/sort")
	public List<Employee> sort() {
		List<Employee> employee = employeeService.sortByFirsNameAsc(Direction.ASC);
		return employee;

	}

	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		user.setUser_id(0);
		employeeService.saveUser(user);
		return user;
	}

	@PostMapping("/addRole")
	public Role addRole(@RequestBody Role role) {
		role.setRole_id(0);
		employeeService.saveRole(role);
		return role;
	}

}
