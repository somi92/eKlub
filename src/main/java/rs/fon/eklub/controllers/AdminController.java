/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.AdminService;
import rs.fon.eklub.envelopes.ServiceResponse;

/**
 *
 * @author milos
 */
@RestController
@Component
public class AdminController {
    
    private AdminService interactor;
    
    @Autowired
    public AdminController(AdminService interactor) {
        this.interactor = interactor;
    }
    
    @PreAuthorize(value = "#oauth2.hasAnyScope('global')")
    @RequestMapping(value = ServiceAPI.Admin.POST_GET_ADMIN,
                    headers = ServiceAPI.Headers.CONTENT_TYPE,
                    method = RequestMethod.POST)
    public ResponseEntity getAdmin(@RequestBody Employee admin) throws ServiceException {
        Employee employee = interactor.getAdmin(admin.getUsername());
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(employee == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<Employee> response = new ServiceResponse();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Admin.POST_GET_ADMIN);
        response.setPayload(employee);
        return new ResponseEntity<>(response, httpStatus);
    }
}
