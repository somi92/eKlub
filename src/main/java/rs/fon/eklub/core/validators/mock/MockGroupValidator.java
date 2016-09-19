/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators.mock;

import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class MockGroupValidator implements EntityValidator<Group> {
    
    @Override
    public boolean validateEntityBeforeInsert(Group entity) throws ValidationException {
        if (entity.getId() == 113) {
            throw new ValidationException("Validation exception!");
        }
        return true;
    }

    @Override
    public boolean validateEntityBeforeDelete(Group entity) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
