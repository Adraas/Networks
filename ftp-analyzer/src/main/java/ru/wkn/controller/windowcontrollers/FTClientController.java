package ru.wkn.controller.windowcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ru.wkn.model.FTClientFacade;

import java.io.IOException;
import java.util.List;

public class FTClientController {

    @FXML
    private TextField textFieldHost;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private ListView listViewResults;

    private FTClientFacade ftClientFacade;

    @FXML
    private void clickOnButtonCheck() {
        String host = textFieldHost.getText();
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        if (!host.trim().isEmpty()
                && !username.trim().isEmpty()
                && !password.trim().isEmpty()) {
            try {
                ftClientFacade = new FTClientFacade();
                fillListViewResults(ftClientFacade.getSizesOfFileGroups(host, username, password));
            } catch (IOException e) {
                showInfo(e.getClass().getSimpleName(), e.getCause().getClass().getSimpleName(),
                        e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    private void fillListViewResults(List<String> resultLines) {
        listViewResults.getItems().clear();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(resultLines);
        listViewResults.setItems(observableList);
    }

    private void showInfo(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    @FXML
    private void clickOnExit() {
        System.exit(0);
    }
}
