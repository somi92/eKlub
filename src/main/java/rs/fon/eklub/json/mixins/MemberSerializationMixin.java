/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.json.mixins;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import rs.fon.eklub.core.entities.Attendance;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.entities.Payment;

/**
 *
 * @author milos
 */
//@JsonFilter("memberFilter")
public interface MemberSerializationMixin {
    
    @JsonIgnore List<Attendance> getAttendances();
    @JsonIgnore List<Payment> getPayments();
    @JsonIgnore Group getGroup();
}
