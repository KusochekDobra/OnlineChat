package chat.server;

import java.util.ArrayList;

public class SendMessage implements Runnable {
    public static ArrayList<User> lastSendUser;


    @Override
    public synchronized void run() {

       for(User item : lastSendUser){
           for(int j = 0; j < Connection.users.size(); j++){
               //Передать сообщение
           }
       }
    }

    public static void add(User user){
        lastSendUser.add(user);
    }

}
