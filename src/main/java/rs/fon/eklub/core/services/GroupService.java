/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ServiceException;

/**
 *
 * @author milos
 */
public interface GroupService {
    
    public void saveGroup(Group group) throws ServiceException;
    public List<Group> getAllGroups() throws ServiceException;
}
