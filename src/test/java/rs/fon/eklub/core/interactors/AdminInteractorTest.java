/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.AdminService;
import rs.fon.eklub.core.validators.EntityValidator;
import rs.fon.eklub.core.validators.MockAdminValidator;
import rs.fon.eklub.repositories.mocks.MockAdminRepository;

/**
 *
 * @author milos
 */
public class AdminInteractorTest {
    
    private AdminService as;
    
    private DataAccessService<Employee> dao;
    private EntityValidator<Employee> validator;
    
    public AdminInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        dao = new MockAdminRepository();
        validator = new MockAdminValidator();
        
        as = new AdminInteractor(dao);
    }
    
    @Test
    public void initializeAdminInteractorOkTest() {
        as = new AdminInteractor(dao);
        assertNotNull(as);
    }
    
    @Test
    public void getAdminOkTest() throws ServiceException {
        Employee employee = as.getAdmin("admin3", "password3");
        assertNotNull(employee);
        assertTrue(dao.getAllEntities().contains(employee));
    }
    
    @Test
    public void getAdminNotFoundTest() throws ServiceException {
        Employee employee = as.getAdmin("admin2", "password3");
        assertNull(employee);
        assertFalse(dao.getAllEntities().contains(employee));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAdminDataExceptionTest() throws ServiceException {
        Employee employee = as.getAdmin("admin13", "password13");
        assertNull(employee);
        assertFalse(dao.getAllEntities().contains(employee));
    }
}
