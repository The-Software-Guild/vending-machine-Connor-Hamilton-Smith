package com.wiley.c242.connorhs.DTO;
import com.wiley.c242.connorhs.DAO.DAO;
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
        String itemInfo;
        if (name.length() <= 7)
            itemInfo =  id + "\t\t" + name + "\t\t£" + price.toString() + "\t\t" + String.valueOf(quantityInStock);
        else
            itemInfo = id + "\t\t" + name + "\t£" + price.toString() + "\t\t" + String.valueOf(quantityInStock);

        if (quantityInStock <= 0)
            itemInfo += " (UNAVAILABLE)";

        return itemInfo;
    }

    public String toDataString()
    {
        return id + DAO.DELIMITER + name + DAO.DELIMITER + price.toString() + DAO.DELIMITER + String.valueOf(quantityInStock);
    }

    @Override
    public boolean equals(Object object)
    {
        Item item = null;
        if (object instanceof Item)
            item = (Item)object;

        if (item == null)
            return false;

        if (this.id.equals(item.id))
            return true;
        else
            return false;
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
