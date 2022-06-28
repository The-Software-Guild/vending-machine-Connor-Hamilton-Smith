package com.wiley.c242.connorhs.Model.DTO;

public class DaoException extends Exception
{
    private String message;

    public DaoException()
    {
        message = "DAO Exception";
    }

    public DaoException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
