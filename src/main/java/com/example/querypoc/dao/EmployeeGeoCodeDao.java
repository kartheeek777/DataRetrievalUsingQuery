package com.example.querypoc.dao;

import com.example.querypoc.models.EmployeeGeoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface EmployeeGeoCodeDao extends JpaRepository<EmployeeGeoCode, Long> {

    @Query(value = "SELECT * FROM EMPLOYEE_TERRITORY WHERE EMPLOYEE_NAME = ?1 AND TERRITORY = ?2", nativeQuery = true)
    EmployeeGeoCode findByEmployeeNameAndTerritory(String ssoLoginName, String territory);

    List<EmployeeGeoCode> findByEmployeeName(String ssoLoginName);
}
