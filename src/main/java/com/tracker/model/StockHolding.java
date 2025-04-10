package com.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockHolding {
    private String stockCode;
    private String stockName;
    private int quantity;
    private double averagePrice;
    private double currentPrice;

    public double getProfitLoss() {
        return (currentPrice - averagePrice) * quantity;
    }
}