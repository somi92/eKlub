/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.GroupService;
import rs.fon.eklub.response.ServiceResponse;

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
    
    @RequestMapping(value = ServiceAPI.Group.GET_ALL_GROUPS,
                    method = RequestMethod.GET)
    @ResponseBody
    public List<Group> getAllGroups() throws ServiceException {
        List<Group> groups = interactor.getAllGroups();
        return groups;
    }
    
    @RequestMapping(value = ServiceAPI.Group.POST_SAVE_GROUP,
                    method = RequestMethod.POST,
                    headers = {ServiceAPI.Headers.CONTENT_TYPE})
    @ResponseBody
    public ServiceResponse saveGroup(@RequestBody Group group) throws ServiceException {
        interactor.saveGroup(group);
        ServiceResponse response = new ServiceResponse();
        response.setResponseStatus("OK");
        response.setResponseMessage("Group saved.");
        response.setRequestUri(ServiceAPI.Group.POST_SAVE_GROUP);
        return response;
    }
}
