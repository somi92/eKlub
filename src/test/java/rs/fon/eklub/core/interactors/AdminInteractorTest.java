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

/**
 *
 * @author milos
 */
public class AdminInteractorTest {
    
    private AdminService as;
    
    private DataAccessService<Employee> dao;
    private EntityValidator<Employee> validator;
    
    private List<Employee> mockEmployeeRepository;
    
    public AdminInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        mockEmployeeRepository = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setUsername("admin1");
        e1.setPassword("password1");
        Employee e2 = new Employee();
        e2.setId(2);
        e2.setUsername("admin2");
        e2.setPassword("password2");
        Employee e3 = new Employee();
        e3.setId(3);
        e3.setUsername("admin3");
        e3.setPassword("password3");
        
        mockEmployeeRepository.add(e1);
        mockEmployeeRepository.add(e2);
        mockEmployeeRepository.add(e3);
        
        dao = new DataAccessService<Employee>() {

            @Override
            public Employee getEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Employee> getAllEntities() throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Employee> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
                List<Employee> employees = new ArrayList<>();
                if(searchCriteria.get("username").equals("admin13")) {
                    throw new DataAccessServiceException("Data access error!");
                }
                for(Employee e : mockEmployeeRepository) {
                    if(e.getUsername().equals(searchCriteria.get("username")) 
                            && e.getPassword().equals(searchCriteria.get("password"))) {
                        employees.add(e);
                    }
                }
                return employees;
            }

            @Override
            public void insertOrUpdateEntity(Employee entity) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        validator = new EntityValidator<Employee>() {

            @Override
            public boolean validateEntity(Employee entity) throws ValidationException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
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
        assertTrue(mockEmployeeRepository.contains(employee));
    }
    
    @Test
    public void getAdminNotFoundTest() throws ServiceException {
        Employee employee = as.getAdmin("admin2", "password3");
        assertNull(employee);
        assertFalse(mockEmployeeRepository.contains(employee));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAdminDataExceptionTest() throws ServiceException {
        Employee employee = as.getAdmin("admin13", "password13");
        assertNull(employee);
        assertFalse(mockEmployeeRepository.contains(employee));
    }
}
