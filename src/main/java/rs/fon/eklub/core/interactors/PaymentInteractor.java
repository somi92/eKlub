/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.PaymentService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class PaymentInteractor implements PaymentService {

    private DataAccessService<Payment> dao;
    private EntityValidator<Payment> validator;

    public PaymentInteractor() {
    }
    
    PaymentInteractor(DataAccessService<Payment> dao, EntityValidator<Payment> validator) {
        this.dao = dao;
        this.validator = validator;
    }
    
    public DataAccessService<Payment> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<Payment> dao) {
        this.dao = dao;
    }

    public EntityValidator<Payment> getValidator() {
        return validator;
    }

    public void setValidator(EntityValidator<Payment> validator) {
        this.validator = validator;
    }

    @Override
    public void savePayments(List<Payment> payments) throws ServiceException {
        if(payments == null || payments.isEmpty()) {
            throw new ServiceException("Payments null or empty!");
        }
        for(Payment p : payments) {
            if(validator.validateEntity(p)) {
                dao.insertOrUpdateEntity(p);
            }
        }
    }

    @Override
    public List<Payment> getPayments(Map<String, Object> searchCriteria) throws ServiceException {
        List<Payment> payments = dao.getEntities(searchCriteria);
        return payments;
    }
}
