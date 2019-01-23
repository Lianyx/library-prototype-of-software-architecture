package launcher;

import annotation.RMIRemote;
import org.reflections.Reflections;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            Reflections reflections = new Reflections("serviceImpl");
            Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(RMIRemote.class);
            for (Class<?> clazz : classesList) {
                Remote toBeRegistered = (Remote) clazz.newInstance();
                String classFullName = clazz.getName();
                String className = classFullName.substring(
                        classFullName.lastIndexOf(".") + 1,
                        classFullName.length() - 4 // 因为全叫的xxxImpl
                );
                Naming.rebind("rmi://localhost:1099/" + className, toBeRegistered);
            }
            System.out.println("Server ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
