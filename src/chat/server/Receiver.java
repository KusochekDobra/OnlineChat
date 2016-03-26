package chat.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.NoSuchElementException;


public class Receiver extends Thread{
    private User user;
    private Socket socket;
    private ObjectInputStream input;
    public ObjectOutputStream output;


    public Receiver(Socket socket) throws IOException {
        this.socket = socket;
        input = new ObjectInputStream (socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
        user = findUser(socket);
    }

    @Override
    public void run() {
        try {
            while (true)
            {
                String string = (String) input.readObject();
                user.setMessage(string);
                Connection.sendMessage.lastSendUser.add(user);

                StaticField.controller.test();
                //ДИЧ (нет) (да)
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Can't close socket((");
            }
        }
    }

    private User findUser(Socket socket){
        for(User item : Connection.getUsers()){
            if (socket.getInetAddress() == item.getSocket().getInetAddress()) return item;
        }
        throw new NoSuchElementException("Что-то пошло не так, юзер должен существовать!") ;
    }



}
