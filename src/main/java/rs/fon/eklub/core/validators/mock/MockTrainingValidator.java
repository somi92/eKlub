/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators.mock;

import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class MockTrainingValidator implements EntityValidator<Training> {

    @Override
    public boolean validateEntityBeforeInsert(Training entity) throws ValidationException {
        if (entity.getDurationMinutes() < 0) {
            throw new ValidationException("Validation exception!");
        }
        return true;
    }

    @Override
    public boolean validateEntityBeforeDelete(Training entity) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
