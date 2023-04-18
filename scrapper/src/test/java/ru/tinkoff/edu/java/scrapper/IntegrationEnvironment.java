package ru.tinkoff.edu.java.scrapper;


import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class IntegrationEnvironment {

    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer();
        POSTGRE_SQL_CONTAINER.start();
        startMigrations();
    }

    private static void startMigrations() {
        try {
            Connection connection = DriverManager.getConnection(POSTGRE_SQL_CONTAINER.getJdbcUrl(), POSTGRE_SQL_CONTAINER.getUsername(), POSTGRE_SQL_CONTAINER.getPassword());
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("master.xml", new DirectoryResourceAccessor(new File("./src/main/resources/migrations").toPath()), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException | SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

