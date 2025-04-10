package com.tracker.controller;

import com.tracker.model.Transaction;
import com.tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TransactionService transactionService;

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "10") int size, Model model) {
        model.addAttribute("totalInvestment", "$10,000");
        model.addAttribute("currentValue", "$12,500");
        model.addAttribute("profitLoss", "$2,500");

        Page<Transaction> transactionPage = transactionService.getTransactions(0, size);
        model.addAttribute("transactions", transactionPage.getContent());
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", transactionPage.getTotalPages());
        model.addAttribute("size", size);

        return "index";
    }

    @GetMapping("/home/transactions")
    public String getTransactions(@RequestParam int page,
                                  @RequestParam int size,
                                  Model model) {
        Page<Transaction> transactionPage = transactionService.getTransactions(page, size);
        model.addAttribute("transactions", transactionPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", transactionPage.getTotalPages());
        model.addAttribute("size", size);

        return "fragments/transaction-history :: transactionHistory";
    }

    @GetMapping("/home/pagination")
    public String getPagination(@RequestParam int page,
                                @RequestParam int size,
                                Model model) {
        Page<Transaction> transactionPage = transactionService.getTransactions(page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", transactionPage.getTotalPages());
        model.addAttribute("size", size);

        return "fragments/pagination :: paging";
    }
}