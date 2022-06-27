package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.View.VendingMachineView;

public class VendingMachineController
{
    VendingMachineServiceLayer serviceLayer;
    VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer serviceLayer, VendingMachineView view)
    {
        this.serviceLayer = serviceLayer;
        this.view = view;
    }

    public void Run()
    {

    }
}
