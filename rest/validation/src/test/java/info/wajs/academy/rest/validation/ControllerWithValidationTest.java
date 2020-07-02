package info.wajs.academy.rest.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerWithValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void doPost() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/do-with-answer")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test-name\", \"value\":\"3\"}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser
    void throw400WhenValueLessThan2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/do-with-answer")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test-name\", \"value\":\"1\"}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}