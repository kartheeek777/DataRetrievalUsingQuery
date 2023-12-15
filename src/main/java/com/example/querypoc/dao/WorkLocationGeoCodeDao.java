package com.example.querypoc.dao;

import com.example.querypoc.models.WorkLocationGeoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface WorkLocationGeoCodeDao extends JpaRepository<WorkLocationGeoCode, Long> {
    @Query(value = "SELECT * FROM WORK_LOCATION_TERRITORY WHERE WORK_LOCATION = ?1", nativeQuery = true)
    WorkLocationGeoCode findByWorkLocation(String employeeWorkLocation);
}
