package com.ensi.financetracker.service;

import com.ensi.financetracker.dto.ExpenseRequest;
import com.ensi.financetracker.dto.ExpenseResponse;
import com.ensi.financetracker.entity.Expense;
import com.ensi.financetracker.exception.ResourceNotFoundException;
import com.ensi.financetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;
    @Test
    void createExpenseTest(){
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setTitle("Title1");
        expenseRequest.setAmount(new BigDecimal(100));
        expenseRequest.setCategory("food");
        expenseRequest.setExpenseDate(LocalDate.of(2012,2,17));
        expenseRequest.setNote("");
//Fake Rep Response
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setTitle("Title1");
        expense.setAmount(new BigDecimal("100"));
        expense.setCategory("food");
        expense.setExpenseDate(LocalDate.of(2012, 2, 17));
        expense.setNote("");

        when(expenseRepository.save(any(Expense.class)))
                .thenReturn(expense);

        ExpenseResponse expenseResponse = expenseService.createExpense(expenseRequest);

        assertNotNull(expenseResponse);
        assertEquals("Title1", expenseResponse.getTitle());
        assertEquals(new BigDecimal("100"), expenseResponse.getAmount());
        assertEquals("food", expenseResponse.getCategory());
    }
    @Test
    void updateExpenseTest(){
        Long id = 2L;
        Expense expense = new Expense();

        expense.setId(id);
        expense.setTitle("Title1");
        expense.setAmount(new BigDecimal("12.5"));
        expense.setCategory("Food");
        expense.setExpenseDate(LocalDate.of(2026, 4, 20));
        expense.setNote("Updated");
        when(expenseRepository.findById(id)).thenReturn(Optional.of(expense));

        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setTitle("Title4");
        expenseRequest.setAmount(new BigDecimal("200"));
        expenseRequest.setCategory("Cloths");
        expenseRequest.setExpenseDate(LocalDate.of(2017,6,23));
        expenseRequest.setNote("This is for update");

        Expense updateExpense = new Expense();
        updateExpense.setId(id);
        updateExpense.setTitle("Title4");
        updateExpense.setAmount(new BigDecimal("200"));
        updateExpense.setCategory("Cloths");
        updateExpense.setExpenseDate(LocalDate.of(2017,6,23));
        updateExpense.setNote("This is for update");

        when(expenseRepository.save(any(Expense.class)))
                .thenReturn(updateExpense);

        ExpenseResponse expenseResponse = expenseService.updateExpense(id, expenseRequest);

        assertNotNull(expenseResponse);
        assertEquals("Title4", expenseResponse.getTitle());
        assertEquals(new BigDecimal("200"), expenseResponse.getAmount());
        assertEquals("Cloths", expenseResponse.getCategory());

        verify(expenseRepository, times(1)).save(any(Expense.class));
    }
    @Test
    void expenseByIdTest_success(){
        Long id=2L;

        Expense expense = new Expense();

        expense.setId(id);
        expense.setTitle("Title1");
        expense.setAmount(new BigDecimal("12.5"));
        expense.setCategory("Food");
        expense.setExpenseDate(LocalDate.of(2026, 4, 20));
        expense.setNote("Updated");
        when(expenseRepository.findById(id)).thenReturn(Optional.of(expense));
        ExpenseResponse expenseResponse = expenseService.getExpenseById(id);
        assertNotNull(expenseResponse);
        assertEquals(id, expenseResponse.getId());
        assertEquals("Title1", expenseResponse.getTitle());
        assertEquals(new BigDecimal("12.5"), expenseResponse.getAmount());
        assertEquals("Food", expenseResponse.getCategory());

        verify(expenseRepository, times(1)).findById(id);
    }

    @Test
    void expenseByIdTest_notFound(){
        Long id=200L;

        when(expenseRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            expenseService.getExpenseById(id);
        });
        verify(expenseRepository, times(1)).findById(id);
    }
    @Test
    void expenseBDeleteTest_success(){
        Long id=2L;
        when(expenseRepository.existsById(id)).thenReturn(true);
        expenseService.deleteExpense(id);

        verify(expenseRepository, times(1)).deleteById(id);
    }

    @Test
    void expenseBDeleteTest_fail(){
        Long id=200L;
        when(expenseRepository.existsById(id)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> {
            expenseService.deleteExpense(id);
        });

        verify(expenseRepository, never()).deleteById(id);
    }

}
