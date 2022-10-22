package com.app.login.controller;

import com.app.login.dto.AppDTO;
import com.app.login.service.AppService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AppControllerTest {
    private MockMvc mockMvc;

    private static final String BASE_PATH="/api";

    ObjectMapper mp=new ObjectMapper();

    @Mock
    private AppService appService;

    @InjectMocks
    private AppController appController;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc= MockMvcBuilders.standaloneSetup(appController).build();
    }

    @Test
    void createOrUpdatelogin() throws Exception{
        AppDTO expected=AppDTO.builder().build();
        when(appService.createOrUpdatelogin(expected)).thenReturn(expected);
        mockMvc.perform( MockMvcRequestBuilders
                .post(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(mp.writeValueAsString(expected)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isNotEmpty());


    }
}