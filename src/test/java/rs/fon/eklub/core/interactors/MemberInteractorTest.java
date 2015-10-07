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
import org.junit.Before;
import org.junit.Test;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.MemberService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class MemberInteractorTest {
    
    private MemberService ms;
    
    private DataAccessService<Member> dao;
    private DataAccessService<Member> daoAllEntitiesError;
    private EntityValidator<Member> validator;
    
    private List<Member> mockMemberRepository;
    
    public MemberInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        mockMemberRepository = new ArrayList<>();
        Member m1 = new Member();
        m1.setId(1);
        Member m2 = new Member();
        m2.setId(2);
        Member m3 = new Member();
        m3.setId(3);
        
        mockMemberRepository.add(m1);
        mockMemberRepository.add(m2);
        mockMemberRepository.add(m3);
        
        dao = new DataAccessService<Member>() {

            @Override
            public Member getEntity(long id) throws DataAccessServiceException {
                if(id == 13) {
                    throw new DataAccessServiceException("Data access error!");
                } else {
                    for(Member m : mockMemberRepository) {
                        if(m.getId() == id) {
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
            public List<Member> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                List<Member> members = new ArrayList<>();
                if((long) searchCriteria.get("id") == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                for(Member m : mockMemberRepository) {
                    if(m.getId() == (long) searchCriteria.get("id")) {
                        members.add(m);
                    }
                }
                return members;
            }

            @Override
            public void insertOrUpdateEntity(Member entity) throws DataAccessServiceException {
                if(entity.getId() == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                mockMemberRepository.add(entity);
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                if(id == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                for(Member m : mockMemberRepository) {
                    if(m.getId() == id) {
                        mockMemberRepository.remove(m);
                        return true;
                    }
                }
                return false;
            }
        };
        
        daoAllEntitiesError = new DataAccessService<Member>() {

            @Override
            public Member getEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Member> getAllEntities() throws DataAccessServiceException {
                throw new DataAccessServiceException("Data access error!");
            }

            @Override
            public List<Member> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void insertOrUpdateEntity(Member entity) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        validator = new EntityValidator<Member>() {

            @Override
            public boolean validateEntity(Member entity) throws ValidationException {
                if(entity.getId() == 113) {
                    throw new ValidationException("Validation exception!");
                }
                return true;
            }
        };
        
        ms = new MemberInteractor(dao, validator);
    }
    
    @Test
    public void initializeMemberInteractorOkTest() {
        ms = new MemberInteractor(dao, validator);
        assertNotNull(ms);
    }
    
    @Test
    public void saveMemberOkTest() throws ServiceException {
        Member m = new Member();
        m.setId(10);
        ms.saveMember(m);
        assertTrue(mockMemberRepository.contains(m));
    }
    
    @Test(expected = ServiceException.class)
    public void saveMemberNullEntityTest() throws ServiceException {
        Member m = null;
        ms.saveMember(m);
        assertFalse(mockMemberRepository.contains(m));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void saveMemberDataExceptionTest() throws ServiceException {
        Member m = new Member();
        m.setId(13);
        ms.saveMember(m);
        assertFalse(mockMemberRepository.contains(m));
    }
    
    @Test(expected = ValidationException.class)
    public void saveMemberValidationException() throws ServiceException {
        Member m = new Member();
        m.setId(113);
        ms.saveMember(m);
        assertFalse(mockMemberRepository.contains(m));
    }
    
    @Test
    public void getMemberByIdOkTest() throws ServiceException {
        Member m1 = ms.getMemberById(1);
        Member m2 = ms.getMemberById(3);
        assertNotNull(m1);
        assertNotNull(m2);
        assertTrue(mockMemberRepository.contains(m1));
        assertTrue(mockMemberRepository.contains(m2));
    }
    
    @Test
    public void getMemberByIdNotFoundTest() throws ServiceException {
        Member m = ms.getMemberById(100);
        assertNull(m);
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getMemberByIdDataExceptionTest() throws ServiceException {
        Member m = ms.getMemberById(13);
        assertNull(m);
    }
    
    @Test
    public void deleteMemberOkTest() throws ServiceException {
        long id = 2;
        Member m = new Member();
        m.setId(id);
        assertTrue(mockMemberRepository.contains(m));
        boolean res = ms.deleteMember(id);
        assertTrue(res);
        assertFalse(mockMemberRepository.contains(m));
    }
    
    @Test
    public void deleteMemberNotFoundTest() throws ServiceException {
        long id = 10;
        Member m = new Member();
        m.setId(id);
        assertFalse(mockMemberRepository.contains(m));
        boolean res = ms.deleteMember(id);
        assertFalse(res);
        assertFalse(mockMemberRepository.contains(m));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void deleteMemberDataExceptionTest() throws ServiceException {
        boolean res = ms.deleteMember(13);
        assertFalse(res);
    }
    
    
}
