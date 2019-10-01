package ua.training.model.services;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    private final Logger logger = LoggerLoader.getLogger(UserService.class);
    private static UserService userService;
    private MySQLDAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    private UserService (){}

    public static UserService getInstance(){
        if (userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public User login(String login, String password){
        User user = getUserByLogin(login);
        if (user!= null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    public User getUserByLogin(String login){
        User user;
        try {
            user = mySQLDAOFactory.getUserDAO().finByLogin(login);
            return user;
        }catch (SQLException e){
            logger.error("Failed to get user by login ", e);
            return null;
        }
    }

    public boolean register(String login, String password){
        User user = new User(login, password);
        return RegisterManager.getInstance().registerUser(user);
    }

    public User getUserById(int id){
        try {
            return mySQLDAOFactory.getUserDAO().findById(id);
        }catch (SQLException e){
            logger.error("Failed to get user by id", e);
            return null;
        }
    }

    public ArrayList<User> getAll(){
        try {
            return mySQLDAOFactory.getUserDAO().findAll();
        }catch (SQLException e){
            logger.error("Failed to get all users", e);
            return null;
        }
    }

    public boolean updateUser(int id, String login, String password){
        User user = new User(id, login, password);
        try {
            mySQLDAOFactory.getUserDAO().update(user);
            return true;
        }catch (SQLException e){
            logger.error("Failed to update user", e);
            return false;
        }
    }

    public boolean deleteUser(User user){
        try {
            mySQLDAOFactory.getUserDAO().delete(user);
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete user", e);
            return false;
        }
    }
}
