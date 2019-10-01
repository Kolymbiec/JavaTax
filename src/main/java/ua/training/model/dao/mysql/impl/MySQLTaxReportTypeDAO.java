package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.mysql.interfaces.TaxReportTypeDAO;
import ua.training.model.dao.mysql.sql_queries.TaxReportTypeSQL;
import ua.training.model.entities.TaxReportType;

import java.sql.*;
import java.util.ArrayList;

public class MySQLTaxReportTypeDAO extends MySQLAbstractDAO implements TaxReportTypeDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLTaxReportTypeDAO.class);

    private static final int ID = 1;
    private static final int NUMBER = 2;
    private static final int TYPE = 3;

    private static MySQLTaxReportTypeDAO mySQLTaxReportTypeDAO;

    private MySQLTaxReportTypeDAO(){}

    public static MySQLTaxReportTypeDAO getInstance(){
        if (mySQLTaxReportTypeDAO == null){
            mySQLTaxReportTypeDAO = new MySQLTaxReportTypeDAO();
        }
        return mySQLTaxReportTypeDAO;
    }

    @Override
    public ArrayList<TaxReportType> findAll() throws SQLException {
        try  (Connection connection = dataSource.getConnection();
              PreparedStatement statement = connection.prepareStatement(TaxReportTypeSQL.SELECT_ALL_FROM_TAXREPORT_TYPE.getQUERY());
              ResultSet resultSet = statement.executeQuery()){
            return resultToList(resultSet);
        }catch (SQLException e){
            logger.error("Failed to find all tax report types ", e);
            throw new SQLException();
        }
    }

    @Override
    public TaxReportType findById(int id) throws SQLException {
        TaxReportType type;
        try {
            type = findById(TaxReportTypeSQL.SELECT_ALL_FROM_TAXREPORT_TYPE.getQUERY() + "WHERE taxreport_type.id = ?",
                    id, set -> set != null ? createTaxReportTypeFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find type by id ", e);
            throw new SQLException();
        }
        return type;
    }

    @Override
    public TaxReportType findByNumber(int number) throws SQLException {
        TaxReportType reportType = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportTypeSQL.SELECT_ALL_FROM_TAXREPORT_TYPE.getQUERY() +
                     "WHERE taxreport_type.number = ?")){
            statement.setInt(1, number);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    reportType = createTaxReportTypeFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax report type by number ", e);
            throw new SQLException();
        }
        return reportType;
    }

    @Override
    public TaxReportType findByName(String name) throws SQLException {
        TaxReportType reportType = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportTypeSQL.SELECT_ALL_FROM_TAXREPORT_TYPE.getQUERY() +
                     "WHERE taxreport_type.type_name = ?")){
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    reportType = createTaxReportTypeFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax report type by type ", e);
            throw new SQLException();
        }
        return reportType;
    }

    @Override
    public boolean insert(TaxReportType type, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(TaxReportTypeSQL.INSERT_TAXREPORT_TYPE.getQUERY(), Statement.RETURN_GENERATED_KEYS)){
            statement.setString(NUMBER - 1, type.getTypeNumber());
            statement.setString(TYPE - 1, type.getTypeName());
            statement.executeUpdate();
            type.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert tax report type ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(TaxReportType type) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportTypeSQL.UPDATE_TAXREPORT_TYPE.getQUERY())){
            statement.setString(NUMBER - 1, type.getTypeNumber());
            statement.setString(TYPE - 1, type.getTypeName());
            statement.setInt(ID + 2, type.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to update tax report type ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(TaxReportType type) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportTypeSQL.DELETE_TAXREPORT_TYPE.getQUERY())){
            statement.setInt(1, type.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete tax report type ", e);
            throw new SQLException();
        }
    }

    private ArrayList<TaxReportType> resultToList(ResultSet resultSet) throws SQLException{
        ArrayList<TaxReportType> list = new ArrayList<>();
        while (resultSet.next()){
            TaxReportType type = createTaxReportTypeFromResult(resultSet);
            list.add(type);
        }
        return list;
    }

    private TaxReportType createTaxReportTypeFromResult(ResultSet resultSet) throws SQLException{
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(ID);
        String number = resultSet.getString(NUMBER);
        String type = resultSet.getString(TYPE);
        return new TaxReportType(id, number, type);
    }
}
