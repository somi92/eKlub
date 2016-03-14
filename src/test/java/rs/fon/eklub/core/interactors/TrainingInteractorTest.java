/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.exceptions.ValidationException;
import rs.fon.eklub.core.services.TrainingService;
import rs.fon.eklub.core.validators.EntityValidator;
import rs.fon.eklub.core.validators.MockTrainingValidator;
import rs.fon.eklub.dao.mock.MockTrainingRepository;

/**
 *
 * @author milos
 */
public class TrainingInteractorTest {
    
    private TrainingService ts;
    
    private DataAccessService<Training> dao;
    private EntityValidator<Training> validator;
    
    public TrainingInteractorTest() {
    }
    
    @Before
    public void setUp() {
        
        dao = new MockTrainingRepository();
        validator = new MockTrainingValidator();
        
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
        ts.saveTraining(t);
        assertTrue(dao.getAllEntities().contains(t));
    }
    
    @Test(expected = ServiceException.class)
    public void saveTrainingNullEntityTest() throws ServiceException {
        Training t = null;
        ts.saveTraining(t);
        assertFalse(dao.getAllEntities().contains(t));
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void saveTrainingDataExceptionTest() throws ServiceException {
        Training t = new Training();
        t.setId(13);
        ts.saveTraining(t);
        assertFalse(dao.getAllEntities().contains(t));
    }
    
    @Test(expected = ValidationException.class)
    public void saveTrainingValidationExceptionTest() throws ServiceException {
        Training t = new Training();
        t.setId(100);
        t.setDurationMinutes(-1);
        ts.saveTraining(t);
        assertFalse(dao.getAllEntities().contains(t));
    }
    
    @Test
    public void getTrainingByIdOkTest() throws ServiceException {
        Training t1 = ts.getTrainingById(1);
        Training t2 = ts.getTrainingById(3);
        assertNotNull(t1);
        assertNotNull(t2);
        assertTrue(dao.getAllEntities().contains(t1));
        assertTrue(dao.getAllEntities().contains(t2));
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
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("group", "1");
        List<Training> trainings = ts.getTrainings(searchCriteria);
        assertTrue(trainings.size() == 2);
    }
    
    @Test
    public void getTrainingsEmptyTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("group", "3");
        List<Training> trainings = ts.getTrainings(searchCriteria);
        assertTrue(trainings.isEmpty());
    }
    
    @Test(expected = DataAccessServiceException.class)
    public void getTrainingsDataExceptionTest() throws ServiceException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("id", 13+"");
        List<Training> trainings = ts.getTrainings(searchCriteria);
        assertNull(trainings);
    }
}
