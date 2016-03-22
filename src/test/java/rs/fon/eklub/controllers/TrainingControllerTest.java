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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.TrainingService;
import rs.fon.eklub.dao.mock.MockTrainingRepository;
import rs.fon.eklub.json.converters.JsonHttpConverter;
import rs.fon.eklub.util.Util;

/**
 *
 * @author milos
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TrainingControllerTest {
    
    private MockMvc mockMvc;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    @Mock
    private TrainingService trainingService;
    
    public TrainingControllerTest() {
    }
    
    @Before
    public void setUp() {
        trainingService = Mockito.mock(TrainingService.class);
        
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        staticApplicationContext.registerSingleton("exceptionHandler", ExceptionHandlingController.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);
        
        
        mockMvc = MockMvcBuilders
                .standaloneSetup(new TrainingController(trainingService))
                .setMessageConverters(new JsonHttpConverter())
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .build();
    }
    
    @Test
    public void saveTrainingOkTest() throws Exception {
        Training t = new Training();
        t.setId(10);
        String jsonTraining = Util.convertEntityToJson(t);
        Mockito.doNothing().when(trainingService).saveTraining(t);
        mockMvc.perform(post(ServiceAPI.Training.POST_SAVE_TRAINING)
                .contentType(contentType)
                .content(jsonTraining))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_SAVED)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Training.POST_SAVE_TRAINING)))
                .andExpect(jsonPath("$.payload", Is.is("Training "+t.getId()+" saved.")));
    }
    
    @Test
    public void saveTrainingExceptionTest() throws Exception {
        Training t = new Training();
        t.setId(10);
        String jsonTraining = Util.convertEntityToJson(t);
        Mockito.doThrow(new ServiceException("Training not saved.")).when(trainingService).saveTraining(t);
        mockMvc.perform(post(ServiceAPI.Training.POST_SAVE_TRAINING)
                .contentType(contentType)
                .content(jsonTraining))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Training not saved.")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Training.POST_SAVE_TRAINING)));
    }
    
    @Test
    public void getTrainingByIdOkTest() throws Exception {
        Training t = new Training();
        t.setId(10);
        t.setDescription("Training description");
        Mockito.when(trainingService.getTrainingById(10)).thenReturn(t);
        mockMvc.perform(get("/trainings/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Training.GET_TRAINING_BY_ID)))
                .andExpect(jsonPath("$.payload.id", Is.is((int)t.getId())))
                .andExpect(jsonPath("$.payload.description", Is.is(t.getDescription())));
    }
    
    @Test
    public void getTrainingByIdNotFoundTest() throws Exception {
        Training t = new Training();
        t.setId(100);
        t.setDescription("Training description");
        Mockito.when(trainingService.getTrainingById(10)).thenReturn(null);
        mockMvc.perform(get("/trainings/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Training.GET_TRAINING_BY_ID)))
                .andExpect(jsonPath("$.payload", Is.is((IsNull.nullValue()))));
    }
    
    @Test
    public void getTrainingByIdExceptionTest() throws Exception {
        Training t = new Training();
        t.setId(10);
        t.setDescription("Training description");
        Mockito.doThrow(new ServiceException("Get training by id error.")).when(trainingService).getTrainingById(10);
        mockMvc.perform(get("/trainings/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("Get training by id error.")))
                .andExpect(jsonPath("$.requestUri", Is.is(("/trainings/10"))));
    }
    
    @Test
    public void getTrainingsOkTest() throws Exception {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("group", "1");
        List<Training> trainings = new MockTrainingRepository().getEntities(searchCriteria);
        Mockito.when(trainingService.getTrainings(searchCriteria)).thenReturn(trainings);
        mockMvc.perform(post(ServiceAPI.Training.POST_SEARCH_TRAINING)
                .contentType(contentType)
                .content(Util.convertEntityToJson(searchCriteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.OK.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Training.POST_SEARCH_TRAINING)))
                .andExpect(jsonPath("$.payload[0].group.id", Is.is(1)))
                .andExpect(jsonPath("$.payload[1].group.id", Is.is(1)));
    }
    
    @Test
    public void getTrainingsNotFoundTest() throws Exception {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("group", "100");
        Mockito.when(trainingService.getTrainings(searchCriteria)).thenReturn(new ArrayList<>());
        mockMvc.perform(post(ServiceAPI.Training.POST_SEARCH_TRAINING)
                .contentType(contentType)
                .content(Util.convertEntityToJson(searchCriteria)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.message", Is.is(ServiceAPI.DefaultResponseMessages.RESOURCE_NOT_FOUND)))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Training.POST_SEARCH_TRAINING)))
                .andExpect(jsonPath("$.payload", Matchers.hasSize(0)));
    }
    
    @Test
    public void getTrainingsExceptionTest() throws Exception {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put("group", "100");
        Mockito.when(trainingService.getTrainings(searchCriteria)).thenThrow(new ServiceException("TrainingsException"));
        mockMvc.perform(post(ServiceAPI.Training.POST_SEARCH_TRAINING)
                .contentType(contentType)
                .content(Util.convertEntityToJson(searchCriteria)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Is.is(HttpStatus.NOT_FOUND.toString())))
                .andExpect(jsonPath("$.errorType", Is.is("rs.fon.eklub.core.exceptions.ServiceException")))
                .andExpect(jsonPath("$.errorMessage", Is.is("TrainingsException")))
                .andExpect(jsonPath("$.requestUri", Is.is(ServiceAPI.Training.POST_SEARCH_TRAINING)));
    }
}
