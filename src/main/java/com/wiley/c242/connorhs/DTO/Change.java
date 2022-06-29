package com.wiley.c242.connorhs.DTO;
import java.math.BigDecimal;

public enum Change
{
    OnePound,
    FiftyPence,
    TwentyPence,
    TenPence,
    FivePence,
    TwoPence,
    OnePence;

    public static BigDecimal getValue(Change type)
    {
        BigDecimal value;
        switch (type)
        {
            case OnePence:
                value = new BigDecimal("0.01");
                break;
            case TwoPence:
                value = new BigDecimal("0.02");
                break;
            case FivePence:
                value = new BigDecimal("0.05");
                break;
            case TenPence:
                value = new BigDecimal("0.10");
                break;
            case TwentyPence:
                value = new BigDecimal("0.20");
                break;
            case FiftyPence:
                value = new BigDecimal("0.50");
                break;
            case OnePound:
                value = new BigDecimal("1.00");
                break;
            default:
                value = new BigDecimal("0.00");
                break;
        }
        return value;
    }
}
