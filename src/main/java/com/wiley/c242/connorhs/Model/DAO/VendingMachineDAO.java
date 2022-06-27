package com.wiley.c242.connorhs.Model.DAO;
import com.wiley.c242.connorhs.Model.DTO.FileIOException;
import com.wiley.c242.connorhs.Model.DTO.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDAO
{
    private StorageIO io;

    public VendingMachineDAO(StorageIO io)
    {
        this.io = io;
    }


    private Map<String, Item> inventory = new HashMap<>();

    // Decrement the quantity of an item
    public void removeItem(String name)
    {

    }

    public List<Item> getInventory()
    {
        return new ArrayList<>(inventory.values());
    }

    public void loadInventory() throws FileIOException
    {
        List<String> dataList = io.load();
        for (String s : dataList)
        {
            String[] itemDetails = s.split("::");
            Item item = new Item(itemDetails[0], new BigDecimal(itemDetails[1]), Integer.parseInt(itemDetails[2]));
            inventory.put(itemDetails[0], item);
        }
    }

    public void saveInventory() throws FileIOException
    {
        List<String> dataList = new ArrayList<>();
        for (Item i : inventory.values())
        {
            dataList.add(i.toStringData());
        }
        io.save(dataList);
    }
}
