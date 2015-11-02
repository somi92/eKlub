/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.repositories.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockCategoryRepository implements DataAccessService<Category> {
    
    private List<Category> categories;
    
    public MockCategoryRepository() {
        Category c1 = new Category(1, "cat1", null);
        Category c2 = new Category(2, "cat2", null);
        Category c3 = new Category(3, "cat3", null);
        categories = new ArrayList<>();
        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
    }

    @Override
    public Category getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAllEntities() throws DataAccessServiceException {
        return categories;
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
