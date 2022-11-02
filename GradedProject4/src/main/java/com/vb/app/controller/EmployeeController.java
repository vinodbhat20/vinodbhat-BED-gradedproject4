package com.vb.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vb.app.entity.Employee;
import com.vb.app.entity.Role;
import com.vb.app.entity.User;
import com.vb.app.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.addUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.addRole(role);
	}

	/**
	 * 9 Endpoint to list all the employees stored in the database.
	 * 
	 * @return
	 */
	@GetMapping("/employees")
	public List<Employee> listAllEmployees() {
		return employeeService.fetchAllEmployees(null);
	}

	/**
	 * Endpoint to list all the employees in order stored in the database.
	 * 
	 * @param order
	 * @return
	 */
	@GetMapping("/employees/{order}")
	public List<Employee> listAllEmployees(@PathVariable("order") String order) {
		return employeeService.fetchAllEmployees(order);
	}

	/**
	 * 5.Endpoint to fetch the employee by id stored in the database.
	 * 
	 * @param empId
	 * @return
	 */

	@GetMapping("/employees/{employeeId}")
	public Employee fetchEmployee(@PathVariable int empId) {

		Employee employee = employeeService.fetchEmployeeById(empId);

		if (employee == null) {
			throw new RuntimeException("Employee not found - " + empId);
		}

		return employee;
	}

	/**
	 * Endpoint to add the employee to be stored in the database.
	 * 
	 * @param employee
	 * @return
	 */
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.addEmployee(employee);
		return employee;
	}

	/**
	 * 6.Endpoint to update the employee to be stored in the database.
	 * 
	 * @param employee
	 * @return
	 */
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return employee;
	}

	/**
	 * 7.Endpoint to delete the employee by id stored in the database.
	 * 
	 * @param employeeId
	 * @return
	 */
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.fetchEmployeeById(employeeId);

		if (employee == null) {
			throw new RuntimeException("Employee not found - " + employeeId);
		}

		employeeService.deleteEmployee(employeeId);

		return "Deleted employee " + employeeId;
	}

	/**
	 * 8.Endpoint to fetch the employee by first name.
	 * 
	 * @param firstName
	 * @return
	 */
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> fetchEmployeesByFirstName(@PathVariable String firstName) {
		return employeeService.fetchAllEmployeesByFirstName(firstName);
	}
}
