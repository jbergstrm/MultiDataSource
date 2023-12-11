package com.example.multidatasource.application;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

/**
 * Construct the primary datasource.
 * @author Joakim Bergström
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.multidatasource.repository.datasource1",
        entityManagerFactoryRef = "dataSource1EntityManagerFactory",
        transactionManagerRef = "dataSource1TransactionManager"
)
public class DataSource1Configuration implements DataSourceConfiguration {

    @Primary
    @Bean("dataSource1Properties")
    @ConfigurationProperties("spring.datasource.multi-datasource-database1")
    @Override
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean("dataSource1")
    @Override
    public DataSource dataSource(
            @Qualifier("dataSource1Properties") final DataSourceProperties properties
    ) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean("dataSource1EntityManagerFactory")
    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("dataSource1") final DataSource dataSource,
            final EntityManagerFactoryBuilder builder
    ) {
        return builder.dataSource(dataSource)
                .packages("com.example.multidatasource.entity.datasource1")
                .properties(new HashMap<>() {{
                    put("hibernate.hbm2ddl.auto", "create-drop");
                    put("hibernate.physical_naming_strategy", "com.example.multidatasource.application.DatabasePhysicalNamingStrategy");
                    put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                }})
                .build();
    }

    @Primary
    @Bean("dataSource1TransactionManager")
    @Override
    public PlatformTransactionManager transactionManager(
            @Qualifier("dataSource1EntityManagerFactory") final LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
