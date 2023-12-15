package com.example.querypoc.dao;

import com.example.querypoc.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_NAME= ?1", nativeQuery = true)
    Employee findByEmployeeName(String ssoLoginName);
}
