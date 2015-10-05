/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.ServiceException;

/**
 *
 * @author milos
 */
public interface MemberService {
    
    public void saveMember(Member member) throws ServiceException;
    public Member getMemberById(long id) throws ServiceException;
    public void deleteMember(long id) throws ServiceException;
    public List<Member> getAllMembers() throws ServiceException;
    public List<Member> getMembers(Map<String, Object> searchCriteria) throws ServiceException;
}
