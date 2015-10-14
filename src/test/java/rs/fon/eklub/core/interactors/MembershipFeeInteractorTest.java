/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MembershipFeeService;

/**
 *
 * @author milos
 */
public class MembershipFeeInteractorTest {
    
    private MembershipFeeService mfs;
    
    private DataAccessService<MembershipFee> dao;
    private DataAccessService<MembershipFee> daoAllEntitiesError;
    
    private List<MembershipFee> mockMembershipFeeRepository;
    
    public MembershipFeeInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        mockMembershipFeeRepository = new ArrayList<>();
        
        Calendar c1 = Calendar.getInstance();
        c1.set(2015, Calendar.OCTOBER, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2015, Calendar.OCTOBER, 31);
        MembershipFee mf1 = new MembershipFee(1, c1.getTime(), c2.getTime(), null);
        
        Calendar c3 = Calendar.getInstance();
        c3.set(2015, Calendar.SEPTEMBER, 1);
        Calendar c4 = Calendar.getInstance();
        c4.set(2015, Calendar.SEPTEMBER, 30);
        MembershipFee mf2 = new MembershipFee(2, c3.getTime(), c4.getTime(), null);
        
        Calendar c5 = Calendar.getInstance();
        c5.set(2015, Calendar.AUGUST, 1);
        Calendar c6 = Calendar.getInstance();
        c6.set(2015, Calendar.AUGUST, 31);
        MembershipFee mf3 = new MembershipFee(3, c5.getTime(), c6.getTime(), null);
        
        mockMembershipFeeRepository.add(mf1);
        mockMembershipFeeRepository.add(mf2);
        mockMembershipFeeRepository.add(mf3);
        
        dao = new DataAccessService<MembershipFee>() {

            @Override
            public MembershipFee getEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<MembershipFee> getAllEntities() throws DataAccessServiceException {
                List<MembershipFee> fees = mockMembershipFeeRepository;
                return fees;
            }

            @Override
            public List<MembershipFee> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void insertOrUpdateEntity(MembershipFee entity) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        daoAllEntitiesError = new DataAccessService<MembershipFee>() {

            @Override
            public MembershipFee getEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<MembershipFee> getAllEntities() throws DataAccessServiceException {
                throw new DataAccessServiceException("Data access error!");
            }

            @Override
            public List<MembershipFee> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void insertOrUpdateEntity(MembershipFee entity) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        mfs = new MembershipFeeInteractor(dao);
    }
    
    @Test
    public void initializeMembershipFeeInteractorOkTest() {
        mfs = new MembershipFeeInteractor(dao);
        assertNotNull(mfs);
    }
    
    @Test
    public void getAllMembershipFeesOkTest() throws ServiceException {
        List<MembershipFee> fees = mfs.getAllMembershipFees();
        assertNotNull(fees);
        assertArrayEquals(mockMembershipFeeRepository.toArray(), fees.toArray());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAllMembershipFeesDataExceptionTest() throws ServiceException {
        mfs = new MembershipFeeInteractor(daoAllEntitiesError);
        List<MembershipFee> fees = mfs.getAllMembershipFees();
        assertNull(fees);
    }
}
