/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.json.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rs.fon.eklub.core.entities.Training;

/**
 *
 * @author milos
 */
public interface AttendanceForTrainingSerializationMixin {
    
    @JsonIgnore Training getTraining();
}
