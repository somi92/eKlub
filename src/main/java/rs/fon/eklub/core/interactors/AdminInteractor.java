/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.AdminService;

/**
 *
 * @author milos
 */
public class AdminInteractor implements AdminService {

    private DataAccessService<Employee> dao;

    public AdminInteractor() {
    }
    
    public AdminInteractor(DataAccessService<Employee> dao) {
        this.dao = dao;
    }
    
    public DataAccessService<Employee> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<Employee> dao) {
        this.dao = dao;
    }

    @Override
    public Employee getAdmin(String username, String password) throws ServiceException {
        Map<String, Object> searchCriteria = new HashMap<>();
        searchCriteria.put("username", username);
        searchCriteria.put("password", password);
        List<Employee> employees = dao.getEntities(searchCriteria);
        if(employees == null || employees.isEmpty()) {
            return null;
        }
        return employees.get(0);
    }
}