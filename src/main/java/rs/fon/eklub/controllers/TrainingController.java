/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.core.services.TrainingService;

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
    
   
    public ResponseEntity getTrainingById(long id) {
        return null;
    }
}
