/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.repositories.mocks;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockCategoryExceptionRepository implements DataAccessService<Category> {

    @Override
    public Category getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAllEntities() throws DataAccessServiceException {
        throw new DataAccessServiceException("Data access error!");
    }

    @Override
    public List<Category> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertOrUpdateEntity(Category entity) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
