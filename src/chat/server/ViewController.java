package chat.server;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ViewController {
    Main main;

    @FXML
    private TextArea textArea;

    public void test(){

        textArea.setText("RABOTAiet");
        textArea.appendText("HUUUUUUUUUUUUUUU");
    }
}
