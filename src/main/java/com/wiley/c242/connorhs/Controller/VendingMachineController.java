package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.Model.DTO.FileIOException;
import com.wiley.c242.connorhs.Model.DTO.Item;
import com.wiley.c242.connorhs.View.VendingMachineView;

import java.math.BigDecimal;

public class VendingMachineController
{
    private VendingMachineServiceLayer serviceLayer;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer serviceLayer, VendingMachineView view)
    {
        this.serviceLayer = serviceLayer;
        this.view = view;
        try { serviceLayer.loadInventory(); }
        catch(FileIOException e) { e.getMessage(); }
    }

    public void Run()
    {
        for (;;)
        {
            String command = view.getCommand().toUpperCase();

            switch (command)
            {
                case "LIST":
                    view.displayItems(serviceLayer.getInventory());
                    break;
                case "PURCHASE":
                    break;
                case "INSERT":
                    BigDecimal value = view.getDecimal();
                    view.printMessage(value.toString());
                    break;
                case "EXIT":
                    view.printMessage("Saving inventory...");
                    try { serviceLayer.loadInventory(); }
                    catch(FileIOException e) { e.getMessage(); }
                    view.printMessage("Exiting program...");
                    return;
                default:
                    view.printMessage("Command not recognised, please try again... \nAccepted commands are PURCHASE, INSERT, EXIT\n");
                    break;
            }
        }
    }
}
