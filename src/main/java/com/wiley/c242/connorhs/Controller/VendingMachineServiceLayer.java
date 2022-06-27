package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.Model.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.Model.DTO.FileIOException;
import com.wiley.c242.connorhs.Model.DTO.Item;

import java.util.List;

public class VendingMachineServiceLayer
{
    private VendingMachineDAO dao;

    public VendingMachineServiceLayer(VendingMachineDAO dao)
    {
        this.dao = dao;
    }

    public List<Item> getInventory()
    {
        return dao.getInventory();
    }

    public void loadInventory() throws FileIOException
    {
        dao.loadInventory();
    }

    public void saveInventory() throws FileIOException
    {
        dao.saveInventory();
    }
}
