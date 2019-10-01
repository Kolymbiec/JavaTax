package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.TaxpayerType;

import java.sql.Connection;
import java.sql.SQLException;

public interface TaxpayerTypeDAO {

    TaxpayerType findById(int id) throws SQLException;
    TaxpayerType findByType(String type) throws SQLException;
    boolean insert(TaxpayerType type, Connection connection) throws SQLException;
    boolean update(TaxpayerType type) throws SQLException;
    boolean delete(TaxpayerType type) throws SQLException;
}
