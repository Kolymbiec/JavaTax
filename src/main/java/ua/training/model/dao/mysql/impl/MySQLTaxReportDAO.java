package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.TaxReportDAO;
import ua.training.model.dao.mysql.sql_queries.TaxReportSQL;
import ua.training.model.entities.*;

import java.sql.*;
import java.util.ArrayList;

public class MySQLTaxReportDAO extends MySQLAbstractDAO implements TaxReportDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLTaxReportDAO.class);

    private static final int ID = 1;
    private static final int TAXPAYER_ID = 2;
    private static final int TAXPAYER_INSPECTOR_ID = 3;
    private static final int FORM_ID = 4;
    private static final int TYPE_ID = 5;
    private static final int STATUS_ID = 6;
    private static final int MESSAGE = 7;
    private static final int DATE = 8;

    private static MySQLTaxReportDAO mySQLTaxReportDAO;
    private DAOFactory daoFactory = new MySQLDAOFactory();

    private MySQLTaxReportDAO(){}

    public static MySQLTaxReportDAO getInstance(){
        if (mySQLTaxReportDAO == null){
            mySQLTaxReportDAO = new MySQLTaxReportDAO();
        }
        return mySQLTaxReportDAO;
    }


    @Override
    public ArrayList<TaxReport> findAll(int start, int recordsPerPage) throws SQLException {
        ArrayList<TaxReport> list = new ArrayList<>();
        try  (Connection connection = dataSource.getConnection();
              PreparedStatement statement = connection.prepareStatement(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() + "ORDER BY id LIMIT ?,?")){
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    TaxReport taxReport = createTaxReportFromResult(resultSet);
                    list.add(taxReport);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find all tax reports ", e);
            throw new SQLException();
        }
        return list;
    }

    @Override
    public TaxReport findById(int id) throws SQLException {
        TaxReport taxReport;
        try {
            taxReport = findById(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() + "WHERE taxreport.id = ?",
                    id, set -> set != null ? createTaxReportFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find tax report by id ", e);
            throw new SQLException();
        }
        return taxReport;
    }

    @Override
    public ArrayList<TaxReport> findByInspector(Inspector inspector) throws SQLException {
        ArrayList<TaxReport> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() +
                    "WHERE taxpayer_inspector_id = " + inspector.getId())) {
                while (resultSet.next()){
                    TaxReport taxReport = createTaxReportFromResult(resultSet);
                    list.add(taxReport);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax reports by inspector ", e);
            throw new SQLException();
        }
        return list;
    }

    @Override
    public ArrayList<TaxReport> findByTaxpayer(Taxpayer taxpayer) throws SQLException {
        ArrayList<TaxReport> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() +
                    "WHERE taxpayer_id = " + taxpayer.getId())) {
                while (resultSet.next()){
                    TaxReport taxReport = createTaxReportFromResult(resultSet);
                    list.add(taxReport);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax reports by taxpayer ", e);
            throw new SQLException();
        }
        return list;
    }

    @Override
    public ArrayList<TaxReport> findByForm(TaxReportForm form) throws SQLException {
        ArrayList<TaxReport> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() +
                    "WHERE form_id = " + form.getId())) {
                while (resultSet.next()){
                    TaxReport taxReport = createTaxReportFromResult(resultSet);
                    list.add(taxReport);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax reports by form ", e);
            throw new SQLException();
        }
        return list;
    }

    @Override
    public ArrayList<TaxReport> findByType(TaxReportType type) throws SQLException {
        ArrayList<TaxReport> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() +
                    "WHERE type_id = " + type.getId())) {
                while (resultSet.next()){
                    TaxReport taxReport = createTaxReportFromResult(resultSet);
                    list.add(taxReport);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax reports by type ", e);
            throw new SQLException();
        }
        return list;
    }

    @Override
    public ArrayList<TaxReport> findByStatus(TaxReportStatus status) throws SQLException {
        ArrayList<TaxReport> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() +
                    "WHERE status_id = " + status.getId())) {
                while (resultSet.next()){
                    TaxReport taxReport = createTaxReportFromResult(resultSet);
                    list.add(taxReport);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax reports by status ", e);
            throw new SQLException();
        }
        return list;
    }

    @Override
    public ArrayList<TaxReport> findByDate(Timestamp date) throws SQLException {
        ArrayList<TaxReport> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportSQL.SELECT_ALL_FROM_TAXREPORT.getQUERY() + "WHERE taxreport.date = ?")){
            statement.setTimestamp(1, date);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    TaxReport taxReport = createTaxReportFromResult(resultSet);
                    list.add(taxReport);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax reports by date ", e);
            throw new SQLException();
        }
        return list;
    }

    @Override
    public int selectNumberOfRows() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(TaxReportSQL.SELECT_COUNT_ROWS.getQUERY())){
            resultSet.next();
            return resultSet.getInt(1);
        }catch ( SQLException e){
            logger.error("Failed to select number of rows ", e);
            throw  new SQLException();
        }
    }

    @Override
    public boolean insert(TaxReport taxReport) throws SQLException {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(TaxReportSQL.INSERT_TAXREPORT.getQUERY(),
                Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(TAXPAYER_ID - 1, taxReport.getTaxpayer().getId());
            statement.setInt(TAXPAYER_INSPECTOR_ID - 1, taxReport.getInspector().getId());
            statement.setInt(FORM_ID - 1, taxReport.getForm().getId());
            statement.setInt(TYPE_ID - 1, taxReport.getType().getId());
            statement.setInt(STATUS_ID - 1, taxReport.getStatus().getId());
            statement.setString(MESSAGE - 1, taxReport.getMessage());
            statement.setTimestamp(DATE -1, taxReport.getDate());
            statement.executeUpdate();
            taxReport.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert tax report ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(TaxReport taxReport) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportSQL.UPDATE_TAXREPORT.getQUERY())){
            statement.setInt(TAXPAYER_ID - 1, taxReport.getTaxpayer().getId());
            statement.setInt(TAXPAYER_INSPECTOR_ID - 1, taxReport.getInspector().getId());
            statement.setInt(FORM_ID - 1, taxReport.getForm().getId());
            statement.setInt(TYPE_ID - 1, taxReport.getType().getId());
            statement.setInt(STATUS_ID - 1, taxReport.getStatus().getId());
            statement.setString(MESSAGE - 1, taxReport.getMessage());
            statement.setTimestamp(DATE -1, taxReport.getDate());
            statement.setInt(ID + 7, taxReport.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to update tax report ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(TaxReport taxReport) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportSQL.DELETE_TAXREPORT.getQUERY())){
            statement.setInt(1, taxReport.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete tax report ", e);
            throw new SQLException();
        }
    }

    private TaxReport createTaxReportFromResult(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(ID);
        Taxpayer taxpayer = daoFactory.getTaxpayerDAO().findById(resultSet.getInt(TAXPAYER_ID));
        Inspector inspector = daoFactory.getInspectorDAO().findById(resultSet.getInt(TAXPAYER_INSPECTOR_ID));
        TaxReportForm form = daoFactory.getTaxReportFormDAO().findById(resultSet.getInt(FORM_ID));
        TaxReportType type = daoFactory.getTaxReportTypeDAO().findById(resultSet.getInt(TYPE_ID));
        TaxReportStatus status = daoFactory.getTaxReportStatusDAO().findById(resultSet.getInt(STATUS_ID));
        String message = resultSet.getString(MESSAGE);
        Timestamp date = resultSet.getTimestamp(DATE);
        return new TaxReport(id, taxpayer, inspector, form, type, status, message, date);
    }
}
