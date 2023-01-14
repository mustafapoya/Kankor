package net.golbarg.kankor.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.util.Comparator;

public class FieldSelection extends HBox implements Comparator<FieldSelection> {
    ObservableList<ColumnSelectionModel> columnList = FXCollections.observableArrayList();
    public String facultyCode = "";
    public FieldSelection(){
        initializeList(3);
        for (int i = 0; i < columnList.size(); i++) {
            getChildren().add(columnList.get(i));
        }
        setSpacing(5);
        setAlignment(Pos.CENTER);
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(getSelectedFieldValue());
            }
        });
    }

    public void initializeList(int count){
        for (int i = 0; i < count; i++) {
            columnList.add(new ColumnSelectionModel());
        }
    }
    public void setFacultycode(){
        facultyCode = getSelectedFieldValue();
    }
    public String getFacultyCode() {
        return facultyCode;
    }
    public String getSelectedFieldValue(){
        String temp = "";
        for (int i = 0; i < columnList.size(); i++) {
            temp += columnList.get(i).getSelectedCellValue();
        }
        return reverse(temp);
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
            if(columnList.get(i).getSelectedCellValue() == -1){
                return false;
            }
        }
        return true;
    }
    public boolean isValidCode(){
        if(getSelectedFieldValue().contains("-")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int compare(FieldSelection o1, FieldSelection o2) {
        if(o1.getSelectedFieldValue().equalsIgnoreCase(o2.getSelectedFieldValue())){
            return 0;
        }
        return -1;
    }
    public boolean equals(FieldSelection field) {
        if(this.getSelectedFieldValue().equalsIgnoreCase(field.getSelectedFieldValue())){
            return true;
        }else{
            return false;
        }
    }
}
