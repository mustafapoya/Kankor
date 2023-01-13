package net.golbarg.kankor.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ColumnSelectionModel extends VBox implements EventHandler<MouseEvent> {
    ObservableList<Image> imgList = FXCollections.observableArrayList();
    ObservableList<Label> imageViewsList = FXCollections.observableArrayList();
    public int selectedCell;
    public TextField txtValue;

    public ColumnSelectionModel() {
        selectedCell = -1;
        initializeImages();
        initializeImageViews();
        txtValue = new TextField();
        txtValue.setPrefColumnCount(1);
        txtValue.setEditable(false);
        setTextFieldProperty(txtValue, 25, 25);

        getChildren().add(txtValue);

        for (int i = 0; i < imageViewsList.size(); i++) {
            getChildren().add(imageViewsList.get(i));
            imageViewsList.get(i).setCursor(Cursor.HAND);
            imageViewsList.get(i).setOnMouseClicked(this);
        }
        setAlignment(Pos.CENTER);
        setSpacing(3);
    }

    private void setTextFieldProperty(TextField txt, int width, int height) {
        txtValue.setPrefWidth(width);
        txtValue.setMaxWidth(width);
        txtValue.setPrefHeight(height);
        txtValue.setPrefWidth(height);
        txtValue.setAlignment(Pos.CENTER);
    }

    public void initializeImages(){
        imgList.add(new Image("img/exam/selection/0.png"));
        imgList.add(new Image("img/exam/selection/1.png"));
        imgList.add(new Image("img/exam/selection/2.png"));
        imgList.add(new Image("img/exam/selection/3.png"));
        imgList.add(new Image("img/exam/selection/4.png"));
        imgList.add(new Image("img/exam/selection/5.png"));
        imgList.add(new Image("img/exam/selection/6.png"));
        imgList.add(new Image("img/exam/selection/7.png"));
        imgList.add(new Image("img/exam/selection/8.png"));
        imgList.add(new Image("img/exam/selection/9.png"));
    }

    public void initializeImageViews(){
        for (int i = 0; i < imgList.size(); i++) {
            imageViewsList.add(new Label(Integer.toString(i)));
            imageViewsList.get(i).setGraphic(new ImageView(imgList.get(i)));
            imageViewsList.get(i).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    @Override
    public void handle(MouseEvent event) {
        Label lbl = (Label)event.getSource();
        selectedCell = Integer.parseInt(lbl.getText());
        changeSelectedCell(selectedCell);
        txtValue.setText(Integer.toString(selectedCell));
    }

    public void changeSelectedCell(int index){
        imageViewsList.get(index).setGraphic(new ImageView(new Image("img/exam/selection/select.png")));

        for (int i = 0; i < imageViewsList.size(); i++) {
            if(i == index){
                continue;
            }
            imageViewsList.get(i).setGraphic(new ImageView(imgList.get(i)));
        }
    }
    public int getSelectedCellValue(){
        return selectedCell;
    }

    public TextField getTextField(){
        return txtValue;
    }

    public boolean isCellSelected(){
        if(selectedCell == -1){
            return false;
        }else{
            return true;
        }
    }

}

