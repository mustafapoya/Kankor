package net.golbarg.kankor.view.exam.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AnswerSheetViewController implements Initializable {
    @FXML
    private VBox root;

    ObservableList<AnswerItemViewController> rowList = FXCollections.observableArrayList();
    int numberOfRows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);

        // test purpose
        //initAnswerSheet(100);
    }

    public void initAnswerSheet(int numberOfRows) {
        this.numberOfRows = numberOfRows;

        try {
            for (int i = 0; i < numberOfRows; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(AnswerSheetViewController.class.getResource("answer-item-view.fxml"));
                HBox element = fxmlLoader.load();
                AnswerItemViewController controller = fxmlLoader.getController();
                controller.setItemNumber(i + 1);
                rowList.add(controller);
                root.getChildren().add(element);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ObservableList<AnswerItemViewController> getRowList() {
        return rowList;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

}
