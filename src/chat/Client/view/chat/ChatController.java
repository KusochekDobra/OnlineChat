package chat.client.view.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.Socket;
import java.sql.Connection;

public class ChatController {
    @FXML
    public TextArea send_window;

    @FXML
    private TextArea view_window;

    private chat.client.view.Connection connection;

    public void setConnection(chat.client.view.Connection connection) {
        this.connection = connection;
    }

    public void onSend(ActionEvent actionEvent) {
        connection.send();
    }

    public void setText(String string){
        view_window.appendText(string + "\n");
    }

    public String getMessageOut(){
        String s = send_window.getText();

        send_window.clear();
        return s;
    }

}
