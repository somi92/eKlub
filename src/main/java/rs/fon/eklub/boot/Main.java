/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author milos
 */

@Configuration
@ComponentScan(basePackages = "rs.fon.eklub.controllers")
@EnableAutoConfiguration
public class Main {
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
