package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TradeRepository tradeRepository;
    @Test
    @WithMockUser(username = "test", password = "test",roles = "user")
    void getTradeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/list"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void addTradeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate").with(csrf())
                        .param("account", "accountTest")
                        .param("type", "typeTest")
                        .param("buyQuantity", "20"))
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void updateTradeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/{id}", 1).with(csrf())
                        .param("account", "accountTest")
                        .param("type", "typeTest")
                        .param("buyQuantity", "15"))
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void deleteTradeTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("accountDelete");
        trade.setType("typeDelete");
        trade.setBuyQuantity(40.0);
        tradeRepository.save(trade);
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/delete/{id}", trade.getTradeId()).with(csrf()))
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().isFound());
    }
}