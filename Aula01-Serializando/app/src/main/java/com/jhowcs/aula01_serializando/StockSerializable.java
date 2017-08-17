package com.jhowcs.aula01_serializando;

import java.io.Serializable;


public class StockSerializable implements Serializable {
    private String name;
    private double entryPrice;

    public StockSerializable(String name, double entryPrice) {
        this.name = name;
        this.entryPrice = entryPrice;
    }
}
