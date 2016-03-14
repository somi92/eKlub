/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.dao.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Attendance;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;

/**
 *
 * @author milos
 */
public class MockTrainingRepository implements DataAccessService<Training> {

    private List<Training> mockTrainingRepository;

    public MockTrainingRepository() {

        mockTrainingRepository = new ArrayList<>();

        Training t1 = new Training();
        t1.setId(1);
        t1.setGroup(new Group(1, null, null, null));
        Training t2 = new Training();
        t2.setId(2);
        t2.setGroup(new Group(2, null, null, null));
        Training t3 = new Training();
        t3.setId(3);
        t3.setGroup(new Group(1, null, null, null));

        Attendance a11 = new Attendance(1, new Member(3), t1, true, 0);
        Attendance a12 = new Attendance(2, new Member(2), t1, true, 0);
        Attendance a21 = new Attendance(3, new Member(1), t2, true, 0);
        Attendance a22 = new Attendance(4, new Member(4), t2, true, 0);
        Attendance a31 = new Attendance(5, new Member(5), t3, true, 0);
        Attendance a32 = new Attendance(6, new Member(6), t3, true, 0);

        List<Attendance> a1 = new ArrayList<>();
        a1.add(a11);
        a1.add(a12);
        t1.setAttendaces(a1);

        List<Attendance> a2 = new ArrayList<>();
        a2.add(a21);
        a2.add(a22);
        t2.setAttendaces(a2);

        List<Attendance> a3 = new ArrayList<>();
        a3.add(a31);
        a3.add(a32);
        t3.setAttendaces(a3);

        mockTrainingRepository.add(t1);
        mockTrainingRepository.add(t2);
        mockTrainingRepository.add(t3);
    }

    @Override
    public Training getEntity(long id) throws DataAccessServiceException {
        if (id == 13) {
            throw new DataAccessServiceException("Data access error!");
        } else {
            for (Training t : mockTrainingRepository) {
                if (t.getId() == id) {
                    return t;
                }
            }
            return null;
        }
    }

    @Override
    public List<Training> getAllEntities() throws DataAccessServiceException {
        return mockTrainingRepository;
    }

    @Override
    public List<Training> getEntities(Map<String, String> searchCriteria) throws DataAccessServiceException {
        List<Training> trainings = new ArrayList<>();
        long id = searchCriteria.get("id") == null ? 0 : Long.parseLong(searchCriteria.get("id"));
        if (id == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        for (Training t : mockTrainingRepository) {
            if ((t.getGroup().getId()+"").equals(searchCriteria.get("group"))) {
                trainings.add(t);
            }
        }
        return trainings;
    }

    @Override
    public void insertOrUpdateEntity(Training entity) throws DataAccessServiceException {
        if (entity.getId() == 13) {
            throw new DataAccessServiceException("Data access error!");
        }
        if (!mockTrainingRepository.contains(entity)) {
            mockTrainingRepository.add(entity);
        }
    }

    @Override
    public boolean deleteEntity(long id) throws DataAccessServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
