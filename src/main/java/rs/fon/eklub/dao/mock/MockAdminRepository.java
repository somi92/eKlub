/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockAdminRepository implements DataAccessService<Employee> {

    private List<Employee> mockEmployeeRepository = new ArrayList<>();

    public MockAdminRepository() {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setUsername("admin1");
        e1.setPassword("password1");
        Employee e3 = new Employee();
        e3.setId(3);
        e3.setUsername("admin3");
        e3.setPassword("password3");

        mockEmployeeRepository.add(e1);
        mockEmployeeRepository.add(e3);
    }

    @Override
    public Employee getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getAllEntities() throws DataAccessServiceException {
        return mockEmployeeRepository;
    }

    @Override
    public List<Employee> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        List<Employee> employees = new ArrayList<>();
        if (searchCriteria.get("username").equals("admin13")) {
            throw new DataAccessServiceException("Data access error!");
        }
        for (Employee e : mockEmployeeRepository) {
            if (e.getUsername().equals(searchCriteria.get("username"))) {
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

}
