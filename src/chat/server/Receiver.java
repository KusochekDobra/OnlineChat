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
            while (socket.isConnected())
            { /**!!!!!!!!!!!!!!*/
                String string = input.readUTF();
                user.setMessage(string);
                SendMessage.lastSendUser.add(user);

                //Каждый раз создавать новый объект ЮЗЕРА
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Can't close socket((");
            }
        }
    }

    private User findUser(Socket socket){
        for(User item : Connection.getUsers()){
            if (socket.getInetAddress() == item.getIP()) return item;
        }
        throw new NoSuchElementException("Что-то пошло не так, юзер должен существовать!") ;
    }



}
