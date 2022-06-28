package com.wiley.c242.connorhs.Model.DAO;
import com.wiley.c242.connorhs.Model.DTO.*;
import java.math.BigDecimal;
import java.util.List;

public interface DAO
{
    // Constant values for marshalling and unmarshalling data of the form: itemID::itemName::price::quantity
    public static final String DELIMITER = "::";
    public static final int idIndex = 0, nameIndex = 1, priceIndex = 2, quantityIndex = 3;


    public void addFunds(BigDecimal value);

    public void removeFunds(BigDecimal value) throws DaoException;

    public BigDecimal getBalance();

    public List<Change> getChange(Change type);

    public void removeItem(String id) throws DaoException;

    public List<Item> getInventory();

    public void loadInventory() throws FileIOException;

    public void saveInventory() throws FileIOException;
}
