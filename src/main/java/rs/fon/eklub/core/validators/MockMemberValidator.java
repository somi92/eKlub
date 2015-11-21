/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators;

import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.ValidationException;

/**
 *
 * @author milos
 */
public class MockMemberValidator implements EntityValidator<Member> {
    
    @Override
    public boolean validateEntity(Member entity) throws ValidationException {
        if (entity.getId() == 113) {
            throw new ValidationException("Validation exception!");
        }
        return true;
    }
}
