package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.TaxReportForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TaxReportFormDAO {

    TaxReportForm findById(int id) throws SQLException;
    TaxReportForm findByForm(String form)throws SQLException;
    boolean insert(TaxReportForm form, Connection connection) throws SQLException;
    boolean update(TaxReportForm form)throws SQLException;
    boolean delete(TaxReportForm form)throws SQLException;

}
