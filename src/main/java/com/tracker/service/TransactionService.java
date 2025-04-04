package com.tracker.service;

import com.tracker.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

//    @Autowired
//    private TransactionRepository transactionRepository;

    public Page<Transaction> getTransactions(int page, int size) {
        List<Transaction> list =  Arrays.asList(
                new Transaction("2025-04-01", "AAPL", "Buy", 10, "$150", "$1500"),
                new Transaction("2025-04-02", "GOOGL", "Sell", 5, "$2800", "$14000"),
                new Transaction("2025-04-02", "GOOGL", "Sell", 5, "$2800", "$14000"),
                new Transaction("2025-04-02", "GOOGL", "Sell", 5, "$2800", "$14000"),

                new Transaction("2025-04-03", "TSLA", "Buy", 7, "$700", "$4900")
        );

        int start = page * size;
        int end = Math.min(start + size, list.size());

        List<Transaction> paginatedList = list.subList(start, end);
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(paginatedList, pageable, list.size());
    }
}
