package com.wiley.c242.connorhs;

import com.wiley.c242.connorhs.Controller.VendingMachineController;
import com.wiley.c242.connorhs.Controller.VendingMachineServiceLayer;
import com.wiley.c242.connorhs.DAO.AuditLogDAO;
import com.wiley.c242.connorhs.DAO.FileStorageIO;
import com.wiley.c242.connorhs.DAO.VendingMachineDAO;
import com.wiley.c242.connorhs.DTO.FileIOException;
import com.wiley.c242.connorhs.UI.ConsoleUserIO;
import com.wiley.c242.connorhs.UI.VendingMachineView;

public class Application
{
    public static void main(String[] args)
    {
        // Set the paths to values obtained from the args if they exist, otherwise use a default value
        String inventoryFilePath = (args.length <= 1) ? "src/main/java/com/wiley/c242/connorhs/Items.txt" : args[0];
        String auditFilePath = (args.length <= 2) ? "src/main/java/com/wiley/c242/connorhs/AuditLog.txt" : args[1];

        VendingMachineView view = new VendingMachineView(new ConsoleUserIO());
        try {
            AuditLogDAO auditDao = new AuditLogDAO(new FileStorageIO(auditFilePath));
            VendingMachineDAO inventoryDao = new VendingMachineDAO(new FileStorageIO(inventoryFilePath));
            VendingMachineServiceLayer serviceLayer = new VendingMachineServiceLayer(inventoryDao, auditDao);

            VendingMachineController controller = new VendingMachineController(serviceLayer, view);

            controller.Run();
        } catch (FileIOException e) {
            view.printMessage(e.getMessage());
        }
    }
}
