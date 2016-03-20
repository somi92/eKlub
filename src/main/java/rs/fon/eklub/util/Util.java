/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
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
    
    // TODO: consider implementing date filtering
    public static String generateHibernateLikeClause(Map<String, String> searchCriteria) {
        return generateHibernateLikeClause(searchCriteria, false);
    }
    
    public static String generateHibernateLikeClause(Map<String, String> searchCriteria, boolean isAndCondition) {
        boolean first = true;
        String conditionRelation = isAndCondition ? " and" : " or";
        String whereClause = "";
        for(String key : searchCriteria.keySet()) {
            whereClause = " " + key + " like '%" + searchCriteria.get(key) + "%'";
            if(!first) {
                whereClause = conditionRelation + whereClause;
                first = false;
            }
        }
        return " where " + whereClause;
    }
    
    public static String generateHibernateWhereClause(Map<String, String> searchCriteria) {
        boolean first = true;
        String whereClause = "";
        for(String key : searchCriteria.keySet()) {
            whereClause = " " + key + " = '" + searchCriteria.get(key) + "'";
            if(!first) {
                whereClause = " and" + whereClause;
                first = false;
            }
        }
        return " where " + whereClause;
    }
}
