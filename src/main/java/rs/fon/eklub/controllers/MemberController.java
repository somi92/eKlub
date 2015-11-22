/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MemberService;
import rs.fon.eklub.response.ServiceResponse;

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
    @ResponseBody
    public ServiceResponse<String> saveMember(@RequestBody Member member) throws ServiceException {
        interactor.saveMember(member);
        ServiceResponse<String> response = new ServiceResponse();
        response.setResponseStatus("OK");
        response.setResponseUri(ServiceAPI.Member.POST_SAVE_MEMBER);
        response.setResponseContent("Member "+member.getId()+" saved.");
        return response;
    }
    
    @RequestMapping(value = ServiceAPI.Member.GET_MEMBER_BY_ID,
                    method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<Member> getMemberById(@PathVariable long id) throws Exception {
        Member member = interactor.getMemberById(id);
        ServiceResponse<Member> response = new ServiceResponse();
        response.setResponseStatus("OK");
        response.setResponseUri(ServiceAPI.Member.GET_MEMBER_BY_ID);
        response.setResponseContent(member);
        return response;
    }
}
