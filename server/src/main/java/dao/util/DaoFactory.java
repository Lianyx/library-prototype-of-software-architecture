package dao.util;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private static Map<Class<?>, Object> serviceMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public synchronized static <TF> TF getService(Class<TF> serviceName) {
        if (serviceMap.containsKey(serviceName)) {
            return (TF) serviceMap.get(serviceName);
        }
        try {
            Class<?> implClass = Class.forName(serviceName.getName() + "Impl");
            Object implObject = implClass.newInstance();
            serviceMap.put(serviceName, implObject);
            return (TF) implObject;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return (TF) new Object();
        }
    }
}
