/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.exceptions.ServiceErrorDescriptor;

/**
 *
 * @author milos
 */
@RestController
@Component
public class CategoryController {
    
    @Autowired
    private CategoryService interactor;
    
    @RequestMapping(ServiceAPI.GET_ALL_CATEGORIES)
    @ResponseBody
    public List<Category> getAllCategories() throws ServiceException {
        List<Category> categories = interactor.getAllCategories();
        return categories;
    }
    
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ServiceException> categoriesNotFound(HttpServletRequest rq, ServiceException e) {
        return new ResponseEntity(
                new ServiceErrorDescriptor(e.getClass().getSimpleName(), e.getMessage(), rq.getRequestURI()), 
                HttpStatus.NOT_FOUND);
    }         
}
