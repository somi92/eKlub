/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.entities.Payment;

/**
 *
 * @author milos
 */
public interface FeeService {
    
    public void saveFee(List<Payment> payments);
    public MembershipFee getFeeById(long id);
    public List<MembershipFee> getMembershipFees(Map<String, Object> searchCriteria);
}
