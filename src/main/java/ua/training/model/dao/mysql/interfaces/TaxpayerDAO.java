package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.Inspector;
import ua.training.model.entities.Taxpayer;
import ua.training.model.entities.TaxpayerType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TaxpayerDAO {
    ArrayList<Taxpayer> findAll() throws SQLException;
    Taxpayer findById(int id) throws SQLException;
    Taxpayer findByLogin(String login) throws SQLException;
    Taxpayer findBySurname(String surname) throws SQLException;
    ArrayList<Taxpayer> findByInspector(Inspector inspector) throws SQLException;
    ArrayList<Taxpayer> findByTaxpayerType(TaxpayerType type) throws SQLException;
    boolean insert(Taxpayer taxpayer, Connection connection)throws SQLException;
    boolean update(Taxpayer taxpayer)throws SQLException;
    boolean delete(Taxpayer taxpayer) throws SQLException;
}
