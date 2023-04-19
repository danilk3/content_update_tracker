package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectionConfiguration {

    @Bean
    public Connection connection() throws SQLException {
        String userName = "tracker-user";
        String password = "123456";
        String url = "jdbc:postgresql://localhost:5432/scrapper";
        return DriverManager.getConnection(url, userName, password);
    }
}
