/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.dal;

import java.util.List;
import java.util.Map;

/**
 *
 * @author milos
 */
public interface DataAccessService<T> {
    
    public T getEntity(long id);
    public List<T> getAllEntities();
    public List<T> getEntities(Map<String, Object> searchCriteria);
    public void insertOrUpdateEntity(T entity);
    public void deleteEntity(T entity);
}
