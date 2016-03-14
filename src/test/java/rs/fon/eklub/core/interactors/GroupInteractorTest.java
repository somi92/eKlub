/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.GroupService;
import rs.fon.eklub.core.validators.EntityValidator;
import rs.fon.eklub.core.validators.MockGroupValidator;
import rs.fon.eklub.dao.mock.MockGroupExceptionRepository;
import rs.fon.eklub.dao.mock.MockGroupRepository;

/**
 *
 * @author milos
 */
public class GroupInteractorTest {
    
    private GroupService gs;
    
    private DataAccessService<Group> dao;
    private DataAccessService<Group> daoAllEntitiesError;
    private EntityValidator<Group> validator;
    
    public GroupInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        dao = new MockGroupRepository();
        daoAllEntitiesError = new MockGroupExceptionRepository();
        validator = new MockGroupValidator();
        gs = new GroupInteractor(dao, validator);
    }
    
    @Test
    public void initializeGroupInteractorOkTest() {
        gs = new GroupInteractor(dao, validator);
        assertNotNull(gs);
    }
    
    @Test
    public void saveGroupOkTest() throws ServiceException {
        Group g = new Group(10, "Grupa10", "grupa10 remark", 
                new Category(1, "Kategorija1", "kategorija1 remark"));
        gs.saveGroup(g);
        assertTrue(dao.getAllEntities().contains(g));
    }
    
    @Test(expected = ServiceException.class)
    public void saveGroupNullEntityTest() throws ServiceException {
        Group g = null;
        gs.saveGroup(g);
        assertFalse(dao.getAllEntities().contains(g));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void saveGroupDataExceptionTest() throws ServiceException {
        Group g = new Group(13, "Grupa13", "grupa13 remark", 
                new Category(1, "Kategorija1", "kategorija1 remark"));
        gs.saveGroup(g);
        assertFalse(dao.getAllEntities().contains(g));
    }
    
    @Test(expected = ValidationException.class)
    public void saveGroupValidationExceptionTest() throws ServiceException {
        Group g = new Group(113, "Grupa113", "grupa113 remark", 
                new Category(1, "Kategorija1", "kategorija1 remark"));
        gs.saveGroup(g);
        assertFalse(dao.getAllEntities().contains(g));
    }
    
    @Test
    public void getAllGroupsOkTest() throws ServiceException {
        List<Group> groups = gs.getAllGroups();
        assertNotNull(groups);
        assertArrayEquals(dao.getAllEntities().toArray(), groups.toArray());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAllGroupsDataExceptionTest() throws ServiceException {
        gs = new GroupInteractor(daoAllEntitiesError, validator);
        List<Group> groups = gs.getAllGroups();
        assertNull(groups);
    }
}
