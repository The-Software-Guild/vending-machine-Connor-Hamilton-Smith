package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.DAO.AuditDAO;
import com.wiley.c242.connorhs.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.DTO.Change;
import com.wiley.c242.connorhs.DTO.DaoException;
import com.wiley.c242.connorhs.DTO.FileIOException;
import com.wiley.c242.connorhs.DTO.Item;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineServiceLayer
{
    private VendingMachineDAO inventoryDao;
    private AuditDAO auditDao;

    public VendingMachineServiceLayer(VendingMachineDAO dao, AuditDAO auditDao)
    {
        this.inventoryDao = dao;
        this.auditDao = auditDao;
    }

    public void addBalance(BigDecimal value)
    {
        inventoryDao.addFunds(value);
    }

    public String getBalance()
    {
        return inventoryDao.getBalance().toString();
    }

    public List<Change> returnChange()
    {
        List<Change> change = new ArrayList<>();

        // Get a list of coins for each type of change 5p, 10p etc..
        Change[] changeTypes = Change.values();
        for (Change type : changeTypes)
        {
            change.addAll(inventoryDao.getChange(type));
        }

        return change;
    }

    public boolean purchaseItem(String id) throws DaoException
    {
        Item item = inventoryDao.getItem(id);

        if (item == null) {
            return false;
        }

        inventoryDao.removeFunds(item.getPrice());
        inventoryDao.removeItem(id);

        auditDao.log("PURCHASE::" + item.toDataString());
        return true;
    }

    public List<Item> getPurchasableInventory()
    {
        double availableBalance = inventoryDao.getBalance().doubleValue();
        List <Item> inventory = inventoryDao.getInventory();

        // Collect a new list of items filtered by available balance > price & quantity > 0, then sort by price (ascending)
        return inventory.stream()
                .filter((item) -> item.getPrice().doubleValue() <= availableBalance)
                .filter((item) -> item.getQuantity() > 0)
                .sorted(Comparator.comparing(Item::getPrice, (id1, id2) -> { return id1.compareTo(id2); }))
                .collect(Collectors.toList());
    }

    public Collection<Item> getInventory()
    {
        return inventoryDao.getInventory();
    }

    public Item getItem(String id)
    {
        return inventoryDao.getItem(id);
    }

    public void saveInventory() throws FileIOException
    {
        inventoryDao.saveInventory();
        auditDao.log("SAVING INVENTORY");
    }
}
