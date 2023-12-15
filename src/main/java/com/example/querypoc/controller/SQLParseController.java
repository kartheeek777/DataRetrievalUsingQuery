package com.example.querypoc.controller;

import com.example.querypoc.models.SqlQueryVO;
import com.example.querypoc.service.SQLParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SQLParseController {


    @Autowired
    SQLParseService sqlParseService;


    @GetMapping("/parse-sql")
    public List<Map<String, Object>> parseSqlQuery
            //(@RequestParam("query") String query
    (@RequestBody  SqlQueryVO sqlQueryObj) {
        return sqlParseService.parseSqlQuery(sqlQueryObj.query, sqlQueryObj.destinationTable);
    }

}
