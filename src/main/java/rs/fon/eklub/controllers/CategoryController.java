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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.envelopes.ServiceResponse;

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
    
    @PreAuthorize(value = "#oauth2.hasAnyScope('global')")
    @RequestMapping(value = ServiceAPI.Category.GET_ALL_CATEGORIES,
                    method = RequestMethod.GET)
    public ResponseEntity getAllCategories() throws ServiceException {
        List<Category> categories = interactor.getAllCategories();
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(categories.size() <= 0) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<List<Category>> response = new ServiceResponse(); 
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Category.GET_ALL_CATEGORIES);
        response.setPayload(categories);
        return new ResponseEntity<>(response, httpStatus);
    } 
}
