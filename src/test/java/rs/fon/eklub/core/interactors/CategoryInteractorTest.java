/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.CategoryService;

/**
 *
 * @author milos
 */
public class CategoryInteractorTest {
    
    private CategoryService cs;
    
    private DataAccessService<Category> dao;
    private DataAccessService<Category> daoAllEntitiesError;
    
    private List<Category> mockCategoryRepository;
    
    public CategoryInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        mockCategoryRepository = new ArrayList<>();
        Category c1 = new Category(1, "cat1", null);
        Category c2 = new Category(2, "cat2", null);
        Category c3 = new Category(3, "cat3", null);
        mockCategoryRepository.add(c1);
        mockCategoryRepository.add(c2);
        mockCategoryRepository.add(c3);
        
        dao = new DataAccessService<Category>() {

            @Override
            public Category getEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Category> getAllEntities() throws DataAccessServiceException {
                List<Category> categories = mockCategoryRepository;
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
        };
        
        daoAllEntitiesError = new DataAccessService<Category>() {

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
        };
        
        cs = new CategoryInteractor(dao);
    }
    
    @Test
    public void initializeCategoryInteractorOkTest() {
        cs = new CategoryInteractor(dao);
        assertNotNull(cs);
    }
    
    @Test
    public void getAllCategoriesOkTest() throws ServiceException {
        List<Category> categories = cs.getAllCategories();
        assertNotNull(categories);
        assertArrayEquals(mockCategoryRepository.toArray(), categories.toArray());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAllCategoriesDataExceptionTest() throws ServiceException {
        cs = new CategoryInteractor(daoAllEntitiesError);
        List<Category> categories = cs.getAllCategories();
        assertNull(categories);
    }
}
