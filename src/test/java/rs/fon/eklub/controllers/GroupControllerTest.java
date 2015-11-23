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
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.GroupService;
import rs.fon.eklub.repositories.mocks.MockGroupRepository;

/**
 *
 * @author milos
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class GroupControllerTest {
    
    private MockMvc mockMvc;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    @Mock
    private GroupService groupService;
    
    public GroupControllerTest() {
    }
    
    @Before
    public void setUp() {
        groupService = Mockito.mock(GroupService.class);
        
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        staticApplicationContext.registerSingleton("exceptionHandler", ExceptionHandlingController.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);
        
        mockMvc = MockMvcBuilders.standaloneSetup(new GroupController(groupService))
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .build();
    }
    
    @Test
    public void getAllGroupsOkTest() throws Exception {
        Mockito.when(groupService.getAllGroups()).thenReturn(new MockGroupRepository().getAllEntities());
        mockMvc.perform(get(ServiceAPI.Group.GET_ALL_GROUPS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Group.GET_ALL_GROUPS)))
                .andExpect(jsonPath("$.payload[0].id", Is.is(1)))
                .andExpect(jsonPath("$.payload[1].id", Is.is(2)))
                .andExpect(jsonPath("$.payload[2].id", Is.is(3)));
    }
    
    @Test
    public void getAllGroupsExceptionTest() throws Exception {
        Mockito.when(groupService.getAllGroups()).thenThrow(new ServiceException("GroupsException"));
        mockMvc.perform(get(ServiceAPI.Group.GET_ALL_GROUPS))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorType", Is.is("ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("GroupsException")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Group.GET_ALL_GROUPS)));
    }
    
    @Test
    public void saveGroupOkTest() throws Exception {
        Group g = new Group();
        g.setId(1);
        String jsonGroup = convertEntityToJson(g);
        Mockito.doNothing().when(groupService).saveGroup(g);
        mockMvc.perform(post(ServiceAPI.Group.POST_SAVE_GROUP)
            .contentType(contentType)
            .content(jsonGroup))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
            .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED)))
            .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Group.POST_SAVE_GROUP)))
            .andExpect(jsonPath("$.payload", Is.is("Group "+g.getId()+" saved.")));
    }
    
    @Test
    public void saveGroupExceptionTest() throws Exception {
        Group g = new Group();
        g.setId(1);
        String jsonGroup = convertEntityToJson(g);
        Mockito.doThrow(new ServiceException("Group not saved.")).when(groupService).saveGroup(g);
        mockMvc.perform(post(ServiceAPI.Group.POST_SAVE_GROUP)
            .contentType(contentType)
            .content(jsonGroup))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errorType", Is.is("ServiceException")))
            .andExpect(jsonPath("$.errorMessage", Is.is("Group not saved.")))
            .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Group.POST_SAVE_GROUP)));
    }
    
    private String convertEntityToJson(Group g) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(g);
    }
}
