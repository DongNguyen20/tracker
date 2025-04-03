package com.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String date;
    private String stock;
    private String type;
    private int quantity;
    private String price;
    private String total;
}