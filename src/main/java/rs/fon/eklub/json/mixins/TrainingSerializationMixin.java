/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.json.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import rs.fon.eklub.core.entities.Attendance;
import rs.fon.eklub.core.entities.Group;

/**
 *
 * @author milos
 */
public interface TrainingSerializationMixin {
    
    @JsonIgnore Group getGroup();
    @JsonIgnore List<Attendance> getAttendances();
}
