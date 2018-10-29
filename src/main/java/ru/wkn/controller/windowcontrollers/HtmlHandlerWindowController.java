package ru.wkn.controller.windowcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class HtmlHandlerWindowController {

    @FXML
    private MenuItem menuItemExit;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private TextField textFieldURL;
    @FXML
    private TextField textFieldDepth;
    @FXML
    private Button buttonPerformCheck;
    @FXML
    private ListView listViewVisitedAddresses;
    @FXML
    private ListView listViewCurrentDNS;
    @FXML
    private ListView listViewExternalDNS;

    @FXML
    private void clickOnMenuItemExit() {
        System.exit(0);
    }

    @FXML
    private void clickOnMenuItemAbout() {
    }

    @FXML
    private void clickOnButtonPerformCheck() {
        if (!textFieldURL.getText().equals("")) {
            int depth;
            String depthAsString = textFieldDepth.getText();
            if (depthAsString.equals("")) {
                depth = 1;
            } else {
                depth = Integer.valueOf(depthAsString);
            }
        }
    }
}
