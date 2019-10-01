package ua.training.model.services;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.dataSource.DBCPDataSource;
import ua.training.model.dao.mysql.interfaces.InspectorDAO;
import ua.training.model.dao.mysql.interfaces.TaxpayerDAO;
import ua.training.model.dao.mysql.interfaces.UserDAO;
import ua.training.model.entities.Inspector;
import ua.training.model.entities.Taxpayer;
import ua.training.model.entities.User;

import java.sql.Connection;
import java.sql.SQLException;

public class RegisterManager {

    private static final Logger logger = LoggerLoader.getLogger(RegisterManager.class);
    private static RegisterManager registerManager;
    private MySQLDAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    private RegisterManager(){}

    public static RegisterManager getInstance(){
        if (registerManager == null){
            registerManager = new RegisterManager();
        }
        return registerManager;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DBCPDataSource.getInstance().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }


    public void close(Connection connection) {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            logger.error("Failed to close connection ", e);
        }
    }

    public boolean registerTaxpayer(Taxpayer taxpayer){
        Connection connection = null;
        try {
            connection = getConnection();
            mySQLDAOFactory.getTaxpayerDAO().insert(taxpayer, connection);
            connection.commit();

        }catch (SQLException e){
            logger.error("Failed to insert taxpayer", e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                logger.error("Failed to rollback", e1);
            }
            return false;
        } finally {
            close(connection);
        }
        return true;
    }

    public boolean registerUser(User user){
        UserDAO userDao = mySQLDAOFactory.getUserDAO();
        Connection connection = null;
        try {
            connection = getConnection();
            userDao.insert(user, connection);
            connection.commit();

        }catch (SQLException e){
            logger.error("Failed to insert user", e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                logger.error("Failed to rollback", e1);
            }
            return false;
        } finally {
            close(connection);
        }
        return true;
    }
}
