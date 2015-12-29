/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Util {
    
    public static <T> String convertEntityToJson(T entity) {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(entity);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static <T> T convertJsonToEntity(String json, Class<T> typeClass) {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.readValue(json, typeClass);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
