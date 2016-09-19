/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.MemberService;
import rs.fon.eklub.core.validators.EntityValidator;
import rs.fon.eklub.core.validators.mock.MockMemberValidator;
import rs.fon.eklub.dao.mock.MockMemberExceptionRepository;
import rs.fon.eklub.dao.mock.MockMemberRepository;

/**
 *
 * @author milos
 */
public class MemberInteractorTest {
    
    private MemberService ms;
    
    private DataAccessService<Member> dao;
    private DataAccessService<Member> daoAllEntitiesError;
    private EntityValidator<Member> validator;
    
    public MemberInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        dao = new MockMemberRepository();
        daoAllEntitiesError = new MockMemberExceptionRepository();
        validator = new MockMemberValidator();
        
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
        assertTrue(dao.getAllEntities().contains(m));
    }
    
    @Test(expected = ServiceException.class)
    public void saveMemberNullEntityTest() throws ServiceException {
        Member m = null;
        ms.saveMember(m);
        assertFalse(dao.getAllEntities().contains(m));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void saveMemberDataExceptionTest() throws ServiceException {
        Member m = new Member();
        m.setId(13);
        ms.saveMember(m);
        assertFalse(dao.getAllEntities().contains(m));
    }
    
    @Test(expected = ValidationException.class)
    public void saveMemberValidationExceptionTest() throws ServiceException {
        Member m = new Member();
        m.setId(113);
        ms.saveMember(m);
        assertFalse(dao.getAllEntities().contains(m));
    }
    
    @Test
    public void getMemberByIdOkTest() throws ServiceException {
        Member m1 = ms.getMemberById(1);
        Member m2 = ms.getMemberById(3);
        assertNotNull(m1);
        assertNotNull(m2);
        assertTrue(dao.getAllEntities().contains(m1));
        assertTrue(dao.getAllEntities().contains(m2));
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
        assertTrue(dao.getAllEntities().contains(m));
        boolean res = ms.deleteMember(id);
        assertTrue(res);
        assertFalse(dao.getAllEntities().contains(m));
    }
    
    @Test
    public void deleteMemberNotFoundTest() throws ServiceException {
        long id = 10;
        Member m = new Member();
        m.setId(id);
        assertFalse(dao.getAllEntities().contains(m));
        boolean res = ms.deleteMember(id);
        assertFalse(res);
        assertFalse(dao.getAllEntities().contains(m));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void deleteMemberDataExceptionTest() throws ServiceException {
        boolean res = ms.deleteMember(13);
        assertFalse(res);
    }
    
    @Test
    public void getAllMembersOkTest() throws ServiceException {
        List<Member> members = ms.getAllMembers();
        assertNotNull(members);
        assertArrayEquals(dao.getAllEntities().toArray(), members.toArray());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAllMembersDataExceptionTest() throws ServiceException {
        ms = new MemberInteractor(daoAllEntitiesError, validator);
        List<Member> members = ms.getAllMembers();
        assertNull(members);
    }
    
    @Test
    public void getMembersOkTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("gender", 'M'+"");
        List<Member> members = ms.getMembers(searchCriteria);
        assertTrue(members.size() == 2);
    }
    
    @Test
    public void getMembersEmptyTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("gender", 'x'+"");
        List<Member> members = ms.getMembers(searchCriteria);
        assertTrue(members.isEmpty());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getMembersDataExceptionTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("id", 13+"");
        List<Member> members = ms.getMembers(searchCriteria);
        assertNull(members);
    }
    
}
