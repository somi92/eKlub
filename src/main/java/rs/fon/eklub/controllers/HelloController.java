/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author milos
 */
@RestController
public class HelloController {
    
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "eKlub hello controller - index method";
    }
    
    @RequestMapping("/test/{param}")
    @ResponseBody
    public String test(@PathVariable String param) {
        return "eKlub hello controller - test method - param: "+param;
    }
}
