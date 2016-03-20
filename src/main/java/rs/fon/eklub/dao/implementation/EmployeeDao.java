/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.implementation;

import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.util.Util;

/**
 *
 * @author milos
 */
@Repository
public class EmployeeDao implements DataAccessService<Employee> {

    private SessionFactory sessionFactory;

    public EmployeeDao() {
    }

    public EmployeeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Employee getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getAllEntities() throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String whereClause = Util.generateHibernateWhereClause(searchCriteria);
            List<Employee> employees = session.createQuery("from Employee e " + whereClause).list();
            tx.commit();
            return employees;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new DataAccessServiceException(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void insertOrUpdateEntity(Employee entity) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
