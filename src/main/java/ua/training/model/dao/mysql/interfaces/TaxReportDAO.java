package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface TaxReportDAO {
    ArrayList<TaxReport> findAll(int start, int recordsPerPage) throws SQLException;
    TaxReport findById(int id) throws SQLException;
    ArrayList<TaxReport> findByInspector(Inspector inspector) throws SQLException;
    ArrayList<TaxReport> findByTaxpayer(Taxpayer taxpayer) throws SQLException;
    ArrayList<TaxReport> findByForm(TaxReportForm form)throws SQLException;
    ArrayList<TaxReport> findByType(TaxReportType type) throws SQLException;
    ArrayList<TaxReport> findByStatus(TaxReportStatus status) throws SQLException;
    ArrayList<TaxReport> findByDate(Timestamp date) throws SQLException;
    int selectNumberOfRows() throws SQLException;
    boolean insert(TaxReport taxReport) throws SQLException;
    boolean update(TaxReport taxReport) throws SQLException;
    boolean delete(TaxReport taxReport) throws SQLException;
}
