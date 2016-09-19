/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators.mock;

import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class MockPaymentValidator implements EntityValidator<Payment> {

    @Override
    public boolean validateEntity(Payment entity) throws ValidationException {
        if (entity.getAmount() < 0) {
            throw new ValidationException("Validation exception!");
        }
        return true;
    }

}
