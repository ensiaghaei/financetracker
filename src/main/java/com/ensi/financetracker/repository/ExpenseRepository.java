package com.ensi.financetracker.repository;

import com.ensi.financetracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategoryIgnoreCase(String category);
    Page<Expense> findByAmountGreaterThan(BigDecimal amount, Pageable pageable);

}
