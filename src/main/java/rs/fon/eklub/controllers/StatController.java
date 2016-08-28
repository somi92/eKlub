/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Stat;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.interactors.StatInteractor;
import rs.fon.eklub.envelopes.ServiceResponse;

/**
 *
 * @author milos
 */
@RestController
@Component
public class StatController {
    
    private StatInteractor interactor;

    @Autowired
    public StatController(StatInteractor interactor) {
        this.interactor = interactor;
    }
    
    @PreAuthorize(value = "#oauth2.hasAnyScope('global')")
    @RequestMapping(value = ServiceAPI.Stat.GET_STATS,
                    method = RequestMethod.GET)
    public ResponseEntity getStats(@PathVariable String target) throws ServiceException {
        List<Stat> stats = new ArrayList<>();
        if(target.equals("entities"))
            stats = interactor.getEntityStats();
        if(target.equals("categories"))
            stats = interactor.getMembersByCategoryStats();
        if(target.equals("attendances"))
            stats = interactor.getAttendanceStats();
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(stats.size() <= 0) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<List<Stat>> response = new ServiceResponse();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Stat.GET_STATS);
        response.setPayload(stats);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
