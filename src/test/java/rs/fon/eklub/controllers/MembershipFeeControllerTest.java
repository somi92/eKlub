/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.util.ArrayList;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MembershipFeeService;
import rs.fon.eklub.dao.mock.MockMembershipFeeRepository;

/**
 *
 * @author milos
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class MembershipFeeControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private MembershipFeeService membershipFeeService;
    
    public MembershipFeeControllerTest() {
    }
    
    @Before
    public void setUp() {
        membershipFeeService = Mockito.mock(MembershipFeeService.class);
        
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        staticApplicationContext.registerSingleton("exceptionHandler", ExceptionHandlingController.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new MembershipFeeController(membershipFeeService))
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .build();
    }
    
    @Test
    public void getAllMembershipFeesOkTest() throws Exception {
        Mockito.when(membershipFeeService.getAllMembershipFees()).thenReturn(new MockMembershipFeeRepository().getAllEntities());
        mockMvc.perform(get(ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES)))
                .andExpect(jsonPath("$.payload[0].id", Is.is(1)))
                .andExpect(jsonPath("$.payload[1].id", Is.is(2)))
                .andExpect(jsonPath("$.payload[2].id", Is.is(3)));
    }
    
    @Test
    public void getAllMembershipFeesNotFoundTest() throws Exception {
        Mockito.when(membershipFeeService.getAllMembershipFees()).thenReturn(new ArrayList<>());
        mockMvc.perform(get(ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES)))
                .andExpect(jsonPath("$.payload", Matchers.hasSize(0)));
    }
    
    @Test
    public void getAllMembershipFeesExceptionTest() throws Exception {
        Mockito.when(membershipFeeService.getAllMembershipFees()).thenThrow(new ServiceException("MembershipFeesException"));
        mockMvc.perform(get(ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("MembershipFeesException")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.MembershipFee.GET_ALL_MEMBERSHIP_FEES)));
    }
}
