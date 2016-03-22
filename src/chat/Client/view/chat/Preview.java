package chat.client.view.chat;

import javafx.scene.control.TabPane;

public class Preview {

    private static final int size = 400;


    TabPane tabPane;

    public Preview(TabPane tabPane) {
        this.tabPane = tabPane;
        run();
    }


    public void run() {
        tabPane.setPrefSize(size * 16/9, size);
       // tabPane.s
    }
}
