/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.repositories.mocks.MockCategoryRepository;

/**
 *
 * @author milos
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class CategoryControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private CategoryService categoryService;
    
    public CategoryControllerTest() {
    }
    
    @Before
    public void setUp() {
        categoryService = Mockito.mock(CategoryService.class);
        
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        staticApplicationContext.registerSingleton("exceptionHandler", ExceptionHandlingController.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoryController(categoryService))
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .build();
    }
    
    @Test
    public void getAllCategoriesOkTest() throws Exception {
        Mockito.when(categoryService.getAllCategories()).thenReturn(new MockCategoryRepository().getAllEntities());
        mockMvc.perform(get(ServiceAPI.Category.GET_ALL_CATEGORIES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Category.GET_ALL_CATEGORIES)))
                .andExpect(jsonPath("$.payload[0].id", Is.is(1)))
                .andExpect(jsonPath("$.payload[1].id", Is.is(2)))
                .andExpect(jsonPath("$.payload[2].id", Is.is(3)));
    }
    
    @Test
    public void getAllCategoriesNotFoundTest() throws Exception {
        Mockito.when(categoryService.getAllCategories()).thenReturn(null);
        mockMvc.perform(get(ServiceAPI.Category.GET_ALL_CATEGORIES))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Category.GET_ALL_CATEGORIES)))
                .andExpect(jsonPath("$.payload", Is.is(IsNull.nullValue())));
    }
    
    @Test
    public void getAllCategoriesExceptionTest() throws Exception {
        Mockito.when(categoryService.getAllCategories()).thenThrow(new ServiceException("CategoriesException"));
        mockMvc.perform(get(ServiceAPI.Category.GET_ALL_CATEGORIES))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("CategoriesException")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Category.GET_ALL_CATEGORIES)));
    }
}
