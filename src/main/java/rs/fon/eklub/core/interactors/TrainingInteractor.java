/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.dal.DataAccessService;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.TrainingService;
import rs.fon.eklub.core.validators.EntityValidator;

/**
 *
 * @author milos
 */
public class TrainingInteractor implements TrainingService {

    private DataAccessService<Training> dao;
    private EntityValidator<Training> validator;

    public TrainingInteractor() {
    }
    
    public TrainingInteractor(DataAccessService<Training> dao, EntityValidator<Training> validator) {
        this.dao = dao;
        this.validator = validator;
    }
    
     public DataAccessService<Training> getDao() {
        return dao;
    }

    public void setDao(DataAccessService<Training> dao) {
        this.dao = dao;
    }

    public EntityValidator<Training> getValidator() {
        return validator;
    }

    public void setValidator(EntityValidator<Training> validator) {
        this.validator = validator;
    }
    
    @Override
    public void saveTraining(Training training) throws ServiceException {
        if(training == null) {
            throw new ServiceException("Payments null or empty!");
        }
        if(validator.validateEntity(training)) {
            dao.insertOrUpdateEntity(training);
        }
    }

    @Override
    public Training getTrainingById(long id) throws ServiceException {
        Training training = dao.getEntity(id);
        return training;
    }

    @Override
    public List<Training> getTrainings(Map<String, Object> searchCriteria) throws ServiceException {
        List<Training> trainings = dao.getEntities(searchCriteria);
        return trainings;
    } 
}
