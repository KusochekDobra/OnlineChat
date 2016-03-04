package chat.server;

import sun.plugin2.message.Message;
import sun.security.x509.IPAddressName;

import java.net.InetAddress;
import java.util.ArrayDeque;
import java.util.Iterator;

public class User{

    private String message;

    private InetAddress inetAddress;

    public User(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public InetAddress getIP(){
        return inetAddress;
    }

    public String getMessage() {
        return message;
    }
}
