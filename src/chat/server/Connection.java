package chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;


public class Connection implements Runnable{

    public static ArrayDeque<User> users = new ArrayDeque<>();

    Thread threadForSend;

    private ServerSocket serverSocket;

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(9999, 10);
           SendMessage sendMessage = new SendMessage();
            threadForSend = new Thread(sendMessage);

            while (true){
                Socket socket = serverSocket.accept();
                    addNewUser(socket.getInetAddress());

                    new Thread(new Receiver(socket)).start();
                    threadForSend.start();
            }
        } catch (IOException e) {
            System.out.println("Can't upload server(");
        }
    }

    public boolean addNewUser(InetAddress inetAddress){
        for (User item: users){
            if(item.getIP() == inetAddress) {System.out.println("User have already been"); return false;}
        }
        users.addLast(new User(inetAddress));
        return true;
    }

    public static ArrayDeque<User> getUsers() {
        return users;
    }

    public void send(){
        ;
    }

}

