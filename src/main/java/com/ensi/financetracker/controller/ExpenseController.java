package com.ensi.financetracker.controller;
import com.ensi.financetracker.dto.ExpenseRequest;
import com.ensi.financetracker.dto.ExpenseResponse;
import com.ensi.financetracker.entity.Expense;
import com.ensi.financetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse createExpense(@Valid @RequestBody ExpenseRequest request) {
        return expenseService.createExpense(request);
    }

    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(@PathVariable Long id,@Valid @RequestBody ExpenseRequest request){
        return expenseService.updateExpense(id, request);
    }

//    @GetMapping
//    public List<ExpenseResponse> getAllExpenses() {
//        return expenseService.getAllExpenses();
//    }

    @GetMapping("/{id}")
    public ExpenseResponse getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @GetMapping("/category/{category}")
    public List<ExpenseResponse> getByCategory(@PathVariable String category) {
        return expenseService.getByCategory(category);
    }

    @GetMapping("/amount")
    public Page<ExpenseResponse> getAmountGreaterThan(
            @RequestParam BigDecimal amount,
            @RequestParam int page,
            @RequestParam int size) {

        return expenseService.getAmountGreaterThan(amount, page, size);
    }

    @GetMapping
    public Page<ExpenseResponse> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return expenseService.getAllExpenses(page, size);
    }
    }
