/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators;

import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.ValidationException;

/**
 *
 * @author milos
 */
public class PaymentValidator implements EntityValidator<Payment> {

    @Override
    public boolean validateEntityBeforeInsert(Payment entity) throws ValidationException {
        boolean isValid = true;
        if (entity.getFee() == null
                || entity.getMemberId() <= 0
                || entity.getAmount() <= 0
                || entity.getDateOfPayment() == null) {
            throw new ValidationException("Validation exception, 'Payment' entity not valid");
        }
        return isValid;
    }

    @Override
    public boolean validateEntityBeforeDelete(Payment entity) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
