package chat.client.view;

import chat.client.view.chat.ChatController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{
    private Socket socket;
    public static ChatController chatController;

    private final String ip;

    private final int PORT;
    private boolean enough;

    private volatile static String userName;

    public Connection(String ip, int PORT){
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
                byte[] buffer = new byte[4096];
                int bc = socket.getInputStream().read(buffer); // receive?
                String string = new String(buffer, 0, bc);

                showMessage(string);
                // objectOutputStream.writeObject("Privet");
                //JOptionPane.showMessageDialog(null, (String) objectInputStream.readObject());

                //   }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String string) {
        chatController.setText(string);
    }

    public void close() {
        enough = true;

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(){
        String s = chatController.getMessageOut();

        try {
            socket.getOutputStream().write((userName + ": " + s).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setChatController(ChatController chatController) {
        Connection.chatController = chatController;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
