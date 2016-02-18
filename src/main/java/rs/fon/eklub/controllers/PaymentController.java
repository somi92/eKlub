/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.PaymentService;
import rs.fon.eklub.envelopes.ServiceResponse;

/**
 *
 * @author milos
 */
@RestController
@Controller
public class PaymentController {
    
    private PaymentService interactor;
    
    @Autowired
    public PaymentController(PaymentService interactor) {
        this.interactor = interactor;
    }
    
    @RequestMapping(value = ServiceAPI.Payment.POST_SAVE_PAYMENTS,
                    method = RequestMethod.POST,
                    headers = {ServiceAPI.Headers.CONTENT_TYPE})
    public ResponseEntity savePayments(@RequestBody List<Payment> payments) throws ServiceException {
        interactor.savePayments(payments);
        ServiceResponse<String> response = new ServiceResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED);
        response.setRequestUri(ServiceAPI.Payment.POST_SAVE_PAYMENTS);
        response.setPayload(payments.size() + " payments saved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = ServiceAPI.Payment.POST_SEARCH_PAYMENTS,
                    method = RequestMethod.POST,
                    headers = ServiceAPI.Headers.CONTENT_TYPE)
    public ResponseEntity getPayments(@RequestBody HashMap<String, String> searchCriteria) throws ServiceException {
        List<Payment> payments = interactor.getPayments(searchCriteria);
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(payments.size() <= 0) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<List<Payment>> response = new ServiceResponse<>();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Payment.POST_SEARCH_PAYMENTS);
        response.setPayload(payments);
        return new ResponseEntity<>(response, httpStatus);
    }
}
