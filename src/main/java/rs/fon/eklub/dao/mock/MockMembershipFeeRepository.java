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
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockMembershipFeeRepository implements DataAccessService<MembershipFee> {

    private List<MembershipFee> mockMembershipFeeRepository = new ArrayList<>();

    public MockMembershipFeeRepository() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2015, Calendar.OCTOBER, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2015, Calendar.OCTOBER, 31);
        MembershipFee mf1 = new MembershipFee(1, c1.getTime(), c2.getTime(), null);

        Calendar c3 = Calendar.getInstance();
        c3.set(2015, Calendar.SEPTEMBER, 1);
        Calendar c4 = Calendar.getInstance();
        c4.set(2015, Calendar.SEPTEMBER, 30);
        MembershipFee mf2 = new MembershipFee(2, c3.getTime(), c4.getTime(), null);

        Calendar c5 = Calendar.getInstance();
        c5.set(2015, Calendar.AUGUST, 1);
        Calendar c6 = Calendar.getInstance();
        c6.set(2015, Calendar.AUGUST, 31);
        MembershipFee mf3 = new MembershipFee(3, c5.getTime(), c6.getTime(), null);

        mockMembershipFeeRepository.add(mf1);
        mockMembershipFeeRepository.add(mf2);
        mockMembershipFeeRepository.add(mf3);
    }

    @Override
    public MembershipFee getEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MembershipFee> getAllEntities() throws DataAccessServiceException {
        List<MembershipFee> fees = mockMembershipFeeRepository;
        return fees;
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
