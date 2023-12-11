package com.example.multidatasource.application;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.io.Serializable;

/**
 * Implementation of {@link PhysicalNamingStrategy} for creating uppercase database naming schemas for Postgres.
 * To achieve uppercase naming schemas in Postgres the names needs to be "Quoted".
 *
 * @author Joakim Bergström
 */
public class DatabasePhysicalNamingStrategy implements PhysicalNamingStrategy, Serializable {

    @Override
    public Identifier toPhysicalCatalogName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return capitalize(logicalName);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return capitalize(logicalName);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return capitalize(logicalName);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return capitalize(logicalName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return capitalize(logicalName);
    }

    /**
     * @return The provided identifier but it is quoted.
     */
    private Identifier capitalize(final Identifier identifier) {
        return identifier != null ?  Identifier.quote(identifier) : null;
    }
}
