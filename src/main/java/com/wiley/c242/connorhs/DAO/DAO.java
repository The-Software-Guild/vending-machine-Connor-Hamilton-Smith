package com.wiley.c242.connorhs.DAO;
import com.wiley.c242.connorhs.DTO.FileIOException;

public interface DAO
{
    // Constant values for marshalling and unmarshalling data of the form: itemID::itemName::price::quantity
    public static final String DELIMITER = "::";
    public static final int idIndex = 0, nameIndex = 1, priceIndex = 2, quantityIndex = 3;

    public void loadInventory() throws FileIOException;

    public void saveInventory() throws FileIOException;
}
