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
public class TrainingValidator implements EntityValidator<Training> {

    @Override
    public boolean validateEntityBeforeInsert(Training entity) throws ValidationException {
        boolean isValid = true;
        if (entity.getDurationMinutes() <= 0
                || entity.getGroup() == null) {
            throw new ValidationException("Validation exception, 'Training' entity not valid");
        }
        return isValid;
    }

    @Override
    public boolean validateEntityBeforeDelete(Training entity) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
