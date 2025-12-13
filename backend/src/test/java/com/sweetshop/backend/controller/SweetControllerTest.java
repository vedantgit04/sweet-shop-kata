package com.sweetshop.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetshop.backend.model.Sweet;
import com.sweetshop.backend.service.SweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // Use @MockBean if @MockitoBean is not found
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // (Likely already there)
@WebMvcTest(SweetController.class)
class SweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Change to @MockBean if using Spring Boot < 3.4
    private SweetService sweetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser // Bypasses security for this test
    void shouldCreateNewSweet() throws Exception {
        Sweet sweet = new Sweet("Jalebi", "Fried", 5.0, 100);

        // Mock the service to return the sweet
        when(sweetService.addSweet(any(Sweet.class))).thenReturn(sweet);

        mockMvc.perform(post("/api/sweets")
                        .with(csrf()) // Required for POST requests when Security is active
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sweet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jalebi"));
    }

    @Test
    @WithMockUser
    void shouldGetAllSweets() throws Exception {
        Sweet s1 = new Sweet("Jalebi", "Fried", 5.0, 100);
        Sweet s2 = new Sweet("Barfi", "Milk", 8.0, 50);

        when(sweetService.getAllSweets()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/api/sweets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    @WithMockUser
    void shouldSearchSweets() throws Exception {
        Sweet s1 = new Sweet("Kaju Katli", "Premium", 20.0, 30);
        when(sweetService.searchSweets("Kaju", null, null)).thenReturn(Arrays.asList(s1));

        mockMvc.perform(get("/api/sweets/search?keyword=Kaju"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @WithMockUser
    void shouldUpdateSweet() throws Exception {
        Sweet updatedSweet = new Sweet("New Name", "New Cat", 15.0, 20);
        when(sweetService.updateSweet(any(Long.class), any(Sweet.class))).thenReturn(updatedSweet);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/sweets/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSweet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    @Test
    @WithMockUser
    void shouldDeleteSweet() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/sweets/1")
                        .with(csrf()))
                .andExpect(status().isNoContent()); // Expects 204 No Content
    }

    @Test
    @WithMockUser
    void shouldPurchaseSweet() throws Exception {
        Sweet sweet = new Sweet("Ladoo", "Trad", 10.0, 90);
        when(sweetService.purchaseSweet(1L, 10)).thenReturn(sweet);

        mockMvc.perform(post("/api/sweets/1/purchase")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("10")) // Sending integer 10 as body
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(90));
    }

    @Test
    @WithMockUser
    void shouldRestockSweet() throws Exception {
        Sweet sweet = new Sweet("Ladoo", "Trad", 10.0, 60);
        when(sweetService.restockSweet(1L, 10)).thenReturn(sweet);

        mockMvc.perform(post("/api/sweets/1/restock")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(60));
    }
}