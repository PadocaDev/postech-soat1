package com.padocadev;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

@TestConfiguration
@Testcontainers
public class TestContainerConfiguracao {

    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    static {
        mySQLContainer.start();
        executeMigrations();
    }

    private static void executeMigrations() {
        String jdbcUrl = mySQLContainer.getJdbcUrl();
        String username = mySQLContainer.getUsername();
        String password = mySQLContainer.getPassword();

        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .load();

        flyway.migrate();
    }

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(mySQLContainer.getJdbcUrl())
                .username(mySQLContainer.getUsername())
                .password(mySQLContainer.getPassword())
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

