package ua.training.model.dao.mysql.impl;

import ua.training.model.dao.dataSource.ConnectionPool;
import ua.training.model.dao.dataSource.DBCPDataSource;
import ua.training.model.dao.mysql.interfaces.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MySQLAbstractDAO {

    protected ConnectionPool dataSource = DBCPDataSource.getInstance();


    protected <T> T findById(String query, int id, Mapper<T> mapper) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.map(resultSet);
                }
            }
        }
        return null;
    }

    protected int getGeneratedKey(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        throw new SQLException("Key is not generated");
    }
}
