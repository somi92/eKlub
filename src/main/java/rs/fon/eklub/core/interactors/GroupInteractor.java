/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.GroupService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class GroupInteractor implements GroupService {

    private DataAccessService<Group> dao;
    private EntityValidator<Group> validator;

    public GroupInteractor() {
    }

    public GroupInteractor(DataAccessService<Group> dao, EntityValidator<Group> validator) {
        this.dao = dao;
        this.validator = validator;
    }
    
    public DataAccessService<Group> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<Group> dao) {
        this.dao = dao;
    }

    public EntityValidator<Group> getValidator() {
        return validator;
    }

    public void setValidator(EntityValidator<Group> validator) {
        this.validator = validator;
    }
    
    @Override
    public void saveGroup(Group group) throws ServiceException {
        if(group == null) {
            throw new ServiceException("Group entity null!");
        }
        if(validator.validateEntity(group)) {
            dao.insertOrUpdateEntity(group);
        } 
    }

    @Override
    public List<Group> getAllGroups() throws ServiceException {
        List<Group> groups = dao.getAllEntities();
        return groups;
    }
}
