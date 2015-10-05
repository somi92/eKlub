/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.services;

import java.util.List;
import java.util.Map;
import rs.fon.eklub.core.entities.Training;

/**
 *
 * @author milos
 */
public interface TrainingService {
    
    public void saveTraining(Training training);
    public Training getTrainingById(long id);
    public List<Training> getTrainings(Map<String, Object> searchCriteria);
}
