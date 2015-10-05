/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.exceptions;

/**
 *
 * @author milos
 */
public class ServiceException extends Exception {
    
    public ServiceException() {
        
    }
    
    public ServiceException(String message) {
        super(message);
    }
}
