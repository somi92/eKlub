/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author milos
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Main extends SpringBootServletInitializer {
    
    private static Class<Main> mainClass = Main.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(mainClass);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(mainClass, args);
    }
}
