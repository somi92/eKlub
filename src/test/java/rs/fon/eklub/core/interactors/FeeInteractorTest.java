/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.MembershipFee;
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
        
    }
}
