package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.DTO.Change;
import com.wiley.c242.connorhs.DAO.DaoException;
import com.wiley.c242.connorhs.DAO.FileIOException;
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
        // TODO: Use steams to list all items purchasable for the current balance
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
