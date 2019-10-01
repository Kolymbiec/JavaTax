package ua.training.model.services;

import org.apache.log4j.Logger;
import ua.training.logging.LoggerLoader;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.TaxReportDAO;
import ua.training.model.entities.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TaxReportService {

    private final Logger logger = LoggerLoader.getLogger(TaxReportService.class);
    private static TaxReportService taxReportService;
    private MySQLDAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    private TaxReportService (){}

    public static TaxReportService getInstance(){
        if (taxReportService == null){
            taxReportService = new TaxReportService();
        }
       return taxReportService;
    }

    public TaxReport getTaxReportById(int id){
        try {
            return mySQLDAOFactory.getTaxReportDao().findById(id);
        }catch (SQLException e){
            logger.error("Failed to get tax report by id ", e);
            return null;
        }
    }

    public ArrayList<TaxReport> getAllTaxReports(int currentPage, int recordsPerPage){
        TaxReportDAO taxReportDAO = mySQLDAOFactory.getTaxReportDao();
        int start =currentPage * recordsPerPage - recordsPerPage;
        try {
            return taxReportDAO.findAll(start, recordsPerPage);
        }catch (SQLException e){
            logger.error("Failed to get all tax reports ", e);
            return null;
        }
    }

    public ArrayList<TaxReport> getByInspector(String inspectorLogin){
        Inspector inspector = null;
        try {
            inspector = mySQLDAOFactory.getInspectorDAO().findByLogin(inspectorLogin);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ", e);
        }
        try {
            return mySQLDAOFactory.getTaxReportDao().findByInspector(inspector);
        }catch (SQLException e){
            logger.error("Failed to get tax reports by inspector ", e);
            return null;
        }
    }

    public ArrayList<TaxReport> getByTaxpayer(String taxpayerLogin){
        Taxpayer taxpayer = null;
        try {
            taxpayer = mySQLDAOFactory.getTaxpayerDAO().findByLogin(taxpayerLogin);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer by login");
        }
        try {
            return mySQLDAOFactory.getTaxReportDao().findByTaxpayer(taxpayer);
        }catch (SQLException e){
            logger.error("Failed to get tax reports by taxpayer ", e);
            return null;
        }
    }

    public ArrayList<TaxReport> getByTaxReportForm(String form){
        TaxReportForm reportForm = null;
        try {
            reportForm = mySQLDAOFactory.getTaxReportFormDAO().findByForm(form);
        }catch (SQLException e){
            logger.error("Failed to get tax report form by form ", e);
        }
        try {
            return mySQLDAOFactory.getTaxReportDao().findByForm(reportForm);
        }catch (SQLException e){
            logger.error("Failed to get tax reports by form ", e);
            return null;
        }
    }

    public ArrayList<TaxReport> getByTaxReportType(String type){
        TaxReportType reportType = null;
        try {
            reportType = mySQLDAOFactory.getTaxReportTypeDAO().findByName(type);
        }catch (SQLException e){
            logger.error("Failed to get tax report type by type ", e);
        }
        try {
            return mySQLDAOFactory.getTaxReportDao().findByType(reportType);
        }catch (SQLException e){
            logger.error("Failed to get tax reports by type ", e);
            return null;
        }
    }

    public ArrayList<TaxReport> getByTaxReportStatus(String status){
        TaxReportStatus reportStatus = null;
        try {
            reportStatus = mySQLDAOFactory.getTaxReportStatusDAO().findByStatus(status);
        }catch (SQLException e){
            logger.error("Failed to get tax report status by status");
        }
        try {
            return mySQLDAOFactory.getTaxReportDao().findByStatus(reportStatus);
        }catch (SQLException e){
            logger.error("Failed to get tax reports by status ", e);
            return null;
        }
    }

    public ArrayList<TaxReport> getByTaxReportDate(Timestamp date){
        try {
           return mySQLDAOFactory.getTaxReportDao().findByDate(date);
        }catch (SQLException e){
            logger.error("Failed to get tax reports by date ", e);
            return null;
        }
    }

    public int getNumberOfRows(){
        try {
            return mySQLDAOFactory.getTaxReportDao().selectNumberOfRows();
        }catch (SQLException e){
            logger.error("Failed to get number of rows ", e);
            return 0;
        }
    }

    public boolean createTaxReport(String taxpayerLogin, String inspectorLogin, String form, String type, String status, String message, Timestamp date){
        Taxpayer taxpayer;
        try {
            taxpayer = mySQLDAOFactory.getTaxpayerDAO().findByLogin(taxpayerLogin);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer by login");
            return false;
        }
        Inspector inspector;
        try {
            inspector = mySQLDAOFactory.getInspectorDAO().findByLogin(inspectorLogin);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ", e);
            return false;
        }
        TaxReportForm reportForm;
        try {
            reportForm = mySQLDAOFactory.getTaxReportFormDAO().findByForm(form);
        }catch (SQLException e){
            logger.error("Failed to get tax report form by form ", e);
            return false;
        }
        TaxReportType reportType;
        try {
            reportType = mySQLDAOFactory.getTaxReportTypeDAO().findByName(type);
        }catch (SQLException e){
            logger.error("Failed to get tax report type by type ", e);
            return false;
        }
        TaxReportStatus reportStatus;
        try {
            reportStatus = mySQLDAOFactory.getTaxReportStatusDAO().findByStatus(status);
        }catch (SQLException e){
            logger.error("Failed to get tax report status by status");
            return false;
        }
        TaxReport taxReport = new TaxReport(taxpayer,inspector,reportForm,reportType,reportStatus,message,date);
        try {
            mySQLDAOFactory.getTaxReportDao().insert(taxReport);
            return true;
        }catch (SQLException e){
            logger.error("Failed to create tax report ", e);
            return false;
        }
    }

    public boolean updateTaxReport(int id, String taxpayerLogin, String inspectorLogin, String form, String type, String status, String message, Timestamp date){
        Taxpayer taxpayer;
        try {
            taxpayer = mySQLDAOFactory.getTaxpayerDAO().findByLogin(taxpayerLogin);
        }catch (SQLException e){
            logger.error("Failed to get taxpayer by login");
            return false;
        }
        Inspector inspector;
        try {
            inspector = mySQLDAOFactory.getInspectorDAO().findByLogin(inspectorLogin);
        }catch (SQLException e){
            logger.error("Failed to get inspector by login ", e);
            return false;
        }
        TaxReportForm reportForm;
        try {
            reportForm = mySQLDAOFactory.getTaxReportFormDAO().findByForm(form);
        }catch (SQLException e){
            logger.error("Failed to get tax report form by form ", e);
            return false;
        }
        TaxReportType reportType;
        try {
            reportType = mySQLDAOFactory.getTaxReportTypeDAO().findByName(type);
        }catch (SQLException e){
            logger.error("Failed to get tax report type by type ", e);
            return false;
        }
        TaxReportStatus reportStatus;
        try {
            reportStatus = mySQLDAOFactory.getTaxReportStatusDAO().findByStatus(status);
        }catch (SQLException e){
            logger.error("Failed to get tax report status by status");
            return false;
        }
        TaxReport taxReport = new TaxReport(id,taxpayer,inspector,reportForm,reportType,reportStatus,message,date);
        try {
            mySQLDAOFactory.getTaxReportDao().update(taxReport);
            return true;
        }catch (SQLException e){
            logger.error("Failed to update tax report ", e);
            return false;
        }
    }

    public boolean deleteTaxReport(TaxReport taxReport){
        try {
            mySQLDAOFactory.getTaxReportDao().delete(taxReport);
            return true;
        }catch (SQLException e){
            logger.error("Failed to delete tax report ", e);
            return false;
        }
    }


}
