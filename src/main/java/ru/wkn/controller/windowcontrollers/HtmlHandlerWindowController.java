package ru.wkn.controller.windowcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ru.wkn.model.HandlerFacade;
import ru.wkn.model.http.Connector;

import java.io.IOException;

public class HtmlHandlerWindowController {

    @FXML
    private TextField textFieldURI;
    @FXML
    private TextField textFieldPort;
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HTML-Handler");
        alert.setHeaderText("Информация о программе и авторе");
        alert.setContentText("Задачи:\n"
                .concat("-Вывести список ссылок просмотренных страниц;\n")
                .concat("-Вывести список ссылок изображений текущего сервера;\n")
                .concat("-Вывести список ссылок изображений внешних серверов.\n\n")
                .concat("Автор: Пикалов Артем\n")
                .concat("Группа: 6302-090301D"));
        alert.show();
    }

    @FXML
    private void clickOnButtonPerformCheck() {
        String uriAddress = textFieldURI.getText();
        int port = Integer.valueOf(textFieldPort.getText());
        if (!uriAddress.equals("")) {
            int depth;
            String depthAsString = textFieldDepth.getText();
            if (depthAsString.equals("")) {
                depth = 1;
            } else {
                depth = Integer.valueOf(depthAsString);
            }
            handlerFacade = new HandlerFacade(new Connector(uriAddress, port));
            try {
                handlerFacade.initLinks("GET", depth);
            } catch (IOException e) {
                e.printStackTrace();
            }

            fillListsOfView();
        }
    }

    private void fillListsOfView() {
        clearContainers();

        listViewVisitedAddresses.getItems().addAll(handlerFacade.getVisitedLinks());
        listViewCurrentDNS.getItems().addAll(handlerFacade.getImageLinks(true));
        listViewExternalDNS.getItems().addAll(handlerFacade.getImageLinks(false));
    }

    private void clearContainers() {
        listViewVisitedAddresses.getItems().clear();
        listViewCurrentDNS.getItems().clear();
        listViewExternalDNS.getItems().clear();
    }
}
