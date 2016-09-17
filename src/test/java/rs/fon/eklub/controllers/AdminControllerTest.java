/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.nio.charset.Charset;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Employee;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.AdminService;
import rs.fon.eklub.util.Util;

/**
 *
 * @author milos
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class AdminControllerTest {
    
    private MockMvc mockMvc;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    @Mock
    private AdminService adminService;

    public AdminControllerTest() {
    }
    
    @Before
    public void setUp() {
        adminService = Mockito.mock(AdminService.class);
        
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        staticApplicationContext.registerSingleton("exceptionHandler", ExceptionHandlingController.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(adminService))
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .build();
    }
    
    @Test
    public void getAdminOkTest() throws Exception {
        Employee e = new Employee();
        e.setId(3);
        e.setUsername("admin3");
        String json = Util.convertEntityToJson(e);
        Mockito.when(adminService.getAdmin(e.getUsername())).thenReturn(e);
        mockMvc.perform(post(ServiceAPI.Admin.POST_GET_ADMIN)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Admin.POST_GET_ADMIN)))
                .andExpect(jsonPath("$.payload.id", Is.is((int)e.getId())))
                .andExpect(jsonPath("$.payload.username", Is.is(e.getUsername())));
    }
    
    @Test
    public void getAdminNotFoundTest() throws Exception {
        Employee e = new Employee();
        e.setId(3);
        e.setUsername("admin4");
        String json = Util.convertEntityToJson(e);
        Mockito.when(adminService.getAdmin(e.getUsername())).thenReturn(null);
        mockMvc.perform(post(ServiceAPI.Admin.POST_GET_ADMIN)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Admin.POST_GET_ADMIN)))
                .andExpect(jsonPath("$.payload", Is.is((IsNull.nullValue()))));
    }
    
    @Test
    public void getAdminExceptionTest() throws Exception {
        Employee e = new Employee();
        e.setId(3);
        e.setUsername("admin4");
        String json = Util.convertEntityToJson(e);
        Mockito.doThrow(new ServiceException("Get admin error.")).when(adminService)
                .getAdmin(e.getUsername());
        mockMvc.perform(post(ServiceAPI.Admin.POST_GET_ADMIN)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Get admin error.")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Admin.POST_GET_ADMIN)));
    }
}
