package com.vb.app.service;

import java.util.List;

import com.vb.app.entity.Employee;
import com.vb.app.entity.Role;
import com.vb.app.entity.User;

import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

	public String addEmployee(Employee employee);

	public Employee fetchEmployeeById(int id);

	public String updateEmployee(Employee employee);

	public String deleteEmployee(int id);

	public List<Employee> fetchAllEmployees(String order);

	public List<Employee> fetchAllEmployeesByFirstName(String firstName);

	public User addUser(User user);

	public Role addRole(Role role);

}
