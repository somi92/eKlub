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

/**
 *
 * @author milos
 */
public class PaymentInteractorTest {
    
    private PaymentService ps;
    
    private DataAccessService<Payment> dao;
    private EntityValidator<Payment> validator;
    
    private List<MembershipFee> mockFeeRepository;
    private List<Payment> mockPaymentsRepository;
    
    public PaymentInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        mockFeeRepository = new ArrayList<>();
        mockPaymentsRepository = new ArrayList<>();
        
        Calendar c1 = Calendar.getInstance();
        c1.set(2015, Calendar.OCTOBER, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2015, Calendar.OCTOBER, 31);
        MembershipFee mf1 = new MembershipFee(1, c1.getTime(), c2.getTime(), null);
        Payment p11 = new Payment(1, mf1, 1000, null, new Member(3));
        Payment p12 = new Payment(2, mf1, 2000, null, new Member(2));
        Payment p13 = new Payment(3, mf1, 2000, null, new Member(1));
        mockPaymentsRepository.add(p11);
        mockPaymentsRepository.add(p12);
        mockPaymentsRepository.add(p13);
        
        Calendar c3 = Calendar.getInstance();
        c3.set(2015, Calendar.SEPTEMBER, 1);
        Calendar c4 = Calendar.getInstance();
        c4.set(2015, Calendar.SEPTEMBER, 30);
        MembershipFee mf2 = new MembershipFee(2, c3.getTime(), c4.getTime(), null);
        Payment p21 = new Payment(4, mf2, 1000, null, new Member(4));
        Payment p22 = new Payment(5, mf2, 1000, null, new Member(5));
        Payment p23 = new Payment(6, mf2, 2000, null, new Member(5));
        mockPaymentsRepository.add(p21);
        mockPaymentsRepository.add(p22);
        mockPaymentsRepository.add(p23);
        
        mockFeeRepository.add(mf1);
        mockFeeRepository.add(mf2);
        
        dao = new DataAccessService<Payment>() {

            @Override
            public Payment getEntity(long id) throws DataAccessServiceException {
                if(id == 13) {
                    throw new DataAccessServiceException("Data access error!");
                } else {
                    for(Payment p : mockPaymentsRepository) {
                        if(p.getId() == id) {
                            return p;
                        }
                    }
                    return null;
                }
            }

            @Override
            public List<Payment> getAllEntities() throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Payment> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
                List<Payment> payments = new ArrayList<>();
                long id = searchCriteria.get("id") == null ? 0 : Long.parseLong(searchCriteria.get("id").toString());
                if(id == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                for(Payment p : mockPaymentsRepository) {
                    if(p.getAmount() == Double.parseDouble(searchCriteria.get("amount"))) {
                        payments.add(p);
                    }
                }
                return payments;
            }

            @Override
            public void insertOrUpdateEntity(Payment entity) throws DataAccessServiceException {
                if(entity.getId() == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                if(!mockPaymentsRepository.contains(entity)) {
                    mockPaymentsRepository.add(entity);
                }
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        validator = new EntityValidator<Payment>() {

            @Override
            public boolean validateEntity(Payment entity) throws ValidationException {
                if(entity.getAmount() < 0) {
                    throw new ValidationException("Validation exception!");
                }
                return true;
            }
        };
        
        ps = new PaymentInteractor(dao, validator);
    }
    
    @Test
    public void initializePaymentInteractorTest() {
        ps = new PaymentInteractor(dao, validator);
        assertNotNull(ps);
    }
    
    @Test
    public void savePaymentsOkTest() throws ServiceException {
        MembershipFee mf1 = mockFeeRepository.get(0);
        MembershipFee mf2 = mockFeeRepository.get(1);
        Payment p1 = new Payment(7, mf2, 2000, null, new Member(10));
        Payment p2 = new Payment(8, mf1, 2000, null, new Member(20));
        Payment p3 = new Payment(9, mf1, 2000, null, new Member(30));
        List<Payment> payments = new ArrayList<>();
        payments.add(p1);
        payments.add(p2);
        payments.add(p3);
        ps.savePayments(payments);
        assertTrue(mockPaymentsRepository.contains(p1));
        assertTrue(mockPaymentsRepository.contains(p2));
        assertTrue(mockPaymentsRepository.contains(p3));
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
        assertFalse(mockPaymentsRepository.contains(p));
    }
    
    @Test(expected = ValidationException.class)
    public void savePaymentsValidationExceptionTest() throws ServiceException {
        List<Payment> payments = new ArrayList<>();
        Payment p = new Payment(13, null, -2000, null, new Member(10));
        payments.add(p);
        ps.savePayments(payments);
        assertFalse(mockPaymentsRepository.contains(p));
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
