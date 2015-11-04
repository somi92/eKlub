/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.GroupService;

/**
 *
 * @author milos
 */
@RestController
@Component
public class GroupController {
    
    @Autowired
    private GroupService interactor;
    
    @RequestMapping(ServiceAPI.Group.GET_ALL_GROUPS)
    @ResponseBody
    public List<Group> getAllGroups() throws ServiceException {
        List<Group> groups = interactor.getAllGroups();
        return groups;
    }
}
