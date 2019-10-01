package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.mysql.interfaces.UserDAO;
import ua.training.model.dao.mysql.sql_queries.UserSQL;
import ua.training.model.entities.User;

import java.sql.*;
import java.util.ArrayList;

public class MySQLUserDAO extends MySQLAbstractDAO implements UserDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLUserDAO.class);
    private  static final int ID = 1;
    private  static final int LOGIN = 2;
    private  static final int PASSWORD = 3;

    private static MySQLUserDAO mySQLUserDAO;

    private MySQLUserDAO(){}

    public static MySQLUserDAO getInstance(){
        if (mySQLUserDAO == null){
            mySQLUserDAO = new MySQLUserDAO();
        }
        return mySQLUserDAO;
    }

    @Override
    public ArrayList<User> findAll() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(UserSQL.SELECT_ALL_FROM_USER.getQUERY())){
                return resultToList(resultSet);
        }catch (SQLException e){
            logger.error("Failed to find all users ", e);
            throw new SQLException();
        }
    }

    @Override
    public User findById(int id) throws SQLException {
        User user;
        try {
            user = findById(UserSQL.SELECT_ALL_FROM_USER.getQUERY() + " WHERE user.id = ?", id, set -> set != null ? createUserFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find user by id ", e);
            throw new SQLException();
        }
        return user;
    }

    @Override
    public User finByLogin(String login) throws SQLException {
        User user = null;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UserSQL.SELECT_ALL_FROM_USER.getQUERY() + "WHERE user.login = ?")){
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    user = createUserFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find user by login ", e);
            throw new SQLException();
        }
        return user;
    }

    @Override
    public boolean insert(User user, Connection connection) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(UserSQL.INSERT_USER.getQUERY(),  Statement.RETURN_GENERATED_KEYS)){
            statement.setString(LOGIN - 1, user.getLogin());
            statement.setString(PASSWORD - 1, user.getPassword());
            statement.executeUpdate();
            user.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert user ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(User user) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UserSQL.UPDATE_USER.getQUERY())){
            statement.setString(LOGIN - 1, user.getLogin());
            statement.setString(PASSWORD - 1, user.getPassword());
            statement.setInt(ID + 2, user.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to update user ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(User user) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UserSQL.DELETE_USER.getQUERY())){
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete user ", e);
            throw new SQLException();
        }
    }

    private ArrayList<User> resultToList(ResultSet resultSet) throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User user = createUserFromResult(resultSet);
            list.add(user);
        }
        return list;
    }

    private User createUserFromResult(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) resultSet.next();
        int id = resultSet.getInt(ID);
        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);
        return new User(id, login, password);
    }
}
