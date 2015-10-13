/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Attendance;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.TrainingService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class TrainingInteractorTest {
    
    private TrainingService ts;
    
    private DataAccessService<Training> dao;
    private EntityValidator<Training> validator;
    
    private List<Training> mockTrainingRepository;
    
    public TrainingInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
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
        
        dao = new DataAccessService<Training>() {

            @Override
            public Training getEntity(long id) throws DataAccessServiceException {
                if(id == 13) {
                    throw new DataAccessServiceException("Data access error!");
                } else {
                    for(Training t : mockTrainingRepository) {
                        if(t.getId() == id) {
                            return t;
                        }
                    }
                    return null;
                }
            }

            @Override
            public List<Training> getAllEntities() throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Training> getEntities(Map<String, Object> searchCriteria) throws DataAccessServiceException {
                List<Training> trainings = new ArrayList<>();
                long id = searchCriteria.get("id") == null ? 0 : Long.parseLong(searchCriteria.get("id").toString());
                if(id == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                for(Training t : mockTrainingRepository) {
                    if(t.getGroup().equals((Group) searchCriteria.get("group"))) {
                        trainings.add(t);
                    }
                }
                return trainings;
            }

            @Override
            public void insertOrUpdateEntity(Training entity) throws DataAccessServiceException {
                if(entity.getId() == 13) {
                    throw new DataAccessServiceException("Data access error!");
                }
                if(!mockTrainingRepository.contains(entity)) {
                    mockTrainingRepository.add(entity);
                }
            }

            @Override
            public boolean deleteEntity(long id) throws DataAccessServiceException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        validator = new EntityValidator<Training>() {

            @Override
            public boolean validateEntity(Training entity) throws ValidationException {
                if(entity.getDurationMinutes() < 0) {
                    throw new ValidationException("Validation exception!");
                }
                return true;
            }
        };
        
        ts = new TrainingInteractor(dao, validator);
    }
    
    @Test
    public void initializeTrainingInteractorOkTest() {
        ts = new TrainingInteractor(dao, validator);
        assertNotNull(ts);
    }
    
    @Test
    public void saveTrainingOkTest() throws ServiceException {
        Training t = new Training();
        t.setId(100);
        Attendance a1 = new Attendance(1, new Member(3), t, true, 0);
        Attendance a2 = new Attendance(2, new Member(2), t, true, 0);
        List<Attendance> a = new ArrayList<>();
        a.add(a1);
        a.add(a2);
        t.setAttendaces(a);
        ts.saveTraining(t);
        assertTrue(mockTrainingRepository.contains(t));
    }
    
    @Test(expected = ServiceException.class)
    public void saveTrainingNullEntityTest() throws ServiceException {
        Training t = null;
        ts.saveTraining(t);
        assertFalse(mockTrainingRepository.contains(t));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void saveTrainingDataExceptionTest() throws ServiceException {
        Training t = new Training();
        t.setId(13);
        ts.saveTraining(t);
        assertFalse(mockTrainingRepository.contains(t));
    }
    
    @Test(expected = ValidationException.class)
    public void saveTrainingValidationExceptionTest() throws ServiceException {
        Training t = new Training();
        t.setId(100);
        t.setDurationMinutes(-1);
        ts.saveTraining(t);
        assertFalse(mockTrainingRepository.contains(t));
    }
    
    @Test
    public void getTrainingByIdOkTest() throws ServiceException {
        Training t1 = ts.getTrainingById(1);
        Training t2 = ts.getTrainingById(3);
        assertNotNull(t1);
        assertNotNull(t2);
        assertTrue(mockTrainingRepository.contains(t1));
        assertTrue(mockTrainingRepository.contains(t2));
    }
    
    @Test
    public void getTrainingByIdNotFoundTest() throws ServiceException {
        Training t = ts.getTrainingById(200);
        assertNull(t);
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getTrainingByIdDataExceptionTest() throws ServiceException {
        Training t = ts.getTrainingById(13);
        assertNull(t);
    }
    
    @Test
    public void getTrainingsOkTest() throws ServiceException {
        Map<String, Object> searchCriteria = new HashMap<>();
        searchCriteria.put("group", new Group(1, null, null, null));
        List<Training> trainings = ts.getTrainings(searchCriteria);
        assertTrue(trainings.size() == 2);
    }
    
    @Test
    public void getTrainingsEmptyTest() throws ServiceException {
        Map<String, Object> searchCriteria = new HashMap<>();
        searchCriteria.put("group", new Group(3, null, null, null));
        List<Training> trainings = ts.getTrainings(searchCriteria);
        assertTrue(trainings.isEmpty());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getTrainingsDataExceptionTest() throws ServiceException {
        Map<String, Object> searchCriteria = new HashMap<>();
        searchCriteria.put("id", 13);
        List<Training> trainings = ts.getTrainings(searchCriteria);
        assertNull(trainings);
    }
}
