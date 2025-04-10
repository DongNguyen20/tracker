package com.tracker.controller;

import com.tracker.model.Transaction;
import com.tracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        // Giả lập dữ liệu thống kê lời/lỗ
        model.addAttribute("totalInvestment", "$10,000");
        model.addAttribute("currentValue", "$12,500");
        model.addAttribute("profitLoss", "$2,500");

        // Lấy danh sách giao dịch từ Service
        Page<Transaction> transactionPage = transactionService.getTransactions(page, size);
        model.addAttribute("transactions", transactionPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", transactionPage.getTotalPages());
        model.addAttribute("size", size);
        return "index"; // Tên file Thymeleaf template
    }
}