package com.tracker.controller;

import com.tracker.model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // Giả lập dữ liệu thống kê lời/lỗ
        model.addAttribute("totalInvestment", "$10,000");
        model.addAttribute("currentValue", "$12,500");
        model.addAttribute("profitLoss", "$2,500");

        // Giả lập danh sách lịch sử giao dịch
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2025-04-01", "AAPL", "Buy", 10, "$150", "$1500"),
                new Transaction("2025-04-02", "GOOGL", "Sell", 5, "$2800", "$14000"),
                new Transaction("2025-04-02", "GOOGL", "Sell", 5, "$2800", "$14000"),
                new Transaction("2025-04-02", "GOOGL", "Sell", 5, "$2800", "$14000"),
                new Transaction("2025-04-02", "GOOGL", "Sell", 5, "$2800", "$14000"),

                new Transaction("2025-04-03", "TSLA", "Buy", 7, "$700", "$4900")
        );
        model.addAttribute("transactions", transactions);

        // Phân trang giả lập
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1);

        return "index"; // Tên file Thymeleaf template
    }
}