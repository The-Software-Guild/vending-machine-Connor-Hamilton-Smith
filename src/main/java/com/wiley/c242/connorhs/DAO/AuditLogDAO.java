package com.wiley.c242.connorhs.DAO;

import com.wiley.c242.connorhs.DTO.FileIOException;
import java.time.LocalDateTime;

public class AuditLogDAO implements AuditDAO
{
    private StorageIO io;

    public AuditLogDAO(StorageIO io) throws FileIOException
    {
        this.io = io;
        log("LOADING INVENTORY");
    }

    @Override
    public void log(String entry)
    {
        String timestampedEntry = LocalDateTime.now().toString() + " : " + entry;
        io.append(timestampedEntry);
    }
}
