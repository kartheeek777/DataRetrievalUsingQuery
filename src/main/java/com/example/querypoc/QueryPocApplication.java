package com.example.querypoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.querypoc.models")
@EnableJpaRepositories("com.example.querypoc.dao")
public class QueryPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryPocApplication.class, args);
    }

}
