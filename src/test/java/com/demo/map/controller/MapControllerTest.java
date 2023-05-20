package com.demo.map.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.map.service.MapService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(MapController.class)
public class MapControllerTest {

    @InjectMocks
    private MapController mapController;

    @Mock
    private MapService service;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mapController).build();
    }

    @Test
    public void testGetSmallestPathWithCorrectInput() throws Exception {
        String pointA = "pointA";
        String pointB = "pointB";
        String output = pointA + pointB;

        String url = "/smallestPath/" + pointA + "/" + pointB;
        Mockito.when(service.getSmallestPath(pointA, pointB))
                .thenReturn(output);

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(output));
    }

}
