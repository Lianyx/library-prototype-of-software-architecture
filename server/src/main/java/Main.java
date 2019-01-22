import serviceImpl.HelloServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            Naming.rebind("rmi://localhost:1099/MyServer", new HelloServiceImpl());
            System.out.println("Server ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
