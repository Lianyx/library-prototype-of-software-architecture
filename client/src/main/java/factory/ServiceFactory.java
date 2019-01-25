package factory;

import org.apache.poi.ss.formula.functions.T;
import service.*;
import utils.SystemInfo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static Map<Class<?>, Object> serviceMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public synchronized static <T> T getService(Class<T> serviceClass) {
        if (serviceMap.containsKey(serviceClass)) {
            return (T) serviceMap.get(serviceClass);
        }
        try {
            int beginIndex = serviceClass.getName().lastIndexOf('.') + 1;
            T newService = (T) Naming.lookup(SystemInfo.RML_BASE_URL.getValue() + serviceClass.getName().substring(beginIndex));
            serviceMap.put(serviceClass, newService);
            return newService;
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
