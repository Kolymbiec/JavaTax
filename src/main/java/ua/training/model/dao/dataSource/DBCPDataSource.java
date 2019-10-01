package ua.training.model.dao.dataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBCPDataSource implements ConnectionPool {

    private final Logger logger = LoggerLoader.getLogger(DBCPDataSource.class);

    private static ConnectionPool instance;

    public static ConnectionPool getInstance() {
        if (instance == null){
            instance = new DBCPDataSource();
        }
        return instance;
    }

    private DataSource dataSource;

    private DBCPDataSource() {
        ResourceBundle bundle = ResourceBundle.getBundle("mysql-connection");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(bundle.getString("url"));
        ds.setUsername(bundle.getString("username"));
        ds.setPassword(bundle.getString("password"));
        ds.setMinIdle(Integer.parseInt(bundle.getString("min.idle")));
        ds.setMaxIdle(Integer.parseInt(bundle.getString("max.idle")));
        ds.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString("max.open.prepared.statements")));
        dataSource = ds;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            logger.info("Connection receive " + connection);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

     public void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(connection, statement);
        try {
            if (resultSet != null) {resultSet.close();}
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

     public void close(Connection connection, Statement statement) {
        try {
            if (statement != null) {statement.close();}
            if (connection != null) {connection.close();}
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
