package com.example.multidatasource.entity.datasource1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

/**
 * @author Joakim Bergström
 */
@Data
@Entity
@Table(name = "DATASOURCE1")
public class DataSource1Entity {

    @Id
    @Column(name = "UUID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
}
