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
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
@Repository
public class MembershipFeeDao implements DataAccessService<MembershipFee> {

    private SessionFactory sessionFactory;

    public MembershipFeeDao() {
    }

    public MembershipFeeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public MembershipFee getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MembershipFee> getAllEntities() throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<MembershipFee> membershipFees = session.createQuery("from MembershipFee mf").list();
            tx.commit();
            return membershipFees;
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
    public List<MembershipFee> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertOrUpdateEntity(MembershipFee entity) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
