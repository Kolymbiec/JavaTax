package ua.training.model.dao.mysql.impl;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.mysql.interfaces.TaxReportFormDAO;
import ua.training.model.dao.mysql.sql_queries.TaxReportFormSQL;
import ua.training.model.entities.TaxReportForm;

import java.sql.*;

public class MySQLTaxReportFormDAO extends MySQLAbstractDAO implements TaxReportFormDAO {

    private final Logger logger = LoggerLoader.getLogger(MySQLTaxReportFormDAO.class);

    private static final int ID = 1;
    private static final int FORM = 2;

    private static MySQLTaxReportFormDAO mySQLTaxReportFormDAO;

    private MySQLTaxReportFormDAO(){}

    public static MySQLTaxReportFormDAO getInstance(){
        if (mySQLTaxReportFormDAO == null){
            mySQLTaxReportFormDAO = new MySQLTaxReportFormDAO();
        }
        return mySQLTaxReportFormDAO;
    }

    @Override
    public TaxReportForm findById(int id) throws SQLException {
        TaxReportForm form;
        try {
            form = findById(TaxReportFormSQL.SELECT_ALL_FROM_TAXREPORT_FORM.getQUERY() + "WHERE taxreport_form.id = ?",
                    id, set -> set != null ? createTaxReportFormFromResult(set) : null);
        }catch (SQLException e){
            logger.error("Failed to find form by id ", e);
            throw new SQLException();
        }
        return form;
    }

    @Override
    public TaxReportForm findByForm(String form) throws SQLException {
        TaxReportForm reportForm = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportFormSQL.SELECT_ALL_FROM_TAXREPORT_FORM.getQUERY() +
                     "WHERE taxreport_form.form = ?")){
            statement.setString(1, form);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    reportForm = createTaxReportFormFromResult(resultSet);
                }
            }
        }catch (SQLException e){
            logger.error("Failed to find tax report form by form ", e);
            throw new SQLException();
        }
        return reportForm;
    }

    @Override
    public boolean insert(TaxReportForm form, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(TaxReportFormSQL.INSERT_TAXREPORT_FORM.getQUERY(), Statement.RETURN_GENERATED_KEYS)){
            statement.setString(FORM - 1, form.getForm());
            statement.executeUpdate();
            form.setId(getGeneratedKey(statement));
            return true;
        }catch (SQLException e){
            logger.error("Failed to insert tax report form ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(TaxReportForm form) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportFormSQL.UPDATE_TAXREPORT_FORM.getQUERY())){
            statement.setString(FORM - 1, form.getForm());
            statement.setInt(ID + 1, form.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to update tax report form ", e);
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(TaxReportForm form) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(TaxReportFormSQL.DELETE_TAXREPORT_FORM.getQUERY())){
            statement.setInt(1, form.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete tax report form ", e);
            throw new SQLException();
        }
    }

    private TaxReportForm createTaxReportFormFromResult(ResultSet resultSet) throws SQLException{
        if (resultSet.isBeforeFirst()) resultSet.next();

        int id = resultSet.getInt(ID);
        String type = resultSet.getString(FORM);
        return new TaxReportForm(id, type);
    }
}
