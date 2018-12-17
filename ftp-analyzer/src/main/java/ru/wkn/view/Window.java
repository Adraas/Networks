package ru.wkn.view;

import java.io.IOException;

public interface Window {

    void show(String windowTitle, int windowWidth, int windowHeight, String resourceName) throws IOException;
}
