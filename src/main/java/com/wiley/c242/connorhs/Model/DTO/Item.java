package com.wiley.c242.connorhs.Model.DTO;

import java.math.BigDecimal;

public class Item
{
    private String name;
    private BigDecimal price;
    private int quantityInStock;

    public Item(String name, BigDecimal price, int stock)
    {
        this.name = name;
        this.price = price;
        this.quantityInStock = stock;
    }

    @Override
    public String toString()
    {
        if (name.length() <= 7)
            return name + "\t\t£" + price.toString() + "\t\t" + String.valueOf(quantityInStock);
        else
            return name + "\t£" + price.toString() + "\t\t" + String.valueOf(quantityInStock);
    }

    public String toStringData()
    {
        return name + "::" + price.toString() + "::" + String.valueOf(quantityInStock);
    }
}
