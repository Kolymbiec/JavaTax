package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.TaxpayerDAO;
import ua.training.model.dao.mysql.sql_queries.TaxpayerSQL;
import ua.training.model.entities.Inspector;
import ua.training.model.entities.Taxpayer;
import ua.training.model.entities.TaxpayerType;
import ua.training.model.entities.User;

import java.sql.*;
import java.util.ArrayList;

public class MySQLTaxpayerDAO extends MySQLAbstractDAO implements TaxpayerDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLTaxpayerDAO.class);

    private static final int ID = 1;
    private static final int USER_ID = 2;
    private static final int TYPE = 3;
    private static final int INSPECTOR_ID = 4;
    private static final int NAME = 5;
    private static final int SURNAME = 6;
    private static final int EMAIL = 7;

    private static MySQLTaxpayerDAO mySQLTaxpayerDAO;
    private DAOFactory daoFactory = new MySQLDAOFactory();

    private MySQLTaxpayerDAO(){}

    public static MySQLTaxpayerDAO getInstance(){
        if (mySQLTaxpayerDAO == null){
            mySQLTaxpayerDAO = new MySQLTaxpayerDAO();
        }
        return mySQLTaxpayerDAO;
    }

    @Override
    public ArrayList<Taxpayer> findAll() throws SQLException {
        try  (Connection connection = dataSource.getConnection();
              PreparedStatement statement = connection.prepareStatement(TaxpayerSQL.SELECT_ALL_FROM_TAXPAYER.getQUERY());
              ResultSet resultSet = statement.executeQuery()){
            return resultToList(resultSet);
        }catch (SQLException e){
            logger.error("Failed to find all taxpayers ", e);
            throw new SQLException();
        }
    }

    @Override
    public Taxpayer findById(int id) throws SQLException {
        Taxpayer taxpayer;
        try {
            taxpayer = findById(TaxpayerSQL.SELECT_ALL_FROM_TAXPAYER.getQUERY() + "WHERE taxpayer.id = ?",
                    id, set -> set != null ? createTaxpayerFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find taxpayer by id ", e);
            throw new SQLException();
        }
        return taxpayer;
    }

    @Override
    public Taxpayer findByLogin(String login) throws SQLException {

        Taxpayer taxpayer = null;
        try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(TaxpayerSQL.SELECT_BY_USER.getQUERY() + login + ")")){
                while (resultSet.next()){
                    taxpayer = createTaxpayerFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find taxpayer by login ", e);
            throw new SQLException();
        }
        return taxpayer;
    }

    @Override
    public Taxpayer findBySurname(String surname) throws SQLException {
        Taxpayer taxpayer = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxpayerSQL.SELECT_ALL_FROM_TAXPAYER.getQUERY() + "WHERE taxpayer.surname = ?")){
            statement.setString(1, surname);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    taxpayer = createTaxpayerFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find taxpayer by login ", e);
            throw new SQLException();
        }
        return taxpayer;
    }

    @Override
    public ArrayList<Taxpayer> findByInspector(Inspector inspector) throws SQLException {
        ArrayList<Taxpayer> taxpayers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(TaxpayerSQL.SELECT_ALL_FROM_TAXPAYER.getQUERY() +
                    "WHERE inspector_id = " + inspector.getId())) {
                while (resultSet.next()){
                    Taxpayer taxpayer = createTaxpayerFromResult(resultSet);
                    taxpayers.add(taxpayer);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find taxpayers by inspector ", e);
            throw new SQLException();
        }
        return taxpayers;
    }

    @Override
    public ArrayList<Taxpayer> findByTaxpayerType(TaxpayerType type) throws SQLException {
        ArrayList<Taxpayer> taxpayers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(TaxpayerSQL.SELECT_ALL_FROM_TAXPAYER.getQUERY() +
                    "WHERE taxpayer_type.id = " + type.getId())) {
                while (resultSet.next()){
                    Taxpayer taxpayer = createTaxpayerFromResult(resultSet);
                    taxpayers.add(taxpayer);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find taxpayers by inspector ", e);
            throw new SQLException();
        }
        return taxpayers;
    }

    @Override
    public boolean insert(Taxpayer taxpayer, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(TaxpayerSQL.INSERT_TAXPAYER.getQUERY(),
                Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(USER_ID - 1, taxpayer.getUser().getId());
            statement.setInt(TYPE - 1, taxpayer.getType().getId());
            statement.setInt(INSPECTOR_ID - 1, taxpayer.getInspector().getId());
            statement.setString(NAME - 1, taxpayer.getName());
            statement.setString(SURNAME - 1, taxpayer.getSurname());
            statement.setString(EMAIL -1, taxpayer.getEmail());
            statement.executeUpdate();
            taxpayer.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert taxpayer ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(Taxpayer taxpayer) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(TaxpayerSQL.UPDATE_TAXPAYER.getQUERY())){
            statement.setInt(USER_ID - 1, taxpayer.getUser().getId());
            statement.setInt(TYPE - 1, taxpayer.getType().getId());
            statement.setInt(INSPECTOR_ID - 1, taxpayer.getInspector().getId());
            statement.setString(NAME - 1, taxpayer.getName());
            statement.setString(SURNAME - 1, taxpayer.getSurname());
            statement.setString(EMAIL -1, taxpayer.getEmail());
            statement.setInt(ID + 6, taxpayer.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to update taxpayer ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(Taxpayer taxpayer) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(TaxpayerSQL.DELETE_TAXPAYER.getQUERY())){
            statement.setInt(1, taxpayer.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete taxpayer ", e);
            throw new SQLException();
        }
    }

    private ArrayList<Taxpayer> resultToList(ResultSet resultSet) throws SQLException {
        ArrayList<Taxpayer> list = new ArrayList<>();
        while (resultSet.next()) {
            Taxpayer taxpayer = createTaxpayerFromResult(resultSet);
            list.add(taxpayer);
        }
        return list;
    }

    private Taxpayer createTaxpayerFromResult(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(ID);
        User user = daoFactory.getUserDAO().findById(resultSet.getInt((USER_ID)));
        TaxpayerType type = daoFactory.getTaxpayerTypeDAO().findById(resultSet.getInt(TYPE));
        Inspector inspector = daoFactory.getInspectorDAO().findById(resultSet.getInt(INSPECTOR_ID));
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        String email = resultSet.getString(EMAIL);
        return new Taxpayer(id, user, type, inspector, name, surname, email);
    }
}
