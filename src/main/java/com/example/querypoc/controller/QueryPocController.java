package com.example.querypoc.controller;

import com.example.querypoc.models.EmployeeGeoCode;
import com.example.querypoc.service.QueryPocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryPocController {

    @Autowired
    public QueryPocService queryPocService;

    @GetMapping("/policy-creation")
    public List<EmployeeGeoCode> parseQuery(
            @RequestParam("ssoLoginName") String ssoLoginName){
        return queryPocService.parseQuery(ssoLoginName);
    }

}
