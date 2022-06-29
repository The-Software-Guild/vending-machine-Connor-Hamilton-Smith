package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.DTO.Change;
import com.wiley.c242.connorhs.DAO.DaoException;
import com.wiley.c242.connorhs.DAO.FileIOException;
import com.wiley.c242.connorhs.DTO.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VendingMachineServiceLayer
{
    private VendingMachineDAO dao;

    public VendingMachineServiceLayer(VendingMachineDAO dao)
    {
        this.dao = dao;
    }

    public void addBalance(BigDecimal value)
    {
        dao.addFunds(value);
    }

    public String getBalance()
    {
        return dao.getBalance().toString();
    }

    public List<Change> returnChange()
    {
        List<Change> change = new ArrayList<>();

        Change[] changeTypes = Change.values();
        for (Change type : changeTypes)
        {
            change.addAll(dao.getChange(type));
        }

        return change;
    }

    public boolean purchaseItem(String id) throws DaoException
    {
        Item item = dao.getItem(id);

        if (item == null) {
            return false;
        }

        dao.removeFunds(item.getPrice());
        dao.removeItem(id);

        return true;
    }

    public Collection<Item> getInventory()
    {
        return dao.getInventory();
    }

    public Item getItem(String id)
    {
        return dao.getItem(id);
    }

    public void saveInventory() throws FileIOException
    {
        dao.saveInventory();
    }
}
