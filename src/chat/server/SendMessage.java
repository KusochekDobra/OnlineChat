package chat.server;

import java.io.IOException;

import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class SendMessage implements Runnable {
    public ArrayList<User> lastSendUser;

    public SendMessage() {
        lastSendUser = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            if(!lastSendUser.isEmpty()) {
                for (User sender : lastSendUser) {
                    for (User receiver : Connection.users) {
                        try {
                            new ObjectOutputStream(receiver.getSocket().getOutputStream()).writeUTF(sender.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                            // add user disconnect

                        }
                    }
                }

                lastSendUser.clear();
                try {
                    wait(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void add(User user){
        lastSendUser.add(user);
    }

}
