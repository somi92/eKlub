/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.entities.Member;

/**
 *
 * @author milos
 */
public interface MembersService {
    
    public void saveMember(Member member);
    public Member getMemberById(long id);
    public void deleteMember(long id);
    public List<Member> getAllMembers();
    public List<Member> getMembers(Map<String, Object> searchCriteria);
}
