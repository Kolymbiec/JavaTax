package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.TaxReportType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TaxReportTypeDAO {
    ArrayList<TaxReportType> findAll() throws SQLException;
    TaxReportType findById(int id) throws SQLException;
    TaxReportType findByNumber(int number) throws SQLException;
    TaxReportType findByName(String name) throws SQLException;
    boolean insert(TaxReportType type, Connection connection) throws SQLException;
    boolean update(TaxReportType type) throws SQLException;
    boolean delete(TaxReportType type) throws SQLException;
}
