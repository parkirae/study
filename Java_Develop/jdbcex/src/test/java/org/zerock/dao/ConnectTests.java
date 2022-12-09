package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

    @Test
    public void testHikariCP() throws Exception {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Drvier");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser"); // 개인 mariaDB ID
        config.setPassword("webuser"); // 개인 mariaDB PW
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();

        System.out.println(connection);

        connection.close();
    }

    @Test
    public void testConnection() throws Exception {

        Class.forName("org.mariadb.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "root", // 자신의 MariaDB ID
                ""); // 자신의 MariaDB PW

        Assertions.assertNotNull(connection);

        connection.close();

    }
}
