package chat.server;

public class Main {

    public static void main(String[] args) {
        Connection connection = new Connection();
        new Thread(connection).start();
    }
}

