/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators;

import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ValidationException;

/**
 *
 * @author milos
 */
public class GroupValidator implements EntityValidator<Group> {

    @Override
    public boolean validateEntity(Group entity) throws ValidationException {
        boolean isValid = true;
        if (entity.getCategory() == null) {
            throw new ValidationException("Validation exception, 'Group' entity not valid");
        }
        return isValid;
    }
    
}
