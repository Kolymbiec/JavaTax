package ua.training.model.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.model.dao.daoFactory.DAOFactory;
import ua.training.model.dao.daoFactory.MySQLDAOFactory;
import ua.training.model.dao.mysql.interfaces.InspectorDAO;
import ua.training.model.entities.Inspector;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOFactory.class})
public class InspectorServiceTest {

    @InjectMocks
    private InspectorService inspectorService;

    @Mock
    private MySQLDAOFactory mySQLDAOFactory;

    @Mock
    private InspectorDAO inspectorDAO;

    private Inspector inspector = new Inspector();

    @Before
    public void setUp(){
       /* inspector.setId(0);
        inspector.setLogin("login");
        inspector.setPassword("password");
        inspector.setSurname("Surname");

        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getMySQLDAOFactory()).thenReturn(mySQLDAOFactory);*/
    }


    @Test
    public void login() throws SQLException {
       /* when(MySQLDAOFactory.getMySQLDAOFactory().geInspectorDAO()).thenReturn(inspectorDAO);
        when(inspectorDAO.findByLogin(inspector.getLogin())).thenReturn(inspector);
        assertNotNull(inspectorService.login(inspector.getLogin(), inspector.getPassword()));*/
    }

    @Test
    public void loginFailedInspectorNull() throws SQLException{
      /*  when(mySQLDAOFactory.geInspectorDAO()).thenReturn(inspectorDAO);
        when(inspectorDAO.findByLogin(inspector.getLogin())).thenReturn(null);
        assertNull(inspectorService.login(inspector.getLogin(), inspector.getPassword()));*/

    }

    @Test
    public void loginFailedNotEqualsPassword() throws SQLException {
       /* when(mySQLDAOFactory.geInspectorDAO()).thenReturn(inspectorDAO);
        when(inspectorDAO.findByLogin(inspector.getLogin())).thenReturn(inspector);
        assertNull(inspectorService.login(inspector.getLogin(), ""));*/
    }
    @Test
    public void register() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getInspectorByLogin()throws SQLException {
       /* when(mySQLDAOFactory.geInspectorDAO()).thenReturn(inspectorDAO);
        when(inspectorDAO.findByLogin(inspector.getLogin())).thenReturn(inspector);
        assertNotNull(inspectorService.getInspectorByLogin(inspector.getLogin()));*/
    }

    @Test
    public void getUserByLoginFailed() throws SQLException{
        /*when(mySQLDAOFactory.geInspectorDAO()).thenReturn(inspectorDAO);
        when(inspectorDAO.findByLogin(inspector.getLogin())).thenThrow(new SQLException());
        assertNull(inspectorService.getInspectorByLogin(inspector.getLogin()));*/
    }

    @Test
    public void getInspectorBySurname() {
    }

    @Test
    public void updateInspector() {
    }

    @Test
    public void deleteInspector() {
    }
}