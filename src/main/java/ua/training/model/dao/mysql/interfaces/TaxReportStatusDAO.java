package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.TaxReportStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TaxReportStatusDAO {

    ArrayList<TaxReportStatus> findAll()throws SQLException;
    TaxReportStatus findById(int id) throws SQLException;
    TaxReportStatus findByStatus(String status)throws SQLException;
    boolean insert(TaxReportStatus status, Connection connection)throws SQLException;
    boolean update(TaxReportStatus status)throws SQLException;
    boolean delete(TaxReportStatus status) throws SQLException;
}
