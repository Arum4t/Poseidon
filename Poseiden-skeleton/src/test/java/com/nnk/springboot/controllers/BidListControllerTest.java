package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    @WithMockUser(username = "test", password = "test",roles = "user")
    void getHomeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void addBidTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate").with(csrf())
                        .param("account", "testAccount")
                        .param("type", "testType")
                        .param("bidQuantity", "50"))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound());
    }
    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void updateBidTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/{id}", 702).with(csrf())
                        .param("account", "testAccount")
                        .param("type", "testType")
                        .param("bidQuantity", "1"))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void deleteBid() throws Exception {
        BidList bid = new BidList();
        bid.setAccount("accountDeleteTest");
        bid.setType("typeDeleteTest");
        bid.setBidQuantity(20.0);
        bidListRepository.save(bid);

        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete/{id}", bid.getBidListId()).with(csrf()))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound());
    }
}