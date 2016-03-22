package chat.server;

import sun.plugin2.message.Message;

import java.net.InetAddress;
import java.net.Socket;

public class User{

    private String message;

    private Socket socket;

    public User(Socket socket) {
        this.socket = socket;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Socket getSocket(){
        return socket;
    }

    public User(Socket socket, String message){
        this.socket = socket;
        this.message = message;
    }

    public User(String message, Socket socket){
        this(socket, message);
    }

    public String getMessage() {
        return message;
    }
}
