/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.boot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rs.fon.eklub.core.interactors.CategoryInteractor;
import rs.fon.eklub.core.interactors.GroupInteractor;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.core.validators.MockGroupValidator;
import rs.fon.eklub.repositories.mocks.MockCategoryExceptionRepository;
import rs.fon.eklub.repositories.mocks.MockCategoryRepository;
import rs.fon.eklub.repositories.mocks.MockGroupRepository;

/**
 *
 * @author milos
 */

@Configuration
@ComponentScan(basePackages = "rs.fon.eklub")
@EnableAutoConfiguration
public class Main {
    
    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                System.out.println("ServletContext initialized");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                System.out.println("ServletContext destroyed");
            }
        };
    }
    
    @Bean
    CategoryService getCategoryInteractor() {
        return new CategoryInteractor(new MockCategoryRepository());
    }
    
    @Bean
    GroupInteractor getGroupInteractor() {
        return new GroupInteractor(new MockGroupRepository(), 
                new MockGroupValidator());
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
