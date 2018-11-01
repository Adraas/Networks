package ru.wkn.controller.windowcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import ru.wkn.model.HandlerFacade;
import ru.wkn.model.http.Connector;

import java.io.IOException;

public class HtmlHandlerWindowController {

    @FXML
    private MenuItem menuItemExit;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private TextField textFieldURI;
    @FXML
    private TextField textFieldDepth;
    @FXML
    private ListView listViewVisitedAddresses;
    @FXML
    private ListView listViewCurrentDNS;
    @FXML
    private ListView listViewExternalDNS;
    private HandlerFacade handlerFacade;

    @FXML
    private void clickOnMenuItemExit() {
        System.exit(0);
    }

    @FXML
    private void clickOnMenuItemAbout() {
    }

    @FXML
    private void clickOnButtonPerformCheck() {
        String uriAddress = textFieldURI.getText();
        if (!uriAddress.equals("")) {
            int depth;
            String depthAsString = textFieldDepth.getText();
            if (depthAsString.equals("")) {
                depth = 1;
            } else {
                depth = Integer.valueOf(depthAsString);
            }
            handlerFacade = new HandlerFacade(new Connector(uriAddress, 80));
            try {
                handlerFacade.initLinks("GET", depth);
            } catch (IOException e) {
                e.printStackTrace();
            }

            fillListsOfView();
        }
    }

    private void fillListsOfView() {
        listViewVisitedAddresses.getItems().addAll(handlerFacade.getVisitedLinks());
        // some instructions
    }
}
