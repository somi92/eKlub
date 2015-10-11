/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import rs.fon.eklub.core.services.FeeService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class FeeInteractorTest {
    
    private FeeService fs;
    
    private DataAccessService<MembershipFee> dao;
    private DataAccessService<MembershipFee> daoAllEntitiesError;
    private EntityValidator<MembershipFee> validator;
    
    private List<MembershipFee> mockFeeRepository;
    
    public FeeInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        mockFeeRepository = new ArrayList<>();
        
        Calendar c1 = Calendar.getInstance();
        c1.set(2015, Calendar.OCTOBER, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2015, Calendar.OCTOBER, 31);
        MembershipFee mf1 = new MembershipFee(1, c1.getTime(), c2.getTime(), null);
        Payment p11 = new Payment(mf1, 1000, null, new Member(3));
        Payment p12 = new Payment(mf1, 1000, null, new Member(2));
        Payment p13 = new Payment(mf1, 1000, null, new Member(1));
        List<Payment> p1 = new ArrayList<>();
        p1.add(p13);
        p1.add(p12);
        p1.add(p11);
        mf1.setPayments(p1);
        
        Calendar c3 = Calendar.getInstance();
        c3.set(2015, Calendar.SEPTEMBER, 1);
        Calendar c4 = Calendar.getInstance();
        c4.set(2015, Calendar.SEPTEMBER, 30);
        MembershipFee mf2 = new MembershipFee(2, c3.getTime(), c4.getTime(), null);
        Payment p21 = new Payment(mf2, 1000, null, new Member(4));
        Payment p22 = new Payment(mf2, 1000, null, new Member(5));
        Payment p23 = new Payment(mf2, 1000, null, new Member(5));
        List<Payment> p2 = new ArrayList<>();
        p2.add(p23);
        p2.add(p22);
        p2.add(p21);
        mf2.setPayments(p2);
        
        mockFeeRepository.add(mf1);
        mockFeeRepository.add(mf2);
        
        dao = new DataAccessService<MembershipFee>() {

            @Override
            public MembershipFee getEntity(long id) throws DataAccessServiceException {
                if(id == 13) {
                    throw new DataAccessServiceException("Data access error!");
                } else {
                    for(MembershipFee mf : mockFeeRepository) {
                        if(mf.getId() == id) {
                            return mf;
                        }
                    }
                    return null;
                }
            }

            @Override
            public List<MembershipFee> getAllEntities() throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<MembershipFee> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                List<MembershipFee> fees = new ArrayList<>();
                long id = searchCriteria.get("id") == null ? 0 : Long.parseLong(searchCriteria.get("id").toString());
                if(id == 13) {
                    throw new DataAccessServiceException("Data access service!");
                }
                for(MembershipFee mf : mockFeeRepository) {
                    if(mf.getRemark().equals(searchCriteria.get("dateFrom").toString())) {
                        fees.add(mf);
                    }
                }
                return fees;
            }

            @Override
            public void insertOrUpdateEntity(MembershipFee entity) throws DataAccessServiceException {
                if(entity.getId() == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                if(!mockFeeRepository.contains(entity)) {
                    mockFeeRepository.add(entity);
                }
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        validator = new EntityValidator<MembershipFee>() {

            @Override
            public boolean validateEntity(MembershipFee entity) throws ValidationException {
                if(entity.getDateTo().before(entity.getDateFrom())) {
                    throw new ValidationException("Validation exception!");
                }
                return true;
            }
        };
        
        fs = new FeeInteractor(dao, validator);
    }
    
    @Test
    public void initializeFeeInteractorTest() {
        fs = new FeeInteractor(dao, validator);
        assertNotNull(fs);
    }
    
    @Test
    public void savePaymentsOkTest() throws ServiceException {
        MembershipFee mf1 = mockFeeRepository.get(0);
        MembershipFee mf2 = mockFeeRepository.get(1);
        Payment p1 = new Payment(mf2, 2000, null, new Member(10));
        Payment p2 = new Payment(mf1, 2000, null, new Member(20));
        List<Payment> payments = new ArrayList<>();
        payments.add(p1);
        payments.add(p2);
        fs.savePayments(payments);
        assertTrue(mockFeeRepository.contains(mf1));
        assertTrue(mockFeeRepository.contains(mf2));
        assertTrue(mockFeeRepository.get(1).getPayments().contains(p1));
        assertTrue(mockFeeRepository.get(0).getPayments().contains(p2));
    }
}
