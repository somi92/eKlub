/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.data;

import java.util.List;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public interface StatAccessService<T> {
    
    public List<T> getEntityStats() throws DataAccessServiceException;
    public List<T> getMembersByCategoryStats() throws DataAccessServiceException;
    public List<T> getAttendanceStats() throws DataAccessServiceException;
}
