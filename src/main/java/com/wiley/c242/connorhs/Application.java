package com.wiley.c242.connorhs;

import com.wiley.c242.connorhs.Controller.VendingMachineController;
import com.wiley.c242.connorhs.Controller.VendingMachineServiceLayer;
import com.wiley.c242.connorhs.DAO.FileStorageIO;
import com.wiley.c242.connorhs.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.DAO.FileIOException;
import com.wiley.c242.connorhs.UI.ConsoleUserIO;
import com.wiley.c242.connorhs.UI.VendingMachineView;

public class Application
{
    public static void main(String[] args)
    {
        String filePath = (args.length == 0) ? "src/main/java/com/wiley/c242/connorhs/Data/Items.txt" : args[0];
        // TODO: Create audit log
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
