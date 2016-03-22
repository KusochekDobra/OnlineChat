package chat.client.view.entry;

import chat.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    Button button;

    @FXML
    BorderPane borderPane;

    @FXML
    void okButton(){

        ((Stage)button.getScene().getWindow()).close();

        (new Main()).entryIsOk();


    }
}
