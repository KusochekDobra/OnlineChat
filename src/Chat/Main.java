package Chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

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
    public void EntryIsOk() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/chat/chat.fxml"));

        try {
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
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
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
