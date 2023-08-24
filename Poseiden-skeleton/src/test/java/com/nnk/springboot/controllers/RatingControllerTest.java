package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void getRatingViewTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/list"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void addRatingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate").with(csrf())
                        .param("moodysRating", "10")
                        .param("sandPRating", "10")
                        .param("fitchRating", "15")
                        .param("orderNumber","20"))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void updateRatingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/{id}", 302).with(csrf())
                        .param("moodysRating", "50")
                        .param("sandPRating", "10")
                        .param("fitchRating", "15")
                        .param("orderNumber","20"))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void deleteRatingTest() throws Exception {
    Rating rating = new Rating();
    rating.setMoodysRating("3");
    rating.setSandPRating("3");
    rating.setOrderNumber(3);
    rating.setFitchRating("3");
    ratingRepository.save(rating);
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/delete/{id}", rating.getId()).with(csrf()))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound());
    }
}