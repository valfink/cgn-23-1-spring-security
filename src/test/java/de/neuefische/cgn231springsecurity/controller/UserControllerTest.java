package de.neuefische.cgn231springsecurity.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DirtiesContext
    @WithMockUser(username = "user", password = "123")
    void getMe_whenAuthenticated_thenUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/me_context"))
                .andExpect(status().isOk())
                .andExpect(content().string("user"));
    }

    @Test
    @DirtiesContext
    @WithMockUser(username = "user", password = "123")
    void getMe_whenRegisterd_then200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "user",
                                "password": "123"
                                }
                                """)
                        .with(csrf()))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/me"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    @WithMockUser(username = "user", password = "123")
    void postUser_whenNoToken_then403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "user",
                                "password": "123"
                                }
                                """))
                .andExpect(status().isForbidden());
    }
}