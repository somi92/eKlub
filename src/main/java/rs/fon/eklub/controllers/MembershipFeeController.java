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
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MembershipFeeService;
import rs.fon.eklub.envelopes.ServiceResponse;

/**
 *
 * @author milos
 */
@RestController
@Component
public class MembershipFeeController {
    
    private MembershipFeeService interactor;
    
    @Autowired
    public MembershipFeeController(MembershipFeeService interactor) {
        this.interactor = interactor;
    }
    
    @RequestMapping(value = ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES,
                    method = RequestMethod.GET)
    public ResponseEntity getAllMembershipFees() throws ServiceException {
        List<MembershipFee> membershipFees = interactor.getAllMembershipFees();
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(membershipFees.size() <= 0) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<List<MembershipFee>> response = new ServiceResponse<>();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES);
        response.setPayload(membershipFees);
        return new ResponseEntity<>(response, httpStatus);
    }
}
