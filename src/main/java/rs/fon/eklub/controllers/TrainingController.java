/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.TrainingService;
import rs.fon.eklub.envelopes.ServiceResponse;
import rs.fon.eklub.util.Util;

/**
 *
 * @author milos
 */
@RestController
@Component
public class TrainingController {
    
    private TrainingService interactor;
    
    @Autowired
    public TrainingController(TrainingService interactor) {
        this.interactor = interactor;
    }
    
    @RequestMapping(value = ServiceAPI.Training.POST_SAVE_TRAINING,
                    method = RequestMethod.POST,
                    headers = {ServiceAPI.Headers.CONTENT_TYPE})
    public ResponseEntity saveTraining(@RequestBody Training training) throws ServiceException {
        interactor.saveTraining(training);
        ServiceResponse<String> response = new ServiceResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED);
        response.setRequestUri(ServiceAPI.Training.POST_SAVE_TRAINING);
        response.setPayload("Training "+training.getId()+" saved.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = ServiceAPI.Training.GET_TRAINING_BY_ID,
                    method = RequestMethod.GET)
    public ResponseEntity getTrainingById(@PathVariable long id) throws ServiceException {
        Training training = interactor.getTrainingById(id);
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(training == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<Training> response = new ServiceResponse();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Training.GET_TRAINING_BY_ID);
        response.setPayload(training);
        return new ResponseEntity<>(response, httpStatus);
    }
    
    /***
     * http://www.java-allandsundry.com/2014/09/customizing-httpmessageconverters-with.html
     * http://www.cowtowncoder.com/blog/archives/2009/08/entry_305.html
     * http://stackoverflow.com/questions/26529646/partial-json-serialization-of-rest-response-objects-in-spring-mvc
     * http://stackoverflow.com/questions/19997909/disable-auto-serializations-for-spring-mvc
     ***/
    @RequestMapping(value = ServiceAPI.Training.POST_SEARCH_TRAINING,
                    method = RequestMethod.POST,
                    headers = ServiceAPI.Headers.CONTENT_TYPE)
    public ResponseEntity getTrainings(@RequestBody HashMap<String, String> searchCriteria) throws ServiceException {
        List<Training> trainings = interactor.getTrainings(searchCriteria);
        HttpStatus httpStatus = null;
        String responseMessage = null;
        if(trainings == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            responseMessage = ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND;
        }
        ServiceResponse<List<Training>> response = new ServiceResponse<>();
        response.setStatus(httpStatus.toString());
        response.setMessage(responseMessage);
        response.setRequestUri(ServiceAPI.Training.POST_SEARCH_TRAINING);
        response.setPayload(trainings);
        return new ResponseEntity<>(response, httpStatus);
    }
    
}
