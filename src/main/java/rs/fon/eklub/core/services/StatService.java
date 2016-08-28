/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import rs.fon.eklub.core.entities.Stat;
import rs.fon.eklub.core.exceptions.ServiceException;

/**
 *
 * @author milos
 */
public interface StatService {
    
    public List<Stat> getEntityStats() throws ServiceException;
    public List<Stat> getMembersByCategoryStats() throws ServiceException;
    public List<Stat> getAttendanceStats() throws ServiceException;
}
