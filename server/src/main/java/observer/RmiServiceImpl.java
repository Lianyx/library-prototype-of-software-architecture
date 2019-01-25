package observer;


import dao.MessageDao;
import object.po.Message;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static dao.util.DaoFactory.getService;

public class RmiServiceImpl extends Observable implements RmiService {
    private MessageDao messageDao;
    private Message lastMessage;

    public RmiServiceImpl() {
        this.messageDao = getService(MessageDao.class);
        this.lastMessage = new Message(null, null); // 只要一个localdatetime

        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(5 * 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                setChanged();
//                notifyObservers(LocalDateTime.now());
//            }

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    System.out.println("observable running");

                    ArrayList<Message> messages = messageDao.selectByUsername("admin");
                    // 反正目前只考虑管理员的消息，而管理员的消息等价于其中一个管理员的消息

                    Message newLast = messages.stream().sorted((a, b) -> {
                        if (a.getTime().isBefore(b.getTime())) {
                            return 1; // a < b，那么，a 要排 b 後面
                        }
                        if (a.getTime().isAfter(b.getTime())) {
                            return -1;
                        }
                        return a.getContent().compareTo(b.getContent());
                    }).findFirst().orElse(new Message(null, null));

                    if (newLast.getTime().isAfter(lastMessage.getTime())) {
                        lastMessage = newLast;
                        setChanged();
                        notifyObservers(messages);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }, 0, 5, TimeUnit.SECONDS);
        }).start();
    }

    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(o);
        addObserver(mo);
    }
}
