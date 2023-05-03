package CODU_RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIServer {
    public static void main(String[] args) {
        try {
            GestorInterface gi = new GestorCODU();
            Naming.rebind("rmi://localhost:50001/G", gi);
            System.out.println("Running");
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}