package launcher;

import annotation.RMIRemote;
import dao.RecordDao;
import observer.RmiService;
import observer.RmiServiceImpl;
import org.reflections.Reflections;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static dao.util.DaoFactory.getService;

public class Main {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            RmiService rmiService = (RmiService) UnicastRemoteObject
                    .exportObject(new RmiServiceImpl(), 1099);
            registry.bind("RmiService", rmiService);

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

            checkPenaltySchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkPenaltySchedule() {
        RecordDao recordDao = getService(RecordDao.class);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("check-penalty scheduler running");
                recordDao.updatePenalty();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, 0, 20, TimeUnit.SECONDS);
    }
}
