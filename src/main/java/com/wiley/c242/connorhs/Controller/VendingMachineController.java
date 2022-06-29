package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.DTO.Change;
import com.wiley.c242.connorhs.DTO.DaoException;
import com.wiley.c242.connorhs.DTO.FileIOException;
import com.wiley.c242.connorhs.DTO.Item;
import com.wiley.c242.connorhs.UI.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController
{
    private VendingMachineServiceLayer serviceLayer;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer serviceLayer, VendingMachineView view)
    {
        this.serviceLayer = serviceLayer;
        this.view = view;
    }

    public void Run()
    {
        for (;;)
        {
            String command = view.getCommand();

            switch (command)
            {
                case "LIST":
                    view.displayItems(serviceLayer.getInventory());
                    break;
                case "BALANCE":
                    showBalance();
                    break;
                case "CHANGE":
                    giveChange();
                    break;
                case "PURCHASE":
                    purchaseItem();
                    break;
                case "INSERT":
                    insertFunds();
                    break;
                case "EXIT":
                    giveChange();
                    saveInventory();
                    return;
                default:
                    view.printMessage("Command not recognised, please try again... \nAccepted commands are LIST, PURCHASE, INSERT, BALANCE, EXIT\n");
                    break;
            }
        }
    }

    private void purchaseItem()
    {
        List<Item> purchasableItems = serviceLayer.getPurchasableInventory();
        if (purchasableItems.size() > 0) {
            view.printMessage("The following items can be purchased with £" + serviceLayer.getBalance());
            view.displayItems(purchasableItems);
        }
        else
            view.printMessage("There are no items that can be purchased for the current balance.");

        String id = view.getPurchaseID();
        boolean purchaseSuccessful = false;
        try {
            purchaseSuccessful = serviceLayer.purchaseItem(id);
        }
        catch(DaoException e) {
            view.printMessage(e.getMessage());
            purchaseSuccessful = false;
        }

        if (purchaseSuccessful)
            view. printMessage("Kerchunk!\n" + serviceLayer.getItem(id).getName() + " dispensed");
        else
            view.printMessage("Purchase failed");
    }

    private void insertFunds()
    {
        BigDecimal value = view.getDecimal();
        serviceLayer.addBalance(value);
        showBalance();
    }

    private void showBalance()
    {
        view.printMessage("Balance : £" + serviceLayer.getBalance());
    }

    private void giveChange()
    {
        List<Change> allChange = serviceLayer.returnChange();

        if (allChange.size() <= 0)
            return;

        // Count and display the number of coins of each type in the list of allChange
        view.printMessage("You receive the following change...");
        Change[] changeTypes = Change.values();
        for (Change type : changeTypes)
        {
            long changeOfType = allChange.stream().filter((c) -> c == type).count();
            if (changeOfType > 0)
                view.printMessage(type + " : " + changeOfType);
        }

    }

    private void saveInventory()
    {
        try {
            serviceLayer.saveInventory();
        } catch(FileIOException e) {
            e.getMessage();
        }

        view.printMessage("Exiting program...");
    }
}
