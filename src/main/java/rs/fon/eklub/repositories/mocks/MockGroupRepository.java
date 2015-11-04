/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.repositories.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockGroupRepository implements DataAccessService<Group> {
    
    private List<Group> mockGroupRepository = new ArrayList<>();
    
    public MockGroupRepository() {
        Group g1 = new Group(1, "grupa1", "grupa1 remark", new Category(1, "kategorija1", "kategorija1 remark"));
        Group g2 = new Group(2, "grupa2", "grupa2 remark", new Category(2, "kategorija2", "kategorija2 remark"));
        Group g3 = new Group(3, "grupa3", "grupa3 remark", new Category(3, "kategorija3", "kategorija3 remark"));
        mockGroupRepository.add(g1);
        mockGroupRepository.add(g2);
        mockGroupRepository.add(g3);
    }
    
    public List<Group> getMockGroupRepository() {
        return mockGroupRepository;
    }

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
        if (entity.getId() == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        mockGroupRepository.add(entity);
    }

    @Override
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
