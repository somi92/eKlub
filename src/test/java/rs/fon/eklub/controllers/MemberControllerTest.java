/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MemberService;

/**
 *
 * @author milos
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class MemberControllerTest {
    
    private MockMvc mockMvc;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    @Mock
    private MemberService memberService;

    public MemberControllerTest() {
    }
    
    @Before
    public void setUp() {
        memberService = Mockito.mock(MemberService.class);
        
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        staticApplicationContext.registerSingleton("exceptionHandler", ExceptionHandlingController.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new MemberController(memberService))
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .build();
    }
    
    @Test
    public void saveMemberOkTest() throws Exception {
        Member m = new Member();
        m.setId(10);
        String jsonMember = convertEntityToJson(m);
        Mockito.doNothing().when(memberService).saveMember(m);
        mockMvc.perform(post(ServiceAPI.Member.POST_SAVE_MEMBER)
                .contentType(contentType)
                .content(jsonMember))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.POST_SAVE_MEMBER)))
                .andExpect(jsonPath("$.payload", Is.is("Member "+m.getId()+" saved.")));
    }
    
    @Test
    public void saveMemberExceptionTest() throws Exception {
        Member m = new Member();
        m.setId(10);
        String jsonMember = convertEntityToJson(m);
        Mockito.doThrow(new ServiceException("Member not saved.")).when(memberService).saveMember(m);
        mockMvc.perform(post(ServiceAPI.Member.POST_SAVE_MEMBER)
                .contentType(contentType)
                .content(jsonMember))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Member not saved.")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.POST_SAVE_MEMBER)));
    }
    
    @Test
    public void getMemberByIdOkTest() throws Exception {
        Member m = new Member();
        m.setId(10);
        m.setNameSurname("Petar Petrovic");
        Mockito.when(memberService.getMemberById(10)).thenReturn(m);
        mockMvc.perform(get("/members/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.GET_MEMBER_BY_ID)))
                .andExpect(jsonPath("$.payload.id", Is.is((int)m.getId())))
                .andExpect(jsonPath("$.payload.nameSurname", Is.is(m.getNameSurname())));
    }
    
    @Test
    public void getMemberByIdExceptionTest() throws Exception {
        Member m = new Member();
        m.setId(10);
        m.setNameSurname("Petar Petrovic");
        Mockito.doThrow(new ServiceException("Get member by id error.")).when(memberService).getMemberById(10);
        mockMvc.perform(get("/members/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Get member by id error.")))
                .andExpect(jsonPath("$.requestUri", Is.is("/members/10")));
                
    }
    
    @Test
    public void deleteMemberOkTest() throws Exception {
        
    }
    
    
    private String convertEntityToJson(Member m) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(m);
    }
}
