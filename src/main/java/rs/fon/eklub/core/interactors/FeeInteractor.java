/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.FeeService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class FeeInteractor implements FeeService {

    private DataAccessService<MembershipFee> dao;
    private EntityValidator<MembershipFee> validator;

    public FeeInteractor() {
    }
    
    FeeInteractor(DataAccessService<MembershipFee> dao, EntityValidator<MembershipFee> validator) {
        this.dao = dao;
        this.validator = validator;
    }
    
    public DataAccessService<MembershipFee> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<MembershipFee> dao) {
        this.dao = dao;
    }

    public EntityValidator<MembershipFee> getValidator() {
        return validator;
    }

    public void setValidator(EntityValidator<MembershipFee> validator) {
        this.validator = validator;
    }

    @Override
    public void savePayments(List<Payment> payments) throws ServiceException {
        if(payments == null || payments.isEmpty()) {
            throw new ServiceException("Payments null or empty!");
        }
        for(Payment p : payments) {
            List<Payment> curentFeePayments = p.getFee().getPayments();
            curentFeePayments.add(p);
            p.getFee().setPayments(curentFeePayments);
            if(validator.validateEntity(p.getFee())) {
                dao.insertOrUpdateEntity(p.getFee());
            }
        }
    }

    @Override
    public MembershipFee getFeeById(long id) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MembershipFee> getMembershipFees(Map<String, Object> searchCriteria) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
