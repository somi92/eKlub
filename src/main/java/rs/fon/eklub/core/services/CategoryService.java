/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.ServiceException;

/**
 *
 * @author milos
 */
public interface CategoryService {
    
    public List<Category> getAllCategories() throws ServiceException;
    
}
