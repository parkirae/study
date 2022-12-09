package org.zerock.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

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
