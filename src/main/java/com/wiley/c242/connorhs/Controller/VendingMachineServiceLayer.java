package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.Model.DAO.VendingMachineDAO;

public class VendingMachineServiceLayer
{
    VendingMachineDAO dao;

    public VendingMachineServiceLayer(VendingMachineDAO dao)
    {
        this.dao = dao;
    }
}
