package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    @WithMockUser(username = "test", password = "test",roles = "user")
    void readCurvePointTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list"))
                .andExpect(status().isOk());
    }
    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void addCurvePointTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate").with(csrf())
                        .param("curveId", "10")
                        .param("term", "15")
                        .param("value", "30"))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void updateCurvePointTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/{id}", 1).with(csrf())
                        .param("curveId", "10")
                        .param("term", "15")
                        .param("value", "15"))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void deleteCurvePointTest() throws Exception {
        CurvePoint curve = new CurvePoint();
        curve.setCurveId(5);
        curve.setTerm(5.0);
        curve.setValue(5.0);
        curvePointRepository.save(curve);
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete/{id}", curve.getId()).with(csrf()))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound());
    }
}