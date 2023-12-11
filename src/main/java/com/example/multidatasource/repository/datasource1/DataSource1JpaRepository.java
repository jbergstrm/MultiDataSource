package com.example.multidatasource.repository.datasource1;

import com.example.multidatasource.entity.datasource1.DataSource1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Joakim Bergström
 */
@Repository
public interface DataSource1JpaRepository extends JpaRepository<DataSource1Entity, UUID> {

}
