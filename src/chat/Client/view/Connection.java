package chat.client.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private final String ip;
    private final int PORT;
    private boolean enough;

    public Connection(String ip, int PORT) {
        this.ip = ip;
        this.PORT = PORT;
        enough = false;
        try {
            socket = new Socket(ip, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!enough) {
                socket.getOutputStream().write("SDJfasg;f gjkadfn;".getBytes());

                // objectOutputStream.writeObject("Privet");
                //JOptionPane.showMessageDialog(null, (String) objectInputStream.readObject());

            }
        } catch (IOException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(new JFrame(), "ERROR", "Can't connect to the server, sorry", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Thread died");
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        enough = true;
    }
}
