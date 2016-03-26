package chat.server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;


public class Connection implements Runnable{

    public static ArrayDeque<User> users = new ArrayDeque<>();
    public static SendMessage sendMessage;
    private boolean isEnough = false;
    private Thread threadForSend;
    private ServerSocket serverSocket;

    public ViewController viewController;

    public final int PORT = 9999;

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(PORT);
            sendMessage = new SendMessage();
            threadForSend = new Thread(sendMessage);
            threadForSend.start();

            while (!isEnough){
                Socket socket = serverSocket.accept();

                if (addNewUser(socket))
                    new Thread(new Receiver(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Can't upload server(");
        }
    }


    public boolean addNewUser(Socket socket){
        for (User item: users){
            if(item.getSocket().getInetAddress() == socket.getInetAddress()) {
                System.out.println("User have already been");
                return false;
            }
        }
        users.addLast(new User(socket));
        return true;
    }

    public static ArrayDeque<User> getUsers() {
        return users;
    }

    public void close() {

        isEnough = true;
        sendMessage.close();



        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(threadForSend.isAlive());

    }
}

