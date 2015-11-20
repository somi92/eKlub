/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.exceptions.ServiceErrorDescriptor;

/**
 *
 * @author milos
 */
@ControllerAdvice
public class ExceptionHandlingController {
    
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ServiceException> categoriesNotFound(HttpServletRequest rq, ServiceException e) {
        return new ResponseEntity(
                new ServiceErrorDescriptor(e.getClass().getSimpleName(), e.getMessage(), rq.getRequestURI()), 
                HttpStatus.NOT_FOUND);
    }
}
