package ru.wkn;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.wkn.controller.Controller;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.startWithFTClientWindow();
    }
}
