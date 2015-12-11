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
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.envelopes.ServiceErrorResponse;

/**
 *
 * @author milos
 */
@ControllerAdvice
public class ExceptionHandlingController {
    
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ServiceException> handleValidationException(HttpServletRequest rq, ServiceException e) {
        ServiceErrorResponse error = new ServiceErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.toString());
        error.setErrorType(e.getClass().getName());
        error.setErrorMessage(e.getMessage());
        error.setRequestUri(rq.getRequestURI());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DataAccessServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ServiceException> handleDataAccessServiceException(HttpServletRequest rq, ServiceException e) {
        ServiceErrorResponse error = new ServiceErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.setErrorType(e.getClass().getName());
        error.setErrorMessage(e.getMessage());
        error.setRequestUri(rq.getRequestURI());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ServiceException> handleServiceException(HttpServletRequest rq, ServiceException e) {
        ServiceErrorResponse error = new ServiceErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.toString());
        error.setErrorType(e.getClass().getName());
        error.setErrorMessage(e.getMessage());
        error.setRequestUri(rq.getRequestURI());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
