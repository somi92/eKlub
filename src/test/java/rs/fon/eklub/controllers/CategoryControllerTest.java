/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.hamcrest.core.Is;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import rs.fon.eklub.boot.Main;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.exceptions.DataAccessServiceException;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.interactors.CategoryInteractor;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.repositories.mocks.MockCategoryExceptionRepository;
import rs.fon.eklub.repositories.mocks.MockCategoryRepository;

/**
 *
 * @author milos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CategoryControllerTest.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CategoryControllerTest.class})
public class CategoryControllerTest {
    
    private MockMvc mockMvc;
    
//    private CategoryService categoryService;
    
    @Bean
    CategoryService getCategoryInteractor() {
        return new CategoryInteractor(new MockCategoryExceptionRepository());
    }
    
    @Autowired
    private CategoryController categoryController;
    
//    @Autowired
//    private WebApplicationContext webApplicationContext;
    
    public CategoryControllerTest() {
    }
    
    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(categoryController).build();
    }
    
    @Test
    public void getAllCategoriesOkTest() throws Exception {
        mockMvc.perform(get(ServiceAPI.Category.GET_ALL_CATEGORIES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[1].id", Is.is(2)))
                .andExpect(jsonPath("$[2].id", Is.is(3)));
    }
    
    @Test
    public void getAllCategoriesExceptionTest() throws Exception {
        mockMvc.perform(get(ServiceAPI.Category.GET_ALL_CATEGORIES))
                .andExpect(status().isNotFound());
    }
    
//    @Test
//    public void getAllCategoriesExceptionTest() throws Exception {
////            MockitoAnnotations.initMocks(this);
//        categoryService = new CategoryInteractor(new MockCategoryExceptionRepository());
////        categoryService = Mockito.mock(CategoryInteractor.class);
////        mockMvc = standaloneSetup(categoryController).build();
////            Mockito.when(categoryService.getAllCategories()).thenThrow(new DataAccessServiceException("Message"));
//        ResultActions perform = mockMvc.perform(get(ServiceAPI.Category.GET_ALL_CATEGORIES));
//        
//    }
}
