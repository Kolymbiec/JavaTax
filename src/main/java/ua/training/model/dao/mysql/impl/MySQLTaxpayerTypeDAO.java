package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.mysql.interfaces.TaxpayerTypeDAO;
import ua.training.model.dao.mysql.sql_queries.TaxpayerTypeSQL;
import ua.training.model.entities.TaxpayerType;

import java.sql.*;

public class MySQLTaxpayerTypeDAO extends MySQLAbstractDAO implements TaxpayerTypeDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLTaxpayerTypeDAO.class);

    private static final int ID = 1;
    private static final int TYPE = 2;

    private static MySQLTaxpayerTypeDAO mySQLTaxpayerTypeDAO;

    private MySQLTaxpayerTypeDAO(){}

    public static MySQLTaxpayerTypeDAO getInstance(){
        if (mySQLTaxpayerTypeDAO == null){
            mySQLTaxpayerTypeDAO = new MySQLTaxpayerTypeDAO();
        }
        return mySQLTaxpayerTypeDAO;
    }

    @Override
    public TaxpayerType findById(int id) throws SQLException {
        TaxpayerType taxpayerType;
        try {
            taxpayerType = findById(TaxpayerTypeSQL.SELECT_ALL_FROM_TAXPAYER_TYPE.getQUERY() +
                    "WHERE taxpayer_type.id = ?", id, set -> set != null ? createTaxpayerTypeFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find taxpayer type by id ", e);
            throw new SQLException();
        }
        return taxpayerType;
    }

    @Override
    public TaxpayerType findByType(String type) throws SQLException {
        TaxpayerType taxpayerType = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxpayerTypeSQL.SELECT_ALL_FROM_TAXPAYER_TYPE.getQUERY()+
                     "WHERE taxpayer_type.type = ?")){
            statement.setString(1, type);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    taxpayerType =createTaxpayerTypeFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find taxpayer type by type ", e);
            throw new SQLException();
        }
        return taxpayerType;
    }

    @Override
    public boolean insert(TaxpayerType type, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(TaxpayerTypeSQL.INSERT_TAXPAYER_TYPE.getQUERY(), Statement.RETURN_GENERATED_KEYS)){
            statement.setString(TYPE - 1, type.getType());
            statement.executeUpdate();
            type.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert taxpayer type ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(TaxpayerType type) throws SQLException {
       try (Connection connection = dataSource.getConnection();
       PreparedStatement statement = connection.prepareStatement(TaxpayerTypeSQL.UPDATE_TAXPAYER_TYPE.getQUERY())){
           statement.setString(TYPE - 1, type.getType());
           statement.setInt(ID + 1, type.getId());
           statement.executeUpdate();
           return true;
       }catch (SQLException e){
           logger.error("Failed to update taxpayer type ", e);
           throw new SQLException();
       }
    }

    @Override
    public boolean delete(TaxpayerType type) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(TaxpayerTypeSQL.DELETE_TAXPAYER_TYPE.getQUERY())){
            statement.setInt(1, type.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete taxpayer type ", e);
            throw new SQLException();
        }
    }

    private TaxpayerType createTaxpayerTypeFromResult(ResultSet resultSet) throws SQLException{
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(ID);
        String type = resultSet.getString(TYPE);
        return new TaxpayerType(id, type);
    }
}
