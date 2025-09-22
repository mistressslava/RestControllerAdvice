package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCarBrandShouldReturnStringPorsche() throws Exception {
        mockMvc.perform(get("/api/cars/porsche"))
                .andExpect(status().isOk())
                .andExpect(content().string("porsche"));
    }

    @Test
    void getCarBrandShouldReturnStringNotAllowed() throws Exception {
        mockMvc.perform(get("/api/cars/bmw"))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string(containsString("Only 'porsche' allowed")))
                .andExpect(content().string(containsString("Reason: Not Acceptable")))
                .andExpect(content().string(containsString("Statuscode: 406")))
                .andExpect(content().string(containsString("Path: /api/cars/bmw")));
    }

    @Test
    void getAllCars() throws Exception {
        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No Cars found")));
    }
}