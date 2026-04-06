package com.zorvyn.finance.controller;

import com.zorvyn.finance.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/zorvyn/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final FinancialService service;

    @GetMapping("/summary")
    public Map<String, Double> summary() {

        double income = service.getIncome();
        double expense = service.getExpense();

        return Map.of(
                "income", income,
                "expense", expense,
                "balance", income - expense
        );
    }

    @GetMapping("/categories")
    public Map<String, Double> categories() {
        return service.categorySummary();
    }
}
