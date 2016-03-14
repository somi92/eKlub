/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockPaymentRepository implements DataAccessService<Payment> {

    private List<Payment> mockPaymentsRepository = new ArrayList<>();

    public MockPaymentRepository() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2015, Calendar.OCTOBER, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2015, Calendar.OCTOBER, 31);
        MembershipFee mf1 = new MembershipFee(1, c1.getTime(), c2.getTime(), null);
        Payment p11 = new Payment(1, mf1, 1000, null, new Member(3));
        Payment p12 = new Payment(2, mf1, 2000, null, new Member(2));
        Payment p13 = new Payment(3, mf1, 2000, null, new Member(1));
        mockPaymentsRepository.add(p11);
        mockPaymentsRepository.add(p12);
        mockPaymentsRepository.add(p13);

        Calendar c3 = Calendar.getInstance();
        c3.set(2015, Calendar.SEPTEMBER, 1);
        Calendar c4 = Calendar.getInstance();
        c4.set(2015, Calendar.SEPTEMBER, 30);
        MembershipFee mf2 = new MembershipFee(2, c3.getTime(), c4.getTime(), null);
        Payment p21 = new Payment(4, mf2, 1000, null, new Member(4));
        Payment p22 = new Payment(5, mf2, 1000, null, new Member(5));
        Payment p23 = new Payment(6, mf2, 2000, null, new Member(5));
        mockPaymentsRepository.add(p21);
        mockPaymentsRepository.add(p22);
        mockPaymentsRepository.add(p23);

    }

    @Override
    public Payment getEntity(long id) throws DataAccessServiceException {
        if (id == 13) {
            throw new DataAccessServiceException("Data access error!");
        } else {
            for (Payment p : mockPaymentsRepository) {
                if (p.getId() == id) {
                    return p;
                }
            }
            return null;
        }
    }

    @Override
    public List<Payment> getAllEntities() throws DataAccessServiceException {
        return mockPaymentsRepository;
    }

    @Override
    public List<Payment> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        List<Payment> payments = new ArrayList<>();
        long id = searchCriteria.get("id") == null ? 0 : Long.parseLong(searchCriteria.get("id").toString());
        if (id == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        for (Payment p : mockPaymentsRepository) {
            if (p.getAmount() == Double.parseDouble(searchCriteria.get("amount"))) {
                payments.add(p);
            }
        }
        return payments;
    }

    @Override
    public void insertOrUpdateEntity(Payment entity) throws DataAccessServiceException {
        if (entity.getId() == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        if (!mockPaymentsRepository.contains(entity)) {
            mockPaymentsRepository.add(entity);
        }
    }

    @Override
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
