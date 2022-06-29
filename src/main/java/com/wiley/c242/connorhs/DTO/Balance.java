package com.wiley.c242.connorhs.DTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Balance
{
    private BigDecimal balance = new BigDecimal("0.00");

    public void addFunds(BigDecimal value)
    {
        balance = balance.add(value);
    }

    public void removeFunds(BigDecimal value)
    {
        balance = balance.subtract(value);
    }

    public BigDecimal getValue()
    {
        return balance;
    }

    public void setValue(BigDecimal value)
    {
        balance = value;
    }
}
