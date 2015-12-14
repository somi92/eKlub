/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators;

import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.ValidationException;

/**
 *
 * @author milos
 */
public class MockTrainingValidator implements EntityValidator<Training> {

    @Override
    public boolean validateEntity(Training entity) throws ValidationException {
        if (entity.getDurationMinutes() < 0) {
            throw new ValidationException("Validation exception!");
        }
        return true;
    }

}
