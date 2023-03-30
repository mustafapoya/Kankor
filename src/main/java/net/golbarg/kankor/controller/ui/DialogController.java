package net.golbarg.kankor.controller.ui;

import javafx.scene.control.Alert;

public class DialogController {

    public static void showMessage(Alert.AlertType type, String title, String headerText, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static Alert getAlert(Alert.AlertType type, String title, String headerText, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        return alert;
    }

    
}
