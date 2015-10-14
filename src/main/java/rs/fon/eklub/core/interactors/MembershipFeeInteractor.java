/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MembershipFeeService;

/**
 *
 * @author milos
 */
public class MembershipFeeInteractor implements MembershipFeeService {

    private DataAccessService<MembershipFee> dao;
    
    public MembershipFeeInteractor() {
    }
    
    public MembershipFeeInteractor(DataAccessService<MembershipFee> dao) {
        this.dao = dao;
    }
    
    public DataAccessService<MembershipFee> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<MembershipFee> dao) {
        this.dao = dao;
    }
    
    @Override
    public List<MembershipFee> getAllMembershipFees() throws ServiceException {
        List<MembershipFee> fees = dao.getAllEntities();
        return fees;
    }
}
