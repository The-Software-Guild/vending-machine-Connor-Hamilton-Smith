package com.wiley.c242.connorhs.Model.DAO;
import com.wiley.c242.connorhs.Model.DTO.FileIOException;

import java.util.List;

public interface StorageIO
{
    public List<String> load() throws FileIOException;

    public void save(List<String> data) throws FileIOException;
}
