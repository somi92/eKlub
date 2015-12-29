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
import rs.fon.eklub.core.exceptions.ServiceException;

/**
 *
 * @author milos
 */
public interface PaymentService {
    
    public void savePayments(List<Payment> payments) throws ServiceException;
    public List<Payment> getPayments(Map<String, String> searchCriteria) throws ServiceException;
}
