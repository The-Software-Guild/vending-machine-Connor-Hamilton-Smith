package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.DTO.Change;
import com.wiley.c242.connorhs.DAO.DaoException;
import com.wiley.c242.connorhs.DAO.FileIOException;
import com.wiley.c242.connorhs.DTO.Item;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Item> getPurchasableInventory()
    {
        double availableBalance = dao.getBalance().doubleValue();
        List <Item> inventory = dao.getInventory();

        // Collect a new list of items filtered by available balance > price & quantity > 0, then sort by price (ascending)
        return inventory.stream()
                .filter((item) -> item.getPrice().doubleValue() <= availableBalance)
                .filter((item) -> item.getQuantity() > 0)
                .sorted(Comparator.comparing(Item::getPrice, (id1, id2) -> { return id1.compareTo(id2); }))
                .collect(Collectors.toList());
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
