/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hamcrest.Matchers;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.MemberService;
import rs.fon.eklub.dao.mock.MockMemberRepository;
import rs.fon.eklub.util.Util;

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
        String jsonMember = Util.convertEntityToJson(m);
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
        String jsonMember = Util.convertEntityToJson(m);
        Mockito.doThrow(new ServiceException("Member not saved.")).when(memberService).saveMember(m);
        mockMvc.perform(post(ServiceAPI.Member.POST_SAVE_MEMBER)
                .contentType(contentType)
                .content(jsonMember))
                .andExpect(status().isInternalServerError())
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
    public void getMemberByIdNotFoundTest() throws Exception {
        Member m = new Member();
        m.setId(100);
        m.setNameSurname("Petar Petrovic");
        Mockito.when(memberService.getMemberById(10)).thenReturn(null);
        mockMvc.perform(get("/members/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.GET_MEMBER_BY_ID)))
                .andExpect(jsonPath("$.payload", Is.is((IsNull.nullValue()))));
    }
    
    @Test
    public void getMemberByIdExceptionTest() throws Exception {
        Member m = new Member();
        m.setId(10);
        m.setNameSurname("Petar Petrovic");
        Mockito.doThrow(new ServiceException("Get member by id error.")).when(memberService).getMemberById(10);
        mockMvc.perform(get("/members/10"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Get member by id error.")))
                .andExpect(jsonPath("$.requestUri", Is.is("/members/10")));
                
    }
    
    @Test
    public void deleteMemberOkTest() throws Exception {
        Mockito.when(memberService.deleteMember(1)).thenReturn(true);
        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_DELETED)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.DELETE_MEMBER_BY_ID)))
                .andExpect(jsonPath("$.payload", Is.is(true)));
    }
    
    
    @Test
    public void deleteMemberNotFoundTest() throws Exception {
        Mockito.when(memberService.deleteMember(100)).thenReturn(false);
        mockMvc.perform(delete("/members/100"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.DELETE_MEMBER_BY_ID)))
                .andExpect(jsonPath("$.payload", Is.is(false)));
    }
    
    @Test
    public void deleteMemberExceptionTest() throws Exception {
        Mockito.doThrow(new ServiceException("Delete member exception")).when(memberService).deleteMember(100);
        mockMvc.perform(delete("/members/100"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Delete member exception")))
                .andExpect(jsonPath("$.requestUri", Is.is("/members/100")));
    }
    
    @Test
    public void getAllMembersOkTest() throws Exception {
        Mockito.when(memberService.getAllMembers()).thenReturn(new MockMemberRepository().getAllEntities());
        mockMvc.perform(get(ServiceAPI.Member.GET_ALL_MEMBERS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.GET_ALL_MEMBERS)))
                .andExpect(jsonPath("$.payload[0].id", Is.is(1)))
                .andExpect(jsonPath("$.payload[1].id", Is.is(2)))
                .andExpect(jsonPath("$.payload[2].id", Is.is(3)));
    }
    
    @Test
    public void getAllMembersNotFoundTest() throws Exception {
        Mockito.when(memberService.getAllMembers()).thenReturn(new ArrayList<>());
        mockMvc.perform(get(ServiceAPI.Member.GET_ALL_MEMBERS))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.GET_ALL_MEMBERS)))
                .andExpect(jsonPath("$.payload", Matchers.hasSize(0)));
    }
    
    @Test
    public void getAllMembersExceptionTest() throws Exception {
         Mockito.when(memberService.getAllMembers()).thenThrow(new ServiceException("MembersException"));
         mockMvc.perform(get(ServiceAPI.Member.GET_ALL_MEMBERS))
                 .andExpect(status().isInternalServerError())
                 .andExpect(jsonPath("$.status", Is.is(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                 .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                 .andExpect(jsonPath("$.errorMessage", Is.is("MembersException")))
                 .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.GET_ALL_MEMBERS)));
    }
    
    @Test
    public void getMembersOkTest() throws Exception {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("gender", "M");
        List<Member> members = new MockMemberRepository().getEntities(searchCriteria);
        Mockito.when(memberService.getMembers(searchCriteria)).thenReturn(members);
        mockMvc.perform(post(ServiceAPI.Member.POST_SEARCH_MEMBERS)
                .contentType(contentType)
                .content(Util.convertEntityToJson(searchCriteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.POST_SEARCH_MEMBERS)))
                .andExpect(jsonPath("$.payload[0].gender", Is.is("M")))
                .andExpect(jsonPath("$.payload[1].gender", Is.is("M")));
    }
    
    @Test
    public void getMembersNotFoundTest() throws Exception {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("gender", "X");
        Mockito.when(memberService.getMembers(searchCriteria)).thenReturn(new ArrayList<>());
        mockMvc.perform(post(ServiceAPI.Member.POST_SEARCH_MEMBERS)
                .contentType(contentType)
                .content(Util.convertEntityToJson(searchCriteria)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.POST_SEARCH_MEMBERS)))
                .andExpect(jsonPath("$.payload", Matchers.hasSize(0)));
    }
    
    @Test
    public void getMembersExceptionTest() throws Exception {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("gender", "X");
        Mockito.when(memberService.getMembers(searchCriteria)).thenThrow(new ServiceException("MembersException"));
        mockMvc.perform(post(ServiceAPI.Member.POST_SEARCH_MEMBERS)
                .contentType(contentType)
                .content(Util.convertEntityToJson(searchCriteria)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("MembersException")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Member.POST_SEARCH_MEMBERS)));
    }
}
