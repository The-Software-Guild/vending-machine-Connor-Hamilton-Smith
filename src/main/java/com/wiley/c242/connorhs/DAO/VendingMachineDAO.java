package com.wiley.c242.connorhs.DAO;
import com.wiley.c242.connorhs.DTO.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineDAO implements DAO
{
    private StorageIO io;

    public VendingMachineDAO(StorageIO io) throws FileIOException
    {
        this.io = io;
        loadInventory();
    }

    private Map<String, Item> inventory = new HashMap<>();
    private Balance balance = new Balance();

    public void addFunds(BigDecimal value)
    {
        balance.addFunds(value);
    }

    public void removeFunds(BigDecimal value) throws DaoException
    {
        BigDecimal newBalance = balance.getValue().subtract(value);

        if (newBalance.doubleValue() >= 0)
            balance.removeFunds(value);
        else
            throw new DaoException("Insufficient funds");
    }

    public BigDecimal getBalance()
    {
        return balance.getValue();
    }

    public List<Change> getChange(Change type)
    {
        List<Change> change = new ArrayList<>();
        BigDecimal[] divideAndRemainder = balance.getValue().divideAndRemainder(Change.getValue(type));
        for (int i = 0; i < divideAndRemainder[0].intValue(); i++)
        {
            change.add(type);
        }
        balance.setValue(divideAndRemainder[1]);
        return change;
    }

    public void removeItem(String id) throws DaoException
    {
        Item item = inventory.get(id);
        int currentQuantity = item.getQuantity();

        if (item == null)
            throw new DaoException("Item ID not recognised");
        else if (currentQuantity <= 0)
            throw new DaoException("Item unavailable");
        else
            item.setQuantity(currentQuantity - 1);
    }

    public List<Item> getInventory()
    {
        return inventory.values().stream()
                .sorted(Comparator.comparing(Item::getId, (id1, id2) -> { return id1.compareTo(id2); }))
                .collect(Collectors.toList());
    }

    public Item getItem(String id)
    {
        return inventory.get(id);
    }

    public void loadInventory() throws FileIOException
    {
        List<String> dataList = io.load();
        for (String s : dataList)
        {
            String[] itemDetails = s.split(DELIMITER);
            Item item = new Item(itemDetails);
            inventory.put(itemDetails[idIndex], item);
        }
    }

    public void saveInventory() throws FileIOException
    {
        List<String> dataList = new ArrayList<>();
        for (Item i : inventory.values())
        {
            dataList.add(i.toDataString());
        }
        io.save(dataList);
    }
}
