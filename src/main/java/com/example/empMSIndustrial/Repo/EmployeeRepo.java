package com.example.empMSIndustrial.Repo;

import com.example.empMSIndustrial.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
