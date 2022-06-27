package com.wiley.c242.connorhs.Controller;

import com.wiley.c242.connorhs.Model.DAO.FileStorageIO;
import com.wiley.c242.connorhs.Model.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.View.ConsoleUserIO;
import com.wiley.c242.connorhs.View.VendingMachineView;

public class Application
{
    public static void main(String[] args)
    {
        VendingMachineView view = new VendingMachineView(new ConsoleUserIO());
        VendingMachineDAO dao = new VendingMachineDAO(new FileStorageIO("src/main/java/com/wiley/c242/connorhs/Model/Data/Items.txt"));
        VendingMachineServiceLayer serviceLayer = new VendingMachineServiceLayer(dao);

        VendingMachineController controller = new VendingMachineController(serviceLayer, view);

        controller.Run();
    }
}
