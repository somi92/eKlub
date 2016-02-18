/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.PaymentService;
import rs.fon.eklub.core.validators.EntityValidator;
import rs.fon.eklub.core.validators.MockPaymentValidator;
import rs.fon.eklub.repositories.mocks.MockPaymentRepository;

/**
 *
 * @author milos
 */
public class PaymentInteractorTest {
    
    private PaymentService ps;
    
    private DataAccessService<Payment> dao;
    private EntityValidator<Payment> validator;
    
    public PaymentInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        dao = new MockPaymentRepository();
        validator = new MockPaymentValidator();
        
        ps = new PaymentInteractor(dao, validator);
    }
    
    @Test
    public void initializePaymentInteractorTest() {
        ps = new PaymentInteractor(dao, validator);
        assertNotNull(ps);
    }
    
    @Test
    public void savePaymentsOkTest() throws ServiceException {
        Calendar c1 = Calendar.getInstance();
        c1.set(2015, Calendar.OCTOBER, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2015, Calendar.OCTOBER, 31);
        Calendar c3 = Calendar.getInstance();
        c3.set(2015, Calendar.SEPTEMBER, 1);
        Calendar c4 = Calendar.getInstance();
        c4.set(2015, Calendar.SEPTEMBER, 30);
        MembershipFee mf1 = new MembershipFee(1, c1.getTime(), c2.getTime(), null);
        MembershipFee mf2 = new MembershipFee(2, c3.getTime(), c4.getTime(), null);
        Payment p1 = new Payment(7, mf2, 2000, null, new Member(10));
        Payment p2 = new Payment(8, mf1, 2000, null, new Member(20));
        Payment p3 = new Payment(9, mf1, 2000, null, new Member(30));
        List<Payment> payments = new ArrayList<>();
        payments.add(p1);
        payments.add(p2);
        payments.add(p3);
        ps.savePayments(payments);
        assertTrue(dao.getAllEntities().contains(p1));
        assertTrue(dao.getAllEntities().contains(p2));
        assertTrue(dao.getAllEntities().contains(p3));
    }
    
    @Test(expected = ServiceException.class)
    public void savePaymentsNullEntityTest() throws ServiceException {
        List<Payment> payments = null;
        ps.savePayments(payments);
        assertNull(payments);
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void savePaymentsDataExceptionTest() throws ServiceException {
        List<Payment> payments = new ArrayList<>();
        Payment p = new Payment(13, null, 2000, null, new Member(10));
        payments.add(p);
        ps.savePayments(payments);
        assertFalse(dao.getAllEntities().contains(p));
    }
    
    @Test(expected = ValidationException.class)
    public void savePaymentsValidationExceptionTest() throws ServiceException {
        List<Payment> payments = new ArrayList<>();
        Payment p = new Payment(13, null, -2000, null, new Member(10));
        payments.add(p);
        ps.savePayments(payments);
        assertFalse(dao.getAllEntities().contains(p));
    }
    
    @Test
    public void getPaymentsOkTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("amount", 2000+"");
        List<Payment> payments = ps.getPayments(searchCriteria);
        assertTrue(payments.size() == 3);
    }
    
    @Test
    public void getPaymentsEmptyTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("amount", 3000+"");
        List<Payment> payments = ps.getPayments(searchCriteria);
        assertTrue(payments.isEmpty());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getPaymentsDataExceptionTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("id", 13+"");
        List<Payment> payments = ps.getPayments(searchCriteria);
        assertNull(payments);
    }
}
