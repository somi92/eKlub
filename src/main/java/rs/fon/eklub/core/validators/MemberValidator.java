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
public class MemberValidator implements EntityValidator<Member> {

    @Override
    public boolean validateEntity(Member entity) throws ValidationException {
        boolean isValid = true;
        if (entity.getIdCard() == null
                || entity.getNameSurname() == null
                || entity.getGender() != 'M' || entity.getGender() != 'Ž'
                || entity.getAddress() == null
                || entity.getPhone() == null
                || entity.getDateOfMembership().before(entity.getDateOfBirth())
                || entity.getGroup() == null) {
            throw new ValidationException("Validation exception, 'Member' entity not valid");
        }
        return isValid;
    }
    
}
