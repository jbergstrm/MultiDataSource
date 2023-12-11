package com.example.multidatasource.application;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

/**
 * Constructs the secondary datasource.<
 * @author Joakim Bergström
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.multidatasource.repository.datasource2",
        entityManagerFactoryRef = "dataSource2EntityManagerFactory",
        transactionManagerRef = "dataSource2TransactionManager"
)
public class DataSource2Configuration implements DataSourceConfiguration {

    @Bean("dataSource2Properties")
    @ConfigurationProperties("spring.datasource.multi-datasource-database2")
    @Override
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("dataSource2")
    @Override
    public DataSource dataSource(
            @Qualifier("dataSource2Properties") final DataSourceProperties properties
    ) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean("dataSource2EntityManagerFactory")
    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("dataSource2") final DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder.dataSource(dataSource)
                .packages("com.example.multidatasource.entity.datasource2")
                .properties(new HashMap<>() {{
                    put("hibernate.hbm2ddl.auto", "create-drop");
                    put("hibernate.physical_naming_strategy", "com.example.multidatasource.application.DatabasePhysicalNamingStrategy");
                    put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                }})
                .build();
    }

    @Bean("dataSource2TransactionManager")
    @Override
    public PlatformTransactionManager transactionManager(
            @Qualifier("dataSource2EntityManagerFactory") final LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
