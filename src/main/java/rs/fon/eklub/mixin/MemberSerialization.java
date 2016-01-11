/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import rs.fon.eklub.core.entities.Attendance;

/**
 *
 * @author milos
 */
public interface MemberSerialization {
    
    @JsonIgnore List<Attendance> getAttendances();
}
