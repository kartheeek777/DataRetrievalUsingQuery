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
@Table(name = "WORK_LOCATION_TERRITORY")
@Data
public class WorkLocationGeoCode {

    @Id
    @Column(name= "WORK_LOCATION_TERRITORY_ID")
    int employeeId;

    @Column(name ="WORK_LOCATION")
    String workLocation;

    @Column(name ="TERRITORY_SUBSTRING")
    String territorySubstring;
}
