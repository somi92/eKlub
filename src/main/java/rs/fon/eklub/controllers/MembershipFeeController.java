/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.eklub.core.services.MembershipFeeService;

/**
 *
 * @author milos
 */
@RestController
@Component
public class MembershipFeeController {
    
    private MembershipFeeService interactor;
    
    @Autowired
    public MembershipFeeController(MembershipFeeService interactor) {
        this.interactor = interactor;
    }
    
}
