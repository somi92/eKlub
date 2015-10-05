/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import rs.fon.eklub.core.entities.Group;

/**
 *
 * @author milos
 */
public interface GroupService {
    
    public void saveGroup(Group group);
    public List<Group> getAllGroups();
}
