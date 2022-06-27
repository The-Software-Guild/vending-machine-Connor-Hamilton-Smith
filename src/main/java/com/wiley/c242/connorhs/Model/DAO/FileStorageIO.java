package com.wiley.c242.connorhs.Model.DAO;

import com.wiley.c242.connorhs.Model.DTO.FileIOException;

import java.io.*;
import java.util.*;

public class FileStorageIO implements StorageIO
{
    private String path;

    public FileStorageIO(String filePath)
    {
        this.path = filePath;
    }

    @Override
    public List<String> load() throws FileIOException
    {
        // Create a scanner object to read the file
        Scanner input;
        try { input = new Scanner( new BufferedReader( new FileReader(path))); }
        // Throw an exception if the file path is not recognised
        catch (FileNotFoundException e) { throw new FileIOException(true); }

        List<String> dataList = new ArrayList<>();
        // Loop through each line in the file
        while (input.hasNextLine())
        {
            // Add the string data to the list
            String data = input.nextLine();
            if (data != null)
                dataList.add(data);
        }
        // Close the input stream
        input.close();
        return dataList;
    }

    @Override
    public void save(List<String> dataList) throws FileIOException
    {
        // Create a print writer for writing to file
        PrintWriter output;
        try { output = new PrintWriter(new FileWriter(path)); }
        catch (IOException e) { throw new FileIOException(false); }

        for (String data : dataList)
        {
            // Write to file
            output.println(data);
            output.flush();
        }
        // Close output stream
        output.close();
    }
}
