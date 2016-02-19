package Chat.view.entry;

import Chat.Main;
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

        (new Main()).EntryIsOk();
        ((Stage)button.getScene().getWindow()).close();

    }
}
