package ua.training.model.dao.mysql.interfaces;

import ua.training.model.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

    ArrayList<User> findAll() throws SQLException;
    User findById(int id) throws SQLException;
    User finByLogin(String login) throws SQLException;
    boolean insert(User user, Connection connection)throws SQLException;
    boolean update(User user) throws SQLException;
    boolean delete(User user)throws SQLException;
}
