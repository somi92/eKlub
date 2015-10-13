/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.CategoryService;

/**
 *
 * @author milos
 */
public class CategoryInteractor implements CategoryService {

    private DataAccessService<Category> dao;
    
    public CategoryInteractor() {
    }

    public CategoryInteractor(DataAccessService<Category> dao) {
        this.dao = dao;
    }
    
    public DataAccessService<Category> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<Category> dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Category> getAllCategories() throws ServiceException {
        List<Category> categories = dao.getAllEntities();
        return categories;
    }
}
