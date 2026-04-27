package com.ensi.financetracker.controller;

import com.ensi.financetracker.entity.Expense;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ExpenseControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateExpense() throws Exception{
        Expense expense = new Expense();
        expense.setTitle("Coffee");
        expense.setCategory("Food");
        expense.setExpenseDate(LocalDate.of(2021,5,27));
        expense.setAmount(new BigDecimal("10.0"));
        mockMvc.perform(post("/api/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isCreated());

    }
    @Test
    void shouldReturnBadRequestCreateExpense() throws Exception{
        Expense expense = new Expense();
        expense.setTitle("Coffee");
        expense.setExpenseDate(LocalDate.of(2021,5,27));
        expense.setAmount(new BigDecimal("10.0"));
        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.category").exists())
        ;

    }
    @Test
    void shouldReturnAllExpenses() throws Exception{
        mockMvc.perform(get("/api/expenses"))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnExpenseById() throws Exception{
        String response = mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Tea\",\"amount\":5 , \"category\": \"Food\", \"expenseDate\": \"2023-07-09\"}"))
                .andReturn().getResponse().getContentAsString();

        Expense saved = objectMapper.readValue(response, Expense.class);

        mockMvc.perform(get("/api/expenses/" + saved.getId()))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/expenses/999"))
                .andExpect(status().isNotFound());
    }
    @Test
    void shouldDeleteExpense() throws Exception {
        String response = mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Tea\",\"amount\":5 , \"category\": \"Food\", \"expenseDate\": \"2023-07-09\"}"))
                .andReturn().getResponse().getContentAsString();

        Expense saved = objectMapper.readValue(response, Expense.class);

        mockMvc.perform(delete("/api/expenses/" + saved.getId()))
                .andExpect(status().isNoContent());
    }
}
