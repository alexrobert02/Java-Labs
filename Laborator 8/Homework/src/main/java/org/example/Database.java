package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    // Create a BasicDataSource instance for database connection pooling
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        // Set the database URL, username, and password for the data source
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("java");
        dataSource.setPassword("java");

        // Set the minimum and maximum number of idle connections in the pool
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);

        // Set the maximum number of prepared statements that can be opened at the same time
        dataSource.setMaxOpenPreparedStatements(100);

        // Disable auto-commit for transactions that return the connection to the pool
        dataSource.setAutoCommitOnReturn(false);
    }

    public Database() {

    }

    // Get a connection from the data source
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
