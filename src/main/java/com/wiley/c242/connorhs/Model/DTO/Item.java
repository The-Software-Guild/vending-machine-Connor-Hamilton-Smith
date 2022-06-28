package com.wiley.c242.connorhs.Model.DTO;
import com.wiley.c242.connorhs.Model.DAO.DAO;
import java.math.BigDecimal;

public class Item
{
    private String id;
    private String name;
    private BigDecimal price;
    private int quantityInStock;

    private Item() { }

    public Item(String id, String name, BigDecimal price, int stock)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = stock;
    }

    public Item(String[] itemDetails)
    {
        this.id = itemDetails[DAO.idIndex];
        this.name = itemDetails[DAO.nameIndex];
        this.price = new BigDecimal(itemDetails[DAO.priceIndex]);
        this.quantityInStock = Integer.parseInt(itemDetails[DAO.quantityIndex]);
    }

    public String toDisplayString()
    {
        if (name.length() <= 7)
            return id + "\t\t" + name + "\t\t£" + price.toString() + "\t\t" + String.valueOf(quantityInStock);
        else
            return id + "\t\t" + name + "\t£" + price.toString() + "\t\t" + String.valueOf(quantityInStock);
    }

    public String toDataString()
    {
        return id + DAO.DELIMITER + name + DAO.DELIMITER + price.toString() + DAO.DELIMITER + String.valueOf(quantityInStock);
    }

    /*
        Getters and setters
     */

    public String getId() { return id; }

    public String getName() { return name; }

    public BigDecimal getPrice() { return price; }

    public int getQuantity() { return quantityInStock; }

    public void setQuantity(int quantity) { quantityInStock = quantity; }
}
