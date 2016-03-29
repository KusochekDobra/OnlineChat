package chat.client;


import chat.client.view.Connection;
import chat.client.view.chat.ChatController;
import chat.client.view.chat.Preview;
import chat.client.view.entry.LoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Connection connection;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Entry");
        setUserAgentStylesheet(STYLESHEET_MODENA);
        initRootLayout();

        //showAnything();
    }
    /*
   private void showAnything() {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(Main.class.getResource("view/chat/chat.fxml"));
       AnchorPane personOverview = null;
       try {
           personOverview = (AnchorPane) loader.load();
       } catch (IOException e) {
           e.printStackTrace();
       }

       rootLayout.setCenter(personOverview);

   }
 */
    public void entryIsOk() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/chat/chat.fxml"));

        try {
            TabPane tabPane = (TabPane) loader.load();

            new Preview(tabPane);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Chatiiik");

            Scene scene = new Scene(tabPane);
            dialogStage.setScene(scene);

            connection = new Connection("127.0.0.1", 9999);
            connection.setUserName(LoginController.name);

            Thread connectionThread = new Thread(connection);
            connectionThread.start();

            ChatController chatController = loader.getController();

            chatController.setConnection(connection);
            Connection.setChatController(chatController);

            dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    try {
                        stop();

                        System.out.println(connectionThread.isAlive());

                        connection.close();
                        connectionThread.join();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            dialogStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/entry/Login.fxml"));

        try {
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();



        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LoginController.closeTimer();
            }
        });
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
