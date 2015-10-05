/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.ServiceException;

/**
 *
 * @author milos
 */
public interface TrainingService {
    
    public void saveTraining(Training training) throws ServiceException;
    public Training getTrainingById(long id) throws ServiceException;
    public List<Training> getTrainings(Map<String, Object> searchCriteria) throws ServiceException;
}
