package com.tracker.controller;

import com.tracker.model.StockHolding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PortfolioController {

    @GetMapping("/portfolio")
    public String viewPortfolio(Model model) {
        List<StockHolding> holdings = new ArrayList<>();

        holdings.add(new StockHolding("FPT", "FPT Corporation", 100, 85000, 92000));
        holdings.add(new StockHolding("VNM", "Vinamilk", 50, 75000, 70000));
        holdings.add(new StockHolding("TCB", "Techcombank", 200, 31000, 33000));

        model.addAttribute("holdings", holdings);
        return "portfolio";
    }
}
