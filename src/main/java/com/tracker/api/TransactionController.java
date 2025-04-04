package com.tracker.api;

import com.tracker.model.Transaction;
import com.tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public Page<Transaction> getTransactions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return transactionService.getTransactions(page, size);
    }
}
