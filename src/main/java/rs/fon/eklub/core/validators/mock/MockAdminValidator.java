/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators.mock;

import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class MockAdminValidator implements EntityValidator<Employee> {

    @Override
    public boolean validateEntity(Employee entity) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
