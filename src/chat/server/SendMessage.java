package chat.server;

import java.io.IOException;

import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class SendMessage implements Runnable {
    public ArrayList<User> lastSendUser;

    volatile private boolean stop = false;

    public SendMessage() {
        lastSendUser = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!stop) {
            if(!lastSendUser.isEmpty()) {
                for (User sender : lastSendUser) {
                    for (User receiver : Connection.users) {
                        //new ObjectOutputStream(receiver.getSocket().getOutputStream()).writeUTF(sender.getMessage());
                        try {
                            receiver.getSocket().getOutputStream().write(sender.getMessage().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                lastSendUser.clear();
            }
        }

    }

    public synchronized void add(User user){
        lastSendUser.add(user);
    }

    public void close(){
        stop = true;

    }

}
