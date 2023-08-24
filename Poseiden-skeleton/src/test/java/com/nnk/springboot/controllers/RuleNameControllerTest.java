package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
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
class RuleNameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    @WithMockUser(username = "test", password = "test",roles = "user")
    void getRuleViewTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void addRuleTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate").with(csrf())
                        .param("name", "nametest")
                        .param("description", "descriptiontest")
                        .param("json", "jsontest")
                        .param("template","templatetest")
                        .param("sqlStr","sqlstrtest")
                        .param("sqlPart","sqlparttest"))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void updateRuleTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/{id}", 2).with(csrf())
                        .param("name", "nametestchange")
                        .param("description", "descriptiontest")
                        .param("json", "jsontest")
                        .param("template","templatetest")
                        .param("sqlStr","sqlstrtest")
                        .param("sqlPart","sqlparttest"))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound());
    }

    @Test
    @Transactional
    @WithMockUser(username = "test", password = "test",roles = "user")
    void deleteRuleTest() throws Exception {
    RuleName rule = new RuleName();
    rule.setName("testDelete");
    rule.setDescription("testDelete");
    rule.setJson("testDelete");
    rule.setTemplate("testDelete");
    rule.setSqlPart("testDelete");
    rule.setSqlStr("testDelete");
    ruleNameRepository.save(rule);
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/delete/{id}", rule.getId()).with(csrf()))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound());
    }
}