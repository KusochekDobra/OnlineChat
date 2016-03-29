package chat.client.view.entry;

import chat.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class LoginController {

    public static final Timer timer = new Timer();

    public static String name;

    @FXML
    Button button;

    @FXML
    BorderPane borderPane;

    @FXML
    TextField login_name;

    @FXML
    Text text;

    @FXML
    void okButton(){

        name = login_name.getText();

        if(!name.equals("")) {
            (new Main()).entryIsOk();

            ((Stage)button.getScene().getWindow()).close();
        }

        else{

            text.setVisible(true);

            TimerTask mMyTimerTask = new TimerTask() {
                @Override
                public void run() {
                    text.setVisible(false);
                }
            };
            timer.schedule(mMyTimerTask, 1700);


        }

    }

    public static void closeTimer() {
        timer.cancel();
    }
}
