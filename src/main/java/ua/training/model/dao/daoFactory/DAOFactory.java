package ua.training.model.dao.daoFactory;

import ua.training.model.dao.mysql.interfaces.*;

public abstract class DAOFactory {

    public static MySQLDAOFactory getMySQLDAOFactory(){
        return new MySQLDAOFactory();
    }

    public abstract UserDAO getUserDAO();
    public abstract InspectorDAO getInspectorDAO();
    public abstract TaxpayerTypeDAO getTaxpayerTypeDAO();
    public abstract TaxpayerDAO getTaxpayerDAO();
    public abstract TaxReportFormDAO getTaxReportFormDAO();
    public abstract TaxReportTypeDAO getTaxReportTypeDAO();
    public abstract TaxReportStatusDAO getTaxReportStatusDAO();
    public abstract TaxReportDAO getTaxReportDao();

}
