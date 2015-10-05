/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.validators;

import rs.fon.eklub.core.exceptions.ValidationException;

/**
 *
 * @author milos
 */
public interface EntityValidator<T> {
    
    public boolean validateEntity(T entity) throws ValidationException;
}
