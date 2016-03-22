package chat.server;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    public static Connection connection;
    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {

        connection = new Connection();


        primaryStage.setTitle("Server");
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("view.fxml"));

        try {
            AnchorPane rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();

            Thread mainThread = new Thread(connection);
            mainThread.start();

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    try {
                        stop();
                        connection.close();
                       mainThread.join();
                    } catch (Exception e) {
                        e.printStackTrace();
                      ;
                    }
                }
            });

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

