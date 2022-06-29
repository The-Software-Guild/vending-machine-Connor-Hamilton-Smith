package com.wiley.c242.connorhs.UI;

import com.wiley.c242.connorhs.DTO.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

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

    public void displayItems(Collection<Item> items)
    {
        printMessage("ID\t\tItem\t\tPrice\t\tQuantity in stock");
        printMessage("___________________________________________________________");
        for (Item i : items)
        {
            printMessage(i.toDisplayString());
        }
        printMessage("");
    }

    public String getCommand()
    {
        printMessage("\nWould you like to LIST the inventory, PURCHASE an item, INSERT funds, check BALANCE or EXIT the program? : ");
        return io.getMessage().toUpperCase();
    }

    public BigDecimal getDecimal()
    {
        for (;;)
        {
            boolean success = true;

            printMessage("How much money will you insert? : ");
            String value = io.getMessage();

            BigDecimal decimalValue = new BigDecimal("0.00");
            try {
                decimalValue = new BigDecimal(value);
                decimalValue = decimalValue.setScale(2, RoundingMode.HALF_UP);
            } catch(NumberFormatException e) {
                printMessage("Please use the correct decimal format...");
                success = false;
            }

            if (success)
                return decimalValue;
        }
    }

    public String getPurchaseID()
    {
        io.printMessage("Please enter the ID of the item you would like to purchase : ");
        return io.getMessage().toUpperCase();
    }
}
