/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;

/**
 *
 * @author milos
 */
public interface AdminService {
    
    public Employee getAdmin(Employee admin) throws ServiceException;
}
