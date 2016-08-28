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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.GroupService;
import rs.fon.eklub.envelopes.ServiceResponse;

/**
 *
 * @author milos
 */
@RestController
@Component
public class GroupController {
    
    private GroupService interactor;

    @Autowired
    public GroupController(GroupService interactor) {
        this.interactor = interactor;
    }
    
    @PreAuthorize(value = "#oauth2.hasAnyScope('global', 'mobile')")
    @RequestMapping(value = ServiceAPI.Group.GET_ALL_GROUPS,
                    method = RequestMethod.GET)
    public ResponseEntity getAllGroups() throws ServiceException {
        List<Group> groups = interactor.getAllGroups();
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(groups.size() <= 0) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<List<Group>> response = new ServiceResponse();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Group.GET_ALL_GROUPS);
        response.setPayload(groups);
        return new ResponseEntity<>(response, httpStatus);
    }
    
    @PreAuthorize(value = "#oauth2.hasAnyScope('global')")
    @RequestMapping(value = ServiceAPI.Group.POST_SAVE_GROUP,
                    method = RequestMethod.POST,
                    headers = {ServiceAPI.Headers.CONTENT_TYPE})
    public ResponseEntity saveGroup(@RequestBody Group group) throws ServiceException {
        interactor.saveGroup(group);
        ServiceResponse<String> response = new ServiceResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED);
        response.setRequestUri(ServiceAPI.Group.POST_SAVE_GROUP);
        response.setPayload("Group "+group.getId()+" saved.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
