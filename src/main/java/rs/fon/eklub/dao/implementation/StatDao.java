/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.implementation;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import rs.fon.eklub.core.data.StatAccessService;
import rs.fon.eklub.core.entities.Stat;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class StatDao implements StatAccessService<Stat> {
    
    private SessionFactory sessionFactory;
    
    public StatDao() {
    }
    
    public StatDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Stat> getEntityStats() throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Stat> stats = new ArrayList<>();
            stats.add(countQuery(session, "Member"));
            stats.add(countQuery(session, "`Group`"));
            stats.add(sumQuery(session, "Training", "durationMinutes"));
            stats.add(sumQuery(session, "Payment", "amount"));
            tx.commit();
            return stats;
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
    public List<Stat> getMembersByCategoryStats() throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Stat> stats = new ArrayList<>();
            SQLQuery query = session.createSQLQuery("select isAttendant, count(isAttendant) from Attendance group by isAttendant;");
            List<Object[]> rows = query.list();
            rows.stream().forEach((row) -> {
                Stat stat = new Stat();
                stat.setLabel(row[0].toString().equals("true") ? "Prisustva" : "Odsustva");
                stat.setValue(row[1].toString());
                stats.add(stat);
            });
            tx.commit();
            return stats;
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
    public List<Stat> getAttendanceStats() throws DataAccessServiceException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Stat> stats = new ArrayList<>();
            SQLQuery query = session.createSQLQuery("select C.idCategory, C.name, count(C.idCategory) "
                    + "from Category C join `Group` G on (C.idCategory = G.idCategory) join Member M "
                    + "on (G.idGroup = M.idGroup) group by C.idCategory;");
            List<Object[]> rows = query.list();
            rows.stream().forEach((row) -> {
                Stat stat = new Stat();
                stat.setLabel(row[1].toString());
                stat.setValue(row[2].toString());
                stats.add(stat);
            });
            tx.commit();
            return stats;
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
    
    private Stat countQuery(Session session, String entity) {
        SQLQuery query = session.createSQLQuery(String.format("select count(*) from %s;", entity));
        Object res = query.list().get(0);
        Stat stat = new Stat(entity, res.toString());
        return stat;
    }
    
    private Stat sumQuery(Session session, String entity, String column) {
        SQLQuery query = session.createSQLQuery(String.format("select sum(%s) from %s;", column, entity));
        Object res = query.list().get(0);
        Stat stat = new Stat(entity, res.toString());
        return stat;
    }
}
