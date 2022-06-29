package com.wiley.c242.connorhs.DAO;

public class FileIOException extends Exception
{
    private String message;

    public FileIOException(boolean isInput)
    {
        if (isInput)
            message = "File not found, cannot load data.";
        else
            message =  "File not found, cannot save data.";
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
