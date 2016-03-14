/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockMemberRepository implements DataAccessService<Member> {
    
    private List<Member> mockMemberRepository;
    
    public MockMemberRepository() {
        mockMemberRepository = new ArrayList<>();
        Member m1 = new Member();
        m1.setId(1);
        m1.setGender('M');
        Member m2 = new Member();
        m2.setId(2);
        m2.setGender('F');
        Member m3 = new Member();
        m3.setId(3);
        m3.setGender('M');
        
        mockMemberRepository.add(m1);
        mockMemberRepository.add(m2);
        mockMemberRepository.add(m3);
    }
    
    @Override
    public Member getEntity(long id) throws DataAccessServiceException {
        if (id == 13) {
            throw new DataAccessServiceException("Data access error!");
        } else {
            for (Member m : mockMemberRepository) {
                if (m.getId() == id) {
                    return m;
                }
            }
            return null;
        }
    }

    @Override
    public List<Member> getAllEntities() throws DataAccessServiceException {
        List<Member> members = mockMemberRepository;
        return members;
    }

    @Override
    public List<Member> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        List<Member> members = new ArrayList<>();
        long id = searchCriteria.get("id") == null ? 0 : Long.parseLong(searchCriteria.get("id").toString());
        if (id == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        for (Member m : mockMemberRepository) {
            if (m.getGender() == searchCriteria.get("gender").toString().charAt(0)) {
                members.add(m);
            }
        }
        return members;
    }

    @Override
    public void insertOrUpdateEntity(Member entity) throws DataAccessServiceException {
        if (entity.getId() == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        mockMemberRepository.add(entity);
    }

    @Override
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        if (id == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        for (Member m : mockMemberRepository) {
            if (m.getId() == id) {
                mockMemberRepository.remove(m);
                return true;
            }
        }
        return false;
    }
    
}
