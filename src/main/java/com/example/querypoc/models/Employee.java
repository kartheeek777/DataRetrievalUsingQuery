package com.example.querypoc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee {

    @Id
    @Column(name= "EMPLOYEE_ID")
    int employeeId;

    @Column(name ="EMPLOYEE_NAME")
    String employeeName;

    @Column(name ="EMPLOYEE_WORK_LOCATION")
    String employeeWorkLocation;
}
