package ua.training.model.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.TaxpayerDAO;
import ua.training.model.entities.Taxpayer;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOFactory.class})
public class TaxpayerServiceTest {

    @InjectMocks
    private TaxpayerService taxpayerService;

    @Mock
    private MySQLDAOFactory mySQLDAOFactory;

    @Mock
    TaxpayerDAO taxpayerDAO;

    private Taxpayer taxpayer = new Taxpayer();

    @Before
    public void setUp(){

    }

    @Test
    public void login() {
    }

    @Test
    public void getTaxpayerByLogin() {
    }
}