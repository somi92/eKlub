/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.implementation;

import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
@Repository
public class GroupDao implements DataAccessService<Group> {
    
    private SessionFactory sessionFactory;

    public GroupDao() {
    }

    public GroupDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Group getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> getAllEntities() throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Group> groups = session.createQuery("from Group g").list();
            tx.commit();
            return groups;
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
    public List<Group> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertOrUpdateEntity(Group entity) throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
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
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
