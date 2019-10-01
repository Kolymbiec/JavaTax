package ua.training.model.dao.daoFactory;

import ua.training.model.dao.mysql.impl.*;
import ua.training.model.dao.mysql.interfaces.*;

public class MySQLDAOFactory extends DAOFactory {


    @Override
    public UserDAO getUserDAO() {
        return MySQLUserDAO.getInstance();
    }

    @Override
    public InspectorDAO getInspectorDAO() {
        return MySQLInspectorDAO.getInstance();
    }

    @Override
    public TaxpayerTypeDAO getTaxpayerTypeDAO() {
        return MySQLTaxpayerTypeDAO.getInstance();
    }

    @Override
    public TaxpayerDAO getTaxpayerDAO() {
        return MySQLTaxpayerDAO.getInstance();
    }

    @Override
    public TaxReportFormDAO getTaxReportFormDAO() {
        return MySQLTaxReportFormDAO.getInstance();
    }

    @Override
    public TaxReportTypeDAO getTaxReportTypeDAO() {
        return MySQLTaxReportTypeDAO.getInstance();
    }

    @Override
    public TaxReportStatusDAO getTaxReportStatusDAO() {
        return MySQLTaxReportStatusDAO.getInstance();
    }

    @Override
    public TaxReportDAO getTaxReportDao() {
        return MySQLTaxReportDAO.getInstance();
    }
}
