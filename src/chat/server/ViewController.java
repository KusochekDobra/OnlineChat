package chat.server;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ViewController {
    Main main;

    @FXML
    private TextArea textArea;

    public void test(String s){

       textArea.appendText(s + "\n");
    }
}
