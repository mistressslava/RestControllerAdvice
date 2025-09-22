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
class AnimalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAnimalSpeciesShouldReturnStringDog() throws Exception {
        mockMvc.perform(get("/api/animals/dog"))
                .andExpect(status().isOk())
                .andExpect(content().string("dog"));
    }

    @Test
    void getAnimalSpeciesShouldReturnStringNotAllowed() throws Exception {
        mockMvc.perform(get("/api/animals/cow"))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string(containsString("Error: Only 'dog' is allowed")))
                .andExpect(content().string(containsString("Reason: Not Acceptable")))
                .andExpect(content().string(containsString("Statuscode: 406")))
                .andExpect(content().string(containsString("Path: /api/animals/cow")));
    }

    @Test
    void getAllAnimalsShouldReturnStringNotFound() throws Exception {
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No Animals found")));
    }

}