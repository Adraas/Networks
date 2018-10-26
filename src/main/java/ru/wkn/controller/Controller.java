package ru.wkn.controller;

import ru.wkn.view.windows.HtmlHandlerWindow;

import java.io.IOException;

public class Controller {

    public void startWithHtmlHandlerWindow() throws IOException {
        HtmlHandlerWindow htmlHandlerWindow = new HtmlHandlerWindow();
        htmlHandlerWindow.windowInitialization("HTML-Handler", 600, 400);
    }
}
