/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.envelopes;

import java.util.List;
import org.springframework.http.HttpStatus;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Category;

/**
 *
 * @author milos
 */
public class ServiceResponseFactory {
 
    public static <T> ServiceResponse createResponse(T entity) {
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(entity == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<T> response = new ServiceResponse(); 
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Category.GET_ALL_CATEGORIES);
        response.setPayload(entity);
        return response;
    }
}
