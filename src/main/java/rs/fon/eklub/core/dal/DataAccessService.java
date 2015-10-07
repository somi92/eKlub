/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.dal;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public interface DataAccessService<T> {
    
    public T getEntity(long id) throws DataAccessServiceException;
    public List<T> getAllEntities() throws DataAccessServiceException;
    public List<T> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException;
    public void insertOrUpdateEntity(T entity) throws DataAccessServiceException;
    public boolean deleteEntity(long id) throws DataAccessServiceException;
}
