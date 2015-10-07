/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.GroupService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class GroupInteractorTest {
    
    private GroupService gs;
    
    private DataAccessService<Group> dao;
    private DataAccessService<Group> daoAllEntitiesError;
    private EntityValidator<Group> validator;
    
    private List<Group> mockGroupRepository;
    
    public GroupInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        mockGroupRepository = new ArrayList<>();
        Group g1 = new Group(1, "grupa1", "grupa1 remark", new Category(1, "kategorija1", "kategorija1 remark"));
        Group g2 = new Group(2, "grupa2", "grupa2 remark", new Category(2, "kategorija2", "kategorija2 remark"));
        Group g3 = new Group(3, "grupa3", "grupa3 remark", new Category(3, "kategorija3", "kategorija3 remark"));
        mockGroupRepository.add(g1);
        mockGroupRepository.add(g2);
        mockGroupRepository.add(g3);
        
        dao = new DataAccessService<Group>() {

            @Override
            public Group getEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Group> getAllEntities() throws DataAccessServiceException {
                List<Group> groups = mockGroupRepository;
                return groups;
            }

            @Override
            public List<Group> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void insertOrUpdateEntity(Group entity) throws DataAccessServiceException {
                if(entity.getId() == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                mockGroupRepository.add(entity);
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        daoAllEntitiesError = new DataAccessService<Group>() {

            @Override
            public Group getEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Group> getAllEntities() throws DataAccessServiceException {
                throw new DataAccessServiceException("Data access error!");
            }

            @Override
            public List<Group> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void insertOrUpdateEntity(Group entity) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        validator = new EntityValidator<Group>() {

            @Override
            public boolean validateEntity(Group entity) throws ValidationException {
                if(entity.getId() == 113) {
                    throw new ValidationException("Validation exception!");
                }
                return true;
            }
        };
        
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
        assertTrue(mockGroupRepository.contains(g));
    }
    
    @Test(expected = ServiceException.class)
    public void saveGroupNullEntityTest() throws ServiceException {
        Group g = null;
        gs.saveGroup(g);
        assertFalse(mockGroupRepository.contains(g));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void saveGroupDataExceptionTest() throws ServiceException {
        Group g = new Group(13, "Grupa13", "grupa13 remark", 
                new Category(1, "Kategorija1", "kategorija1 remark"));
        gs.saveGroup(g);
        assertFalse(mockGroupRepository.contains(g));
    }
    
    @Test(expected = ValidationException.class)
    public void saveGroupValidationExceptionTest() throws ServiceException {
        Group g = new Group(113, "Grupa113", "grupa113 remark", 
                new Category(1, "Kategorija1", "kategorija1 remark"));
        gs.saveGroup(g);
        assertFalse(mockGroupRepository.contains(g));
    }
    
    @Test
    public void getAllGroupsOkTest() throws ServiceException {
        List<Group> groups = gs.getAllGroups();
        assertNotNull(groups);
        assertArrayEquals(mockGroupRepository.toArray(), groups.toArray());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAllGroupsDataExceptionTest() throws ServiceException {
        gs = new GroupInteractor(daoAllEntitiesError, validator);
        List<Group> groups = gs.getAllGroups();
        assertNull(groups);
    }
}
