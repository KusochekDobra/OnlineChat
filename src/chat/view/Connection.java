package chat.view;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection extends Thread{

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private final String ip;
    private final int PORT;

    public Connection(String ip, int PORT) {
        this.ip = ip;
        this.PORT = PORT;
       }


    @Override
    public void run() {
        try {

            /**ВООООООПРОС*/

            while (true) {
                socket = new Socket(InetAddress.getByName(ip), PORT);

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush();

                objectInputStream = new ObjectInputStream(socket.getInputStream());

                objectOutputStream.writeObject("Privet");
                JOptionPane.showMessageDialog(null, (String) objectInputStream.readObject());
            }
        } catch (IOException e) {

           JOptionPane.showMessageDialog(new JFrame(), "ERROR","Can't connection to the server, sorry(" , JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR","Can't connection to the server, sorry(" , JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException();
        }


    }
}
