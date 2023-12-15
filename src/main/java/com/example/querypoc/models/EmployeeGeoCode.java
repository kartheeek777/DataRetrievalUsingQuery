package com.example.querypoc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE_TERRITORY")
@Data
public class EmployeeGeoCode {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "emp_terr_seq", allocationSize = 1)
    @Column(name= "EMPLOYEE_TERRITORY_ID")
    int employeeId;

    @Column(name ="EMPLOYEE_NAME")
    String employeeName;

    @Column(name ="TERRITORY")
    String territory;
}
