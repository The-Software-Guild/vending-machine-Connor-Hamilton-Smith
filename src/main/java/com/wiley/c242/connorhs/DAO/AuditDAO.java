package com.wiley.c242.connorhs.DAO;

import com.wiley.c242.connorhs.DTO.FileIOException;

public interface AuditDAO
{
    public static final String DELIMITER = "::";
    public static final int idIndex = 0, nameIndex = 1, priceIndex = 2, quantityIndex = 3;

    public void log(String entry);
}
