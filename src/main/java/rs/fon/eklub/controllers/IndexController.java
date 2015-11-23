/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import rs.fon.eklub.envelopes.ServiceErrorResponse;

/**
 *
 * @author milos
 */
@RestController
public class IndexController {
    
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
    
    @RequestMapping(method = {RequestMethod.GET, 
                              RequestMethod.POST, 
                              RequestMethod.DELETE,
                              RequestMethod.PUT,
                              RequestMethod.HEAD})
    @ResponseBody
    public ResponseEntity<String> notFoundException(HttpServletRequest rq) {
        return new ResponseEntity(
                new ServiceErrorResponse("404", "Resource not found.", rq.getRequestURI()), 
                HttpStatus.NOT_FOUND);
    }
}
