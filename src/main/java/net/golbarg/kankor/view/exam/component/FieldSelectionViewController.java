package net.golbarg.kankor.view.exam.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FieldSelectionViewController implements Initializable {
    @FXML
    private HBox root;

    ObservableList<ColumnSelectionViewController> columnList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            for(int i = 0; i < 3; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(FieldSelectionViewController.class.getResource("column-selection-view.fxml"));
                VBox element = fxmlLoader.load();
                ColumnSelectionViewController controller = fxmlLoader.getController();
                columnList.add(controller);
                root.getChildren().add(element);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        root.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                System.out.println(getSelectedFieldValue());
            }
        });
    }

    public String getFacultyCode() {
        return getSelectedFieldValue();
    }

    public String getSelectedFieldValue(){
        String temp = "";
        for (int i = 0; i < columnList.size(); i++) {
            temp += columnList.get(i).getSelectedValue();
        }
        // it is revered because layout direction is RTL
        return reverse(temp);
//        return temp;
    }

    public static String reverse(String value){
        String temp = "";
        for (int i = value.length()-1; i >= 0; i--) {
            temp += value.charAt(i);
        }
        return temp;
    }

    public boolean isFieldSelected(){
        for (int i = 0; i < columnList.size(); i++) {
            if(columnList.get(i).getSelectedValue() == -1){
                return false;
            }
        }
        return true;
    }

    public boolean isValidCode() {
        if(getSelectedFieldValue().contains("-")){
            return false;
        }else{
            return true;
        }
    }

    public boolean equals(FieldSelectionViewController field) {
        if(this.getSelectedFieldValue().equalsIgnoreCase(field.getSelectedFieldValue())){
            return true;
        }else{
            return false;
        }
    }


}
