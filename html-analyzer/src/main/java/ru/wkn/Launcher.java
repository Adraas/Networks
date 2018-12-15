package ru.wkn;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.wkn.controller.Controller;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller();
        try {
            controller.startWithHtmlHandlerWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
