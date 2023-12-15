package com.example.querypoc.service;

import com.example.querypoc.dao.EmployeeDao;
import com.example.querypoc.dao.EmployeeGeoCodeDao;
import com.example.querypoc.dao.TerritoryDataDao;
import com.example.querypoc.dao.WorkLocationGeoCodeDao;
import com.example.querypoc.models.Employee;
import com.example.querypoc.models.EmployeeGeoCode;
import com.example.querypoc.models.WorkLocationGeoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryPocService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    WorkLocationGeoCodeDao workLocationGeoCodeDao;

    @Autowired
    EmployeeGeoCodeDao employeeGeoCodeDao;

    @Autowired
    TerritoryDataDao territoryDataDao;


    public List<EmployeeGeoCode> parseQuery(String ssoLoginName) {

        Employee empObject = employeeDao.findByEmployeeName(ssoLoginName);
        System.out.println("Employee Object -->"+ empObject.toString());

        // fetch the geocode from the map
//        String geoCode = workLocationGeoCodeMap.get(empObject.getEmployeeWorkLocation());
        // fetch the geo_code based on the employee work_location
        WorkLocationGeoCode workLocationGeoCodeObject =
                workLocationGeoCodeDao.findByWorkLocation(empObject.getEmployeeWorkLocation());
        System.out.println("workLocationGeoCodeObject -->"+ workLocationGeoCodeObject.toString());
        // This will return territory_string value

        List<String> territoriesFromTerritoryData = territoryDataDao
                .findTerritoryBySubString(workLocationGeoCodeObject.getTerritorySubstring());


        for ( String territory: territoriesFromTerritoryData) {
            // Check whether the given ssoLoginName is present in the EmmployeeGeoCode table
            EmployeeGeoCode employeeGeoCode = employeeGeoCodeDao.findByEmployeeNameAndTerritory(ssoLoginName, territory);
            if ( employeeGeoCode == null ) {
                // save the employee geocode object
                employeeGeoCode = new EmployeeGeoCode();
                employeeGeoCode.setEmployeeName(ssoLoginName);
                employeeGeoCode.setTerritory(territory);
                employeeGeoCodeDao.save(employeeGeoCode);
            }
        }
        return employeeGeoCodeDao.findByEmployeeName(ssoLoginName);
    }
}
