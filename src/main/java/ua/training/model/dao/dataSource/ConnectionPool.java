package ua.training.model.dao.dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface ConnectionPool {
    Connection getConnection() throws SQLException;
    void close(Connection connection, Statement statement, ResultSet resultSet);
    void close(Connection connection, Statement statement);
}
