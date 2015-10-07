/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MemberService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class MemberInteractor implements MemberService {

    private DataAccessService<Member> dao;
    private EntityValidator<Member> validator;
    
    public MemberInteractor() {
    
    }

    MemberInteractor(DataAccessService<Member> dao, EntityValidator<Member> validator) {
        this.dao = dao;
        this.validator = validator;
    }
    
    public DataAccessService<Member> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<Member> dao) {
        this.dao = dao;
    }

    public EntityValidator<Member> getValidator() {
        return validator;
    }

    public void setValidator(EntityValidator<Member> validator) {
        this.validator = validator;
    }

    @Override
    public void saveMember(Member member) throws ServiceException {
        if(member == null) {
            throw new ServiceException("Member entity null!");
        }
        if(validator.validateEntity(member)) {
            dao.insertOrUpdateEntity(member);
        }
    }

    @Override
    public Member getMemberById(long id) throws ServiceException {
        Member member = dao.getEntity(id);
        return member;
    }

    @Override
    public boolean deleteMember(long id) throws ServiceException {
        return dao.deleteEntity(id);
    }

    @Override
    public List<Member> getAllMembers() throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Member> getMembers(Map<String, Object> searchCriteria) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
