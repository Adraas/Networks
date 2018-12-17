package ru.wkn.controller;

import ru.wkn.view.Window;
import ru.wkn.view.windows.FTClientWindow;

import java.io.IOException;

public class Controller {

    public void startWithFTClientWindow() throws IOException {
        Window window = new FTClientWindow();
        window.show("FileTransfer Client", 610, 330, "/fxml/ftclient-window.fxml");
    }
}
