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
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MembershipFeeService;
import rs.fon.eklub.dao.mock.MockMembershipFeeExceptionRepository;
import rs.fon.eklub.dao.mock.MockMembershipFeeRepository;

/**
 *
 * @author milos
 */
public class MembershipFeeInteractorTest {
    
    private MembershipFeeService mfs;
    
    private DataAccessService<MembershipFee> dao;
    private DataAccessService<MembershipFee> daoAllEntitiesError;
    
    public MembershipFeeInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        dao = new MockMembershipFeeRepository();
        daoAllEntitiesError = new MockMembershipFeeExceptionRepository();
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
        assertArrayEquals(dao.getAllEntities().toArray(), fees.toArray());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getAllMembershipFeesDataExceptionTest() throws ServiceException {
        mfs = new MembershipFeeInteractor(daoAllEntitiesError);
        List<MembershipFee> fees = mfs.getAllMembershipFees();
        assertNull(fees);
    }
}
