package com.zorvyn.finance.repository;

import com.zorvyn.finance.entity.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = :type")
    Double sumByType(RecordType type);

    @Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f GROUP BY f.category")
    List<Object[]> categorySummary();
}
