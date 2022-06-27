package com.wiley.c242.connorhs.View;

import com.wiley.c242.connorhs.Model.DTO.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView
{
    private UserIO io;

    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    public void printMessage(String message)
    {
        io.printMessage(message);
    }

    public void displayItems(List<Item> items)
    {
        printMessage("Item\t\tPrice\t\tQuantity in stock");
        printMessage("______________________________________________________");
        for (Item i : items)
        {
            printMessage(i.toString());
        }
        printMessage("");
    }

    public String getCommand()
    {
        printMessage("Would you like to LIST the inventory, PURCHASE an item, INSERT funds or EXIT the program? : ");
        return io.getMessage();
    }

    public BigDecimal getDecimal()
    {
        for (;;)
        {
            boolean success = true;

            printMessage("How much money will you insert? : ");
            String value = io.getMessage();
            BigDecimal decimalValue = new BigDecimal("0.00");

            try { decimalValue = new BigDecimal(value); }
            catch(NumberFormatException e)
            {
                printMessage("Please use the correct decimal format...");
                success = false;
            }

            if (success)
                return decimalValue;
        }
    }
}
