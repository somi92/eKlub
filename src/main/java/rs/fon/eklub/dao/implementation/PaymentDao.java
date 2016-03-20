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
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.util.Util;

/**
 *
 * @author milos
 */
@Repository
public class PaymentDao implements DataAccessService<Payment> {

    private SessionFactory sessionFactory;

    public PaymentDao() {
    }

    public PaymentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Payment getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Payment> getAllEntities() throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Payment> payments = session.createQuery("from Payment p").list();
            tx.commit();
            return payments;
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
    public List<Payment> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String whereClause = Util.generateHibernateLikeClause(searchCriteria);
            List<Payment> payments = session.createQuery("from Payment p " + whereClause).list();
            tx.commit();
            return payments;
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
    public void insertOrUpdateEntity(Payment entity) throws DataAccessServiceException {
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
