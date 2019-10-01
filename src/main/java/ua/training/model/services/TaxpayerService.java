package ua.training.model.services;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.InspectorDAO;
import ua.training.model.dao.mysql.interfaces.TaxpayerDAO;
import ua.training.model.entities.Inspector;
import ua.training.model.entities.Taxpayer;
import ua.training.model.entities.TaxpayerType;
import ua.training.model.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaxpayerService {
    private final Logger logger = LoggerLoader.getLogger(TaxpayerService.class);
    private static TaxpayerService taxpayerService;
    private MySQLDAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    private TaxpayerService(){}

    public static TaxpayerService getInstance(){
        if (taxpayerService == null){
            taxpayerService = new TaxpayerService();
        }
        return taxpayerService;
    }

    public Taxpayer login(String login, String password){
        Taxpayer taxpayer = getTaxpayerByLogin(login);
        if (taxpayer != null && taxpayer.getUser().getPassword().equals(password)){
            return taxpayer;
        }
        return null;
    }

    public boolean register(String type, String login, String password, String inspectorLogin, String name, String surname, String email){
        User user = null;
        if (UserService.getInstance().register(login, password)) {
            user = new User(login, password);
        }
        TaxpayerType taxpayerType;
        try {
            taxpayerType = mySQLDAOFactory.getTaxpayerTypeDAO().findByType(type);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer type by type ", e);
            return false;
        }
        InspectorDAO inspectorDAO = mySQLDAOFactory.getInspectorDAO();
        Inspector inspector;
        try {
            inspector = inspectorDAO.findByLogin(inspectorLogin);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ",e);
            return false;
        }
        Taxpayer taxpayer = new Taxpayer(user, taxpayerType, inspector, name, surname, email);
        return RegisterManager.getInstance().registerTaxpayer(taxpayer);
    }

    public Taxpayer getTaxpayerById(int id){
        try {
            return mySQLDAOFactory.getTaxpayerDAO().findById(id);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer by id");
            return null;
        }
    }

    public ArrayList<Taxpayer> getAll(){
        try {
            return mySQLDAOFactory.getTaxpayerDAO().findAll();
        }catch (SQLException e){
            logger.error("Failed to get all taxpayers ", e);
            return null;
        }
    }

    public Taxpayer getTaxpayerByLogin(String login){
        Taxpayer taxpayer;
        try {
            taxpayer = mySQLDAOFactory.getTaxpayerDAO().findByLogin(login);
            return taxpayer;
        }catch (SQLException e){
            logger.error("Failed to get taxpayer by login ", e);
            return null;
        }
    }

    public Taxpayer getTaxpayerBySurname(String surname){
        TaxpayerDAO taxpayerDAO = mySQLDAOFactory.getTaxpayerDAO();
        try {
            return taxpayerDAO.findBySurname(surname);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer by surname ", e);
            return null;
        }
    }

    public ArrayList<Taxpayer> getTaxpayersByInspector(String inspectorLogin){
        InspectorDAO inspectorDAO = mySQLDAOFactory.getInspectorDAO();
        Inspector inspector = null;
        try {
            inspector = inspectorDAO.findByLogin(inspectorLogin);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ",e);
        }
        TaxpayerDAO taxpayerDAO = mySQLDAOFactory.getTaxpayerDAO();
        try {
            return taxpayerDAO.findByInspector(inspector);
        }catch (SQLException e){
            logger.error("Failed to get taxpayers by inspector", e);
            return null;
        }
    }

    public ArrayList<Taxpayer> getTaxpayersByTaxpayerType(String type){
        TaxpayerType taxpayerType = null;
        try {
            taxpayerType = mySQLDAOFactory.getTaxpayerTypeDAO().findByType(type);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer type by type ", e );
        }
        TaxpayerDAO taxpayerDAO = mySQLDAOFactory.getTaxpayerDAO();
        try {
            return taxpayerDAO.findByTaxpayerType(taxpayerType);
        }catch (SQLException e){
            logger.error("Failed to get taxpayers by type", e);
            return null;
        }
    }

    public boolean changeTaxpayerInspector(String inspectorLogin, String taxpayerLogin){
        Inspector inspector;
        try {
            inspector =  mySQLDAOFactory.getInspectorDAO().findByLogin(inspectorLogin);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ",e);
            return false;
        }
        Taxpayer taxpayer = getTaxpayerByLogin(taxpayerLogin);
        taxpayer.setInspector(inspector);
        try {
            mySQLDAOFactory.getTaxpayerDAO().update(taxpayer);
            return true;
        }catch (SQLException e){
            logger.error("Failed to change inspector ",e);
            return false;
        }
    }

    public boolean updateTaxpayer(int id, String type, String login, String password, String inspectorLogin, String name, String surname, String email){
        User user;
        try {
            user = mySQLDAOFactory.getUserDAO().finByLogin(login);
            user.setPassword(password);
        }catch (SQLException e){
            logger.error("Failed to get user by login", e);
            return false;
        }
        TaxpayerType taxpayerType;
        try {
            taxpayerType = mySQLDAOFactory.getTaxpayerTypeDAO().findByType(type);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer type by type ", e);
            return false;
        }
        InspectorDAO inspectorDAO = mySQLDAOFactory.getInspectorDAO();
        Inspector inspector;
        try {
            inspector = inspectorDAO.findByLogin(inspectorLogin);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ",e);
            return false;
        }
        TaxpayerDAO taxpayerDAO = mySQLDAOFactory.getTaxpayerDAO();
        Taxpayer taxpayer = new Taxpayer(id, user, taxpayerType, inspector, name, surname, email);
        try {
            taxpayerDAO.update(taxpayer);
            return true;
        }catch (SQLException e){
            logger.error("Failed to update taxpayer ", e);
            return false;
        }
    }

    public boolean deleteTaxpayer(Taxpayer taxpayer){
        TaxpayerDAO taxpayerDAO = mySQLDAOFactory.getTaxpayerDAO();
        try {
            mySQLDAOFactory.getUserDAO().delete(taxpayer.getUser());
            taxpayerDAO.delete(taxpayer);
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete taxpayer ", e);
            return false;
        }
    }


}
