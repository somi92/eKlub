/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.controllers;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import rs.fon.eklub.boot.Main;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.interactors.GroupInteractor;
import rs.fon.eklub.core.services.GroupService;
import rs.fon.eklub.core.validators.MockGroupValidator;
import rs.fon.eklub.repositories.mocks.MockGroupExceptionRepository;

/**
 *
 * @author milos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Main.class})
public class GroupControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private GroupService groupService;
    
    @InjectMocks
    private GroupController groupController;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    public GroupControllerTest() {
    }
    
    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void getAllGroupsOkTest() throws Exception {
        mockMvc.perform(get(ServiceAPI.Group.GET_ALL_GROUPS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[1].id", Is.is(2)))
                .andExpect(jsonPath("$[2].id", Is.is(3)));
    }
    
//    @Test(expected = ServiceException.class)
//    public void getAllGroupsExceptionTest() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        groupService = new GroupInteractor(new MockGroupExceptionRepository(),
//                new MockGroupValidator());
//        mockMvc = standaloneSetup(groupController).build();
//        Mockito.when(groupService.getAllGroups()).thenReturn(null);
//        mockMvc.perform(get(ServiceAPI.Group.GET_ALL_GROUPS)).andExpect(status().isNotFound());
//    }
}
