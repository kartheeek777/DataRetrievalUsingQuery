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
@Table(name = "TERRITORY_DATA")
@Data
public class TerritoryData {

    @Id
    @Column(name= "TERRITORY_ID")
    int territoryId;

    @Column(name ="TERRITORY")
    String territory;
}
