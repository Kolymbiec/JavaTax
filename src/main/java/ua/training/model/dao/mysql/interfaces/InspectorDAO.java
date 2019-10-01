package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.Inspector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InspectorDAO {
    ArrayList<Inspector> findAll() throws SQLException;
    Inspector findById(int id) throws SQLException;
    Inspector findBySurname(String surname) throws SQLException;
    Inspector findByLogin(String login) throws SQLException;
    boolean insert(Inspector inspector, Connection connection)throws SQLException;
    boolean update(Inspector inspector) throws SQLException;
    boolean delete(Inspector inspector)throws SQLException;

}
