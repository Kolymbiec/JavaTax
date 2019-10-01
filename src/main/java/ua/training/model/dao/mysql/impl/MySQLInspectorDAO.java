package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.InspectorDAO;
import ua.training.model.dao.mysql.sql_queries.InspectorSQL;
import ua.training.model.entities.Inspector;
import ua.training.model.entities.User;

import java.sql.*;
import java.util.ArrayList;

public class MySQLInspectorDAO extends MySQLAbstractDAO implements InspectorDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLInspectorDAO.class);

    private static final int ID = 1;
    private static final int USER_ID = 2;
    private  static final int NAME = 3;
    private  static final int SURNAME = 4;
    private  static final int EMAIL = 5;

    private DAOFactory daoFactory = new MySQLDAOFactory();
    private static MySQLInspectorDAO mySQLInspectorDAO;

    private MySQLInspectorDAO(){}

    public static MySQLInspectorDAO getInstance(){
        if (mySQLInspectorDAO == null){
            mySQLInspectorDAO = new MySQLInspectorDAO();
        }
        return mySQLInspectorDAO;
    }

    @Override
    public ArrayList<Inspector> findAll() throws SQLException {
        try  (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(InspectorSQL.SELECT_ALL_FROM_INSPECTOR.getQUERY())){
            return resultToList(resultSet);
        }catch (SQLException e){
            logger.error("Failed to find all inspectors ", e);
            throw new SQLException();
        }
    }

    @Override
    public Inspector findById(int id) throws SQLException {
        Inspector inspector;
        try {
            inspector = findById(InspectorSQL.SELECT_ALL_FROM_INSPECTOR.getQUERY() + " WHERE inspector.id = ?", id,
                    set -> set != null ? createInspectorFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find inspector by id ", e);
            throw new SQLException();
        }
        return inspector;
    }

    @Override
    public Inspector findBySurname(String surname) throws SQLException {
        Inspector inspector = null;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(InspectorSQL.SELECT_ALL_FROM_INSPECTOR.getQUERY()
        + "WHERE inspector.surname = ?")){
            statement.setString(1, surname);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    inspector = createInspectorFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find inspector by surname ", e);
            throw new SQLException();
        }
        return inspector;
    }

    @Override
    public Inspector findByLogin(String login) throws SQLException {
        Inspector inspector = null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()){
            try (ResultSet set = statement.executeQuery(InspectorSQL.SELECT_BY_USER.getQUERY()
                    + login + ")")){
                while (set.next()){
                    inspector = createInspectorFromResult(set);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find inspector by login ", e);
            throw new SQLException();
        }
        return inspector;
    }

    @Override
    public boolean insert(Inspector inspector, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(InspectorSQL.INSERT_INSPECTOR.getQUERY(), Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(USER_ID - 1, inspector.getUser().getId());
            statement.setString(NAME - 1, inspector.getName());
            statement.setString(SURNAME - 1, inspector.getSurname());
            statement.setString(EMAIL - 1, inspector.getEmail());
            statement.executeUpdate();
            inspector.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert inspector ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(Inspector inspector) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(InspectorSQL.UPDATE_INSPECTOR.getQUERY())){
            statement.setInt(USER_ID - 1, inspector.getUser().getId());
            statement.setString(NAME - 1, inspector.getName());
            statement.setString(SURNAME - 1, inspector.getSurname());
            statement.setString(EMAIL - 1, inspector.getEmail());
            statement.setInt(ID + 5, inspector.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to update inspector ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(Inspector inspector) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(InspectorSQL.DELETE_INSPECTOR.getQUERY())){
            statement.setInt(1, inspector.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete inspector ", e);
            throw new SQLException();
        }
    }

    private ArrayList<Inspector> resultToList(ResultSet resultSet) throws SQLException {
        ArrayList<Inspector> list = new ArrayList<>();
        while (resultSet.next()) {
            Inspector inspector = createInspectorFromResult(resultSet);
            list.add(inspector);
        }
        return list;
    }

    private Inspector createInspectorFromResult(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(ID);
        User user = daoFactory.getUserDAO().findById(resultSet.getInt(USER_ID));
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        String email = resultSet.getString(EMAIL);
        return new Inspector(id, user, name, surname, email);
    }
}
