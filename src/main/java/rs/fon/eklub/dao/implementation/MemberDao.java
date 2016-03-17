/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.implementation;

import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.util.Util;

/**
 *
 * @author milos
 */
@Repository
public class MemberDao implements DataAccessService<Member> {

    private SessionFactory sessionFactory;

    public MemberDao() {
    }

    public MemberDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Member getEntity(long id) throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Member member = session.get(Member.class, id);
            tx.commit();
            return member;
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
    public List<Member> getAllEntities() throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Member> members = session.createQuery("from Member m").list();
            tx.commit();
            return members;
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
    public List<Member> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String whereClause = Util.generateHibernateWhereClause(searchCriteria);
            List<Member> members = session.createQuery("from Member m " + whereClause).list();
            tx.commit();
            return members;
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
    public void insertOrUpdateEntity(Member entity) throws DataAccessServiceException {
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
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Member where id = :ID");
            query.setParameter("ID", id);
            int result = query.executeUpdate();
            tx.commit();
            return result > 0;
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
}
