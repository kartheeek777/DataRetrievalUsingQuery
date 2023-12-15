package com.example.querypoc.dao;

import com.example.querypoc.models.TerritoryData;
import com.example.querypoc.models.WorkLocationGeoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface TerritoryDataDao extends JpaRepository<TerritoryData, Long> {


    @Query(value = "SELECT TERRITORY FROM TERRITORY_DATA WHERE TERRITORY LIKE CONCAT('%',?1,'%')", nativeQuery = true)
    List<String> findTerritoryBySubString(String territory);
}
