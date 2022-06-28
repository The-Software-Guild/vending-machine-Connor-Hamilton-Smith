package com.wiley.c242.connorhs;

import com.wiley.c242.connorhs.Controller.VendingMachineController;
import com.wiley.c242.connorhs.Controller.VendingMachineServiceLayer;
import com.wiley.c242.connorhs.Model.DAO.FileStorageIO;
import com.wiley.c242.connorhs.Model.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.Model.DTO.FileIOException;
import com.wiley.c242.connorhs.View.ConsoleUserIO;
import com.wiley.c242.connorhs.View.VendingMachineView;

public class Application
{
    public static void main(String[] args)
    {
        String filePath = (args.length == 0) ? "src/main/java/com/wiley/c242/connorhs/Model/Data/Items.txt" : args[0];

        VendingMachineView view = new VendingMachineView(new ConsoleUserIO());
        try
        {
            VendingMachineDAO dao = new VendingMachineDAO(new FileStorageIO(filePath));
            VendingMachineServiceLayer serviceLayer = new VendingMachineServiceLayer(dao);

            VendingMachineController controller = new VendingMachineController(serviceLayer, view);

            controller.Run();
        } catch (FileIOException e) {
            view.printMessage(e.getMessage());
        }
    }
}
