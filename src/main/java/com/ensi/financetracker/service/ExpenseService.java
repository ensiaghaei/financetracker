package com.ensi.financetracker.service;
import com.ensi.financetracker.dto.ExpenseRequest;
import com.ensi.financetracker.dto.ExpenseResponse;
import com.ensi.financetracker.entity.Expense;
import com.ensi.financetracker.exception.ResourceNotFoundException;
import com.ensi.financetracker.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponse createExpense(ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setNote(request.getNote());
        Expense saveExpense = expenseRepository.save(expense);
        return mapToResponse(saveExpense);
    }

public ExpenseResponse updateExpense(Long id, ExpenseRequest request){
    Expense expense = expenseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    expense.setTitle(request.getTitle());
    expense.setAmount(request.getAmount());
    expense.setCategory(request.getCategory());
    expense.setExpenseDate(request.getExpenseDate());
    expense.setNote(request.getNote());
    Expense updateExpense = expenseRepository.save(expense);
    return mapToResponse(updateExpense);
}
    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public ExpenseResponse getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
        return mapToResponse(expense);
    }

    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }

    public List<ExpenseResponse> getByCategory(String category) {
        return expenseRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public Page<ExpenseResponse> getAmountGreaterThan(BigDecimal amount, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return expenseRepository
                .findByAmountGreaterThan(amount, pageable)
                .map(this::mapToResponse);
    }

    public Page<ExpenseResponse> getAllExpenses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return expenseRepository.findAll(pageable)
                .map(this::mapToResponse);
    }
        private ExpenseResponse mapToResponse(Expense expense) {
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getId());
        response.setTitle(expense.getTitle());
        response.setAmount(expense.getAmount());
        response.setCategory(expense.getCategory());
        response.setExpenseDate(expense.getExpenseDate());
        response.setNote(expense.getNote());
        return response;
    }

}
