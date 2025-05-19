package com.cap.admin.catalogo.e2e.category;

import com.cap.admin.catalogo.E2ETest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@E2ETest
@Testcontainers
public class CategoryE2ETest {

    @Container
    private static final MySQLContainer MYSQL_CONTAINER = new MySQLContainer("mysql:8.0.30-debian")
            .withPassword("123456")
            .withUsername("root")
            .withDatabaseName("adm_videos");

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("mysql.host", () -> MYSQL_CONTAINER.getHost());
        registry.add("mysql.username", () -> MYSQL_CONTAINER.getUsername());
        registry.add("mysql.password", () -> MYSQL_CONTAINER.getPassword());
        registry.add("mysql.database", () -> MYSQL_CONTAINER.getDatabaseName());
        registry.add("mysql.port", () -> MYSQL_CONTAINER.getMappedPort(3306));
    }

    @Test
    public void testWorks() {
        Assertions.assertTrue(MYSQL_CONTAINER.isRunning());
    }
}
