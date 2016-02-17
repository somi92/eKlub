/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.GregorianCalendar;
import java.util.List;
import org.hamcrest.core.Is;
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
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.PaymentService;
import rs.fon.eklub.util.Util;

/**
 *
 * @author milos
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class PaymentControllerTest {
    
    private MockMvc mockMvc;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    @Mock
    private PaymentService paymentService;
    
    public PaymentControllerTest() {
    }
    
    @Before
    public void setUp() {
        paymentService = Mockito.mock(PaymentService.class);
        
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        staticApplicationContext.registerSingleton("exceptionHandler", ExceptionHandlingController.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new PaymentController(paymentService))
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .build();
    }
    
    @Test
    public void savePaymentsOkTest() throws Exception {
        MembershipFee fee1 = new MembershipFee(1, new GregorianCalendar(2016, 0, 17).getTime(), 
                new GregorianCalendar(2016, 1, 17).getTime(), "fee1");
        MembershipFee fee2 = new MembershipFee(1, new GregorianCalendar(2016, 1, 17).getTime(), 
                new GregorianCalendar(2016, 2, 17).getTime(), "fee2");
        Member member = new Member(1);
        Payment p1 = new Payment(1, fee1, 2000, new GregorianCalendar(2016, 0, 17).getTime(), member);
        Payment p2 = new Payment(2, fee2, 2000, new GregorianCalendar(2016, 1, 17).getTime(), member);
        List<Payment> payments = new ArrayList<>();
        payments.add(p1);
        payments.add(p2);
        String jsonPayments = Util.convertEntityToJson(payments);
        Mockito.doNothing().when(paymentService).savePayments(payments);
        mockMvc.perform(post(ServiceAPI.Payment.POST_SAVE_PAYMENTS)
                .contentType(contentType)
                .content(jsonPayments))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Payment.POST_SAVE_PAYMENTS)))
                .andExpect(jsonPath("$.payload", Is.is(payments.size() + " payments saved")));
    }
    
    @Test
    public void savePaymentsExceptionTest() throws Exception {
        MembershipFee fee1 = new MembershipFee(1, new GregorianCalendar(2016, 0, 17).getTime(), 
                new GregorianCalendar(2016, 1, 17).getTime(), "fee1");
        MembershipFee fee2 = new MembershipFee(1, new GregorianCalendar(2016, 1, 17).getTime(), 
                new GregorianCalendar(2016, 2, 17).getTime(), "fee2");
        Member member = new Member(1);
        Payment p1 = new Payment(1, fee1, 2000, new GregorianCalendar(2016, 0, 17).getTime(), member);
        Payment p2 = new Payment(2, fee2, 2000, new GregorianCalendar(2016, 1, 17).getTime(), member);
        List<Payment> payments = new ArrayList<>();
        payments.add(p1);
        payments.add(p2);
        String jsonPayments = Util.convertEntityToJson(payments);
        Mockito.doThrow(new ServiceException("Payments not saved.")).when(paymentService).savePayments(payments);
        mockMvc.perform(post(ServiceAPI.Payment.POST_SAVE_PAYMENTS)
                .contentType(contentType)
                .content(jsonPayments))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Payments not saved.")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Payment.POST_SAVE_PAYMENTS)));
    }
}
