/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.dao.mock.MockCategoryExceptionRepository;
import rs.fon.eklub.dao.mock.MockCategoryRepository;

/**
 *
 * @author milos
 */
public class CategoryInteractorTest {
    
    private CategoryService cs;
    
    private DataAccessService<Category> dao;
    private DataAccessService<Category> daoAllEntitiesError;
    
    public CategoryInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        dao = new MockCategoryRepository();
        daoAllEntitiesError = new MockCategoryExceptionRepository();
        
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
        assertArrayEquals(dao.getAllEntities().toArray(), categories.toArray());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAllCategoriesDataExceptionTest() throws ServiceException {
        cs = new CategoryInteractor(daoAllEntitiesError);
        List<Category> categories = cs.getAllCategories();
        assertNull(categories);
    }
}
