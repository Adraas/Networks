package ru.wkn.view.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.wkn.view.Window;

import java.io.IOException;

public class FTClientWindow implements Window {

    @Override
    public void show(String windowTitle, int windowWidth, int windowHeight, String resourceName) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(resourceName));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane, windowWidth, windowHeight));
        stage.setResizable(false);
        stage.setTitle(windowTitle);
        stage.show();
    }
}
