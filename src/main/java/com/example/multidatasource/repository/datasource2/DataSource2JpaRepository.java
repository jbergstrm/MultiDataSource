package com.example.multidatasource.repository.datasource2;

import com.example.multidatasource.entity.datasource2.DataSource2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Joakim Bergström
 */
@Repository
public interface DataSource2JpaRepository extends JpaRepository<DataSource2Entity, UUID> {

}
