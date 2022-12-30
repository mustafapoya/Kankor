package net.golbarg.kankor.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CharacterView extends HBox {
    ObservableList<TextField> cells = FXCollections.observableArrayList();
    public SimpleStringProperty text = new SimpleStringProperty();
    private int numberOfColumns = 12;

    public CharacterView(){
        this("");
    }

    public CharacterView(String text) {
        this.text.set(text);
        createColumns();
        disableCells();
        setColumnValues();
    }

    public void setColumnValues() {
        RemoveSpaces(this.text.get());
        for (int i = 0; i < text.get().length() && i < numberOfColumns; i++) {
            cells.get(i).setText(Character.toString(text.get().charAt(i)));
        }
    }

    public void createColumns() {
        this.getChildren().clear();

        for (int i = 0; i < numberOfColumns; i++) {
            TextField textField = new TextField();
            textField.setPrefColumnCount(1);
            cells.add(textField);
            this.getChildren().add(cells.get(i));
        }
    }

    public void disableCells() {
        for (int i = 0; i < numberOfColumns; i++) {
            cells.get(i).setEditable(false);
        }
    }

    public void initializeText(String text){
        this.text.set(text);
        RemoveSpaces(this.text.get());
        setColumnValues();
    }

    public void updateText(String text){
        RemoveSpaces(text);
        int i = 0;
        for (i = 0; i < text.length() && i < numberOfColumns; i++) {
            cells.get(i).setText(Character.toString(this.text.get().charAt(i)));
        }

        for(;i < 12;i++){
            cells.get(i).setText("");
        }
    }

    public String cleanText(String text){
        String temp = "";
        for (int i = 0;  i < text.length() && i < numberOfColumns; i++) {
            if(Character.isLetter(text.charAt(i))){
                temp += text.charAt(i);
            }
        }
        return temp;
    }

    private void RemoveSpaces(String text) {
        String temp = "";
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                temp += text.charAt(i);
            }
        }
        this.text.set(temp);
    }

}
