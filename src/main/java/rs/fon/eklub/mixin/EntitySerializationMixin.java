/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.mixin;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author milos
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
public class EntitySerializationMixin {
    
}
