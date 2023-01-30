package net.golbarg.kankor.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ColumnSelectionViewController implements Initializable {
    @FXML
    private VBox root;

    @FXML
    private TextField txtValue;

    @FXML
    private Label lbl0;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl6;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl8;
    @FXML
    private Label lbl9;

    private Label [] cells;

    public int selectedValue = -1;

    private int input_width = 30;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cells = new Label[]{ lbl0, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9 };
        txtValue.setPrefColumnCount(1);
        txtValue.setPrefWidth(input_width);
        txtValue.setPrefHeight(input_width);
        txtValue.setMaxHeight(input_width);
        txtValue.setMaxWidth(input_width);

        //
        EventHandler<MouseEvent> valueClickListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label lbl = (Label) event.getSource();
                setSelectedValue(Integer.parseInt(lbl.getText()));
                changeSelectedCell(selectedValue);
                txtValue.setText(String.valueOf(selectedValue));
            }
        };

        for(int i = 0; i < cells.length; i++) {
            cells[i].setCursor(Cursor.HAND);
            cells[i].setOnMouseClicked(valueClickListener);
        }

    }

    private void changeSelectedCell(int selectedAnswer) {
        for(int i = 0; i < cells.length; i++) {
            if(cells[i].getText().equals(String.valueOf(selectedAnswer))) {
                cells[i].getStyleClass().removeIf(style -> style.equals("lbl-answer-item"));
                cells[i].getStyleClass().add("lbl-answer-item-selected");
            } else {
                cells[i].getStyleClass().add("lbl-answer-item");
                cells[i].getStyleClass().removeIf(style -> style.equals("lbl-answer-item-selected"));
            }
        }

        System.out.println(getTextInput() + " : " + getSelectedValue() + " selected cell : " + getSelectedCell());
    }

    public int getSelectedValue() {
        return this.selectedValue;
    }

    public void setSelectedValue(int i){
        this.selectedValue = i;
    }

    public void setTextInput(String value) {
        txtValue.setText(value);
    }
    public String getTextInput() {
        return txtValue.getText();
    }

    public boolean isCellSelected() {
        return selectedValue != -1;
    }

    private int getSelectedCell() {
        return isCellSelected() ? selectedValue : selectedValue;
    }

}
