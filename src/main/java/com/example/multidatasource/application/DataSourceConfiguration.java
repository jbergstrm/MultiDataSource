package com.example.multidatasource.application;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Interface defining which methods need's to be overwritten when configuration a new DataSource.
 * @author Joakim Bergström
 */
public interface DataSourceConfiguration {

    /* https://docs.spring.io/spring-boot/docs/2.1.x/reference/html/howto-data-access.html */

    /**
     * Loads properties from the application.yml that's under "spring.datasource.xxx"
     */
    DataSourceProperties dataSourceProperties();

    /**
     * Constructing the dataSource based on the properties given.
     */
    DataSource dataSource(DataSourceProperties properties);

    /**
     * Building the entityManager.
     */
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                EntityManagerFactoryBuilder builder);

    /**
     * Creating the JpaTransactionManager to handle the entityManager.
     */
    PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory);
}
