package com.example.multidatasource;

import com.example.multidatasource.entity.datasource1.DataSource1Entity;
import com.example.multidatasource.entity.datasource2.DataSource2Entity;
import com.example.multidatasource.repository.datasource1.DataSource1JpaRepository;
import com.example.multidatasource.repository.datasource2.DataSource2JpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joakim Bergström
 */
@RestController
@RequiredArgsConstructor
public class Controller {

    /** Injected {@link DataSource1JpaRepository} */
    private final DataSource1JpaRepository dataSource1JpaRepository;

    /** Injected {@link DataSource2JpaRepository} */
    private final DataSource2JpaRepository dataSource2JpaRepository;

    @GetMapping("/datasource1")
    public ResponseEntity<DataSource1Entity> dataSource1() {
        return ResponseEntity.ok().body(dataSource1JpaRepository.save(new DataSource1Entity()));
    }

    @GetMapping("/datasource2")
    public ResponseEntity<DataSource2Entity> dataSource2() {
        return ResponseEntity.ok().body(dataSource2JpaRepository.save(new DataSource2Entity()));
    }
}
