package com.zorvyn.finance.service;

import com.zorvyn.finance.entity.*;
import com.zorvyn.finance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FinancialService {

    private final FinancialRecordRepository repo;

    public FinancialRecord create(FinancialRecord record) {
        return repo.save(record);
    }

    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public double getIncome() {
        return Optional.ofNullable(repo.sumByType(RecordType.INCOME)).orElse(0.0);
    }

    public double getExpense() {
        return Optional.ofNullable(repo.sumByType(RecordType.EXPENSE)).orElse(0.0);
    }

    public Map<String, Double> categorySummary() {
        Map<String, Double> map = new HashMap<>();
        for (Object[] row : repo.categorySummary()) {
            map.put((String) row[0], (Double) row[1]);
        }
        return map;
    }
}
