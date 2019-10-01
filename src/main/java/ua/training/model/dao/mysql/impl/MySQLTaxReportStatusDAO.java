package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.mysql.interfaces.TaxReportStatusDAO;
import ua.training.model.dao.mysql.sql_queries.TaxReportStatusSQL;
import ua.training.model.entities.TaxReportStatus;

import java.sql.*;
import java.util.ArrayList;

public class MySQLTaxReportStatusDAO extends MySQLAbstractDAO implements TaxReportStatusDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLTaxReportStatusDAO.class);

    private static final int ID = 1;
    private static final int STATUS = 2;

    private static MySQLTaxReportStatusDAO mySQLTaxReportStatusDAO;

    private MySQLTaxReportStatusDAO(){}

    public static MySQLTaxReportStatusDAO getInstance(){
        if (mySQLTaxReportStatusDAO == null){
            mySQLTaxReportStatusDAO = new MySQLTaxReportStatusDAO();
        }
        return mySQLTaxReportStatusDAO;
    }

    @Override
    public ArrayList<TaxReportStatus> findAll() throws SQLException {
        try  (Connection connection = dataSource.getConnection();
              PreparedStatement statement = connection.prepareStatement(TaxReportStatusSQL.SELECT_ALL_FROM_TAXREPORT_STATUS.getQUERY());
              ResultSet resultSet = statement.executeQuery()){
            return resultToList(resultSet);
        }catch (SQLException e){
            logger.error("Failed to find all tax report statuses ", e);
            throw new SQLException();
        }
    }

    @Override
    public TaxReportStatus findById(int id) throws SQLException {
        TaxReportStatus status;
        try {
            status = findById(TaxReportStatusSQL.SELECT_ALL_FROM_TAXREPORT_STATUS.getQUERY() + "WHERE taxreport_status.id = ?",
                    id, set -> set != null ? createTaxReportStatusFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find status by id ", e);
            throw new SQLException();
        }
        return status;
    }

    @Override
    public TaxReportStatus findByStatus(String status) throws SQLException {
        TaxReportStatus reportStatus = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportStatusSQL.SELECT_ALL_FROM_TAXREPORT_STATUS.getQUERY() +
                     "WHERE taxreport_status.status = ?")){
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    reportStatus = createTaxReportStatusFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax report status by form ", e);
            throw new SQLException();
        }
        return reportStatus;
    }

    @Override
    public boolean insert(TaxReportStatus status, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(TaxReportStatusSQL.INSERT_TAXREPORT_STATUS.getQUERY(), Statement.RETURN_GENERATED_KEYS)){
            statement.setString(STATUS - 1, status.getStatus());
            statement.executeUpdate();
            status.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert tax report status ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(TaxReportStatus status) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportStatusSQL.UPDATE_TAXREPORT_STATUS.getQUERY())){
            statement.setString(STATUS - 1, status.getStatus());
            statement.setInt(ID + 1, status.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to update tax report status ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(TaxReportStatus status) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportStatusSQL.DELETE_TAXREPORT_STATUS.getQUERY())){
            statement.setInt(1, status.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete tax report status ", e);
            throw new SQLException();
        }
    }

    private ArrayList<TaxReportStatus> resultToList(ResultSet resultSet) throws SQLException{
        ArrayList<TaxReportStatus> list = new ArrayList<>();
        while (resultSet.next()){
            TaxReportStatus status = createTaxReportStatusFromResult(resultSet);
            list.add(status);
        }
        return list;
    }

    private TaxReportStatus createTaxReportStatusFromResult(ResultSet resultSet) throws SQLException{
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(ID);
        String status = resultSet.getString(STATUS);
        return new TaxReportStatus(id, status);
    }
}
