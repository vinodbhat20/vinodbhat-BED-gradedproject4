package com.vb.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vb.app.entity.Employee;
import com.vb.app.entity.Role;
import com.vb.app.entity.User;
import com.vb.app.repository.EmployeeRepository;
import com.vb.app.repository.RoleRepository;
import com.vb.app.repository.UserRepository;
import com.vb.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@Override
	public User addUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		List<Role> roles = user.getRoles();

		/*
		 * roles.forEach(r -> { ArrayList<User> users = new ArrayList<User>();
		 * users.add(user); r.setUsers(users); });
		 */

		user.setRoles(new ArrayList<Role>());
		
		for (Role role : roles) {

			role = roleRepository.findByName(role.getName());
			
			if (role == null) {
				return null;
			}else {
				user.getRoles().add(role);
			}
		}
		return userRepository.save(user);
	}

	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	
	
	@Override
	public List<Employee> fetchAllEmployees(String order) {
		if ("ASC".equals(order)) {
			return employeeRepository.findAll(Sort.by(Direction.ASC, "id"));
		} else if ("DESC".equals(order)) {
			return employeeRepository.findAll(Sort.by(Direction.DESC, "id"));
		}
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> fetchAllEmployeesByFirstName(String firstName) {

		Employee employee = new Employee();
		employee.setFirstName(firstName);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("id", "lastName", "email");
		Example<Employee> example = Example.of(employee, exampleMatcher);
		return employeeRepository.findAll(example);

	}

	@Override
	public Employee fetchEmployeeById(int empId) {
		Optional<Employee> result = employeeRepository.findById(empId);

		Employee employee = null;

		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new RuntimeException("Unable to find employee " + empId);
		}
		return employee;
	}

	@Override
	public String updateEmployee(Employee employee) {
		employeeRepository.saveAndFlush(employee);
		return "Employee Updated";
	}

	@Override
	public String deleteEmployee(int id) {
		employeeRepository.deleteById(id);
		return "Employee Deleted";
	}

	@Override
	public String addEmployee(Employee employee) {
		employeeRepository.saveAndFlush(employee);
		return "Employee Added";

	}

}
