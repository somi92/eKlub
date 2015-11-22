/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.response.ServiceResponse;

/**
 *
 * @author milos
 */
@RestController
@Component
public class CategoryController {
    
    private CategoryService interactor;
    
    @Autowired
    public CategoryController(CategoryService interactor) {
        this.interactor = interactor;
    }
    
//    @RequestMapping(value = ServiceAPI.Category.GET_ALL_CATEGORIES,
//                    method = RequestMethod.GET)
//    @ResponseBody
//    public ServiceResponse<List<Category>> getAllCategories() throws ServiceException {
//        List<Category> categories = interactor.getAllCategories();
//        ServiceResponse<List<Category>> response = new ServiceResponse();
//        response.setResponseStatus("OK");
//        response.setResponseUri(ServiceAPI.Category.GET_ALL_CATEGORIES);
//        response.setResponseContent(categories);
//        return response;
//    } 
    
    @RequestMapping(value = ServiceAPI.Category.GET_ALL_CATEGORIES,
                    method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse<List<Category>>> getAllCategories() throws ServiceException {
        List<Category> categories = interactor.getAllCategories();
        ServiceResponse<List<Category>> response = new ServiceResponse();
        response.setResponseStatus("OK");
        response.setResponseUri(ServiceAPI.Category.GET_ALL_CATEGORIES);
        response.setResponseContent(categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } 
}
