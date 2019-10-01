package ua.training.model.services;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.InspectorDAO;
import ua.training.model.entities.Inspector;
import ua.training.model.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class InspectorService {
    private final Logger logger = LoggerLoader.getLogger(InspectorService.class);
    private static InspectorService inspectorService;
    private MySQLDAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    private InspectorService(){}

    public static InspectorService getInstance(){
        if (inspectorService == null){
            inspectorService = new InspectorService();
        }
        return inspectorService;
    }

    public Inspector login(String login, String password){
        Inspector inspector = getInspectorByLogin(login);
        if (inspector != null && inspector.getUser().getPassword().equals(password)){
            return inspector;
        }
        return null;
    }

    public ArrayList<Inspector> getAll(){
        try {
            return mySQLDAOFactory.getInspectorDAO().findAll();
        }catch (SQLException e){
            logger.error("Failed to get all inspectors ", e);
            return null;
        }
    }
    public Inspector getInspectorByLogin(String login){
        try {
            return mySQLDAOFactory.getInspectorDAO().findByLogin(login);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ", e);
            return null;
        }
    }

    public Inspector getInspectorBySurname(String surname){
        try {
            return mySQLDAOFactory.getInspectorDAO().findBySurname(surname);
        }catch (SQLException e){
            logger.error("Failed to get inspector by surname ", e);
            return null;
        }
    }

    public boolean updateInspector(int id, String login, String password, String name, String surname, String email){
        Inspector inspector = new Inspector(new User(id, login, password),name,surname,email);
        try {
            mySQLDAOFactory.getInspectorDAO().update(inspector);
            return true;
        }catch (SQLException e){
            logger.error("failed to update inspector ", e);
            return false;
        }
    }

    public boolean deleteInspector(Inspector inspector){
        try {
            mySQLDAOFactory.getUserDAO().delete(inspector.getUser());
            mySQLDAOFactory.getInspectorDAO().delete(inspector);
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete inspector ", e);
            return false;
        }
    }

}
