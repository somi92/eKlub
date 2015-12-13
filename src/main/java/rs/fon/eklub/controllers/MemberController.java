/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MemberService;
import rs.fon.eklub.envelopes.ServiceResponse;

/**
 *
 * @author milos
 */
@RestController
@Component
public class MemberController {
    
    private MemberService interactor;

    @Autowired
    public MemberController(MemberService interactor) {
        this.interactor = interactor;
    }
    
    @RequestMapping(value = ServiceAPI.Member.POST_SAVE_MEMBER,
                    method = RequestMethod.POST,
                    headers = {ServiceAPI.Headers.CONTENT_TYPE})
    public ResponseEntity saveMember(@RequestBody Member member) throws ServiceException {
        interactor.saveMember(member);
        ServiceResponse<String> response = new ServiceResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED);
        response.setRequestUri(ServiceAPI.Member.POST_SAVE_MEMBER);
        response.setPayload("Member "+member.getId()+" saved.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = ServiceAPI.Member.GET_MEMBER_BY_ID,
                    method = RequestMethod.GET)
    public ResponseEntity getMemberById(@PathVariable long id) throws ServiceException {
        Member member = interactor.getMemberById(id);
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(member == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<Member> response = new ServiceResponse();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Member.GET_MEMBER_BY_ID);
        response.setPayload(member);
        return new ResponseEntity<>(response, httpStatus);
    }
    
    @RequestMapping(value = ServiceAPI.Member.DELETE_MEMBER_BY_ID,
                    method = RequestMethod.DELETE)
    public ResponseEntity deleteMemberById(@PathVariable long id) throws ServiceException {
        boolean result = interactor.deleteMember(id);
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(result == true) {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_DELETED;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        }
        ServiceResponse<Boolean> response = new ServiceResponse();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Member.DELETE_MEMBER_BY_ID);
        response.setPayload(result);
        return new ResponseEntity<>(response, httpStatus);
    }
}
