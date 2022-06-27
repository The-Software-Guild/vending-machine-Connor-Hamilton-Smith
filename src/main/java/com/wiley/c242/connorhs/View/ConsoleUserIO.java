package com.wiley.c242.connorhs.View;
import java.util.Scanner;

public class ConsoleUserIO implements UserIO
{
    private Scanner input = new Scanner(System.in);

    @Override
    public String getMessage()
    {
        return input.nextLine();
    }

    @Override
    public void printMessage(String message)
    {
        System.out.println(message);
    }
}
