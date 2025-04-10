package com.tracker.controller;

import com.tracker.model.Transaction;
import com.tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TransactionService transactionService;

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "10") int size, Model model) {
        model.addAttribute("totalInvestment", "$10,000");
        model.addAttribute("currentValue", "$12,500");
        model.addAttribute("profitLoss", "$2,500");

        model.addAttribute("transactions",loadTransactionData(0, size, model));

        return "index";
    }

    @GetMapping("/home/transactions")
    public String getTransactions(@RequestParam int page,
                                  @RequestParam int size,
                                  Model model) {
        model.addAttribute("transactions", loadTransactionData(0, size, model));
        return "fragments/transaction-history :: transactionHistory";
    }

    private List<Transaction> loadTransactionData(int page, int size, Model model) {
        Page<Transaction> transactionPage = transactionService.getTransactions(page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", transactionPage.getTotalPages());
        model.addAttribute("size", size);
        return transactionPage.getContent();
    }

    @GetMapping("/home/pagination")
    public String getPagination(@RequestParam int page,
                                @RequestParam int size,
                                Model model) {
        loadTransactionData(0, size, model);
        model.addAttribute("baseUrl", "/home");

        return "fragments/pagination :: paging";
    }
}