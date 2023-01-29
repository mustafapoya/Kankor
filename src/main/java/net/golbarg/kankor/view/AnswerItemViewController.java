package net.golbarg.kankor.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

public class AnswerItemViewController implements Initializable {
    @FXML
    private HBox root;

    @FXML
    private Label lblItemNumber;

    @FXML
    private Label lblAnswer1;
    @FXML
    private Label lblAnswer2;
    @FXML
    private Label lblAnswer3;
    @FXML
    private Label lblAnswer4;

    private Label [] answers;

    public int selectedAnswer = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        answers = new Label[]{ lblAnswer1, lblAnswer2, lblAnswer3, lblAnswer4 };

        EventHandler<MouseEvent> answerClickListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label lbl = (Label) event.getSource();
                setSelectedAnswer(Integer.parseInt(lbl.getText()));
                changeSelectedCell(selectedAnswer);
            }
        };

        for(int i = 0; i < answers.length; i++) {
            answers[i].setCursor(Cursor.HAND);
            answers[i].setOnMouseClicked(answerClickListener);
        }
    }

    private void changeSelectedCell(int selectedAnswer) {
        for(int i = 0; i < answers.length; i++) {
            if(answers[i].getText().equals(String.valueOf(selectedAnswer))) {
                answers[i].getStyleClass().removeIf(style -> style.equals("lbl-answer-item"));
                answers[i].getStyleClass().add("lbl-answer-item-selected");
            } else {
                answers[i].getStyleClass().add("lbl-answer-item");
                answers[i].getStyleClass().removeIf(style -> style.equals("lbl-answer-item-selected"));
            }
        }

         System.out.println(getItemNumber() + " : " + getSelectedAnswer() + " selected cell : " + getSelectedCell());
    }

    public int getSelectedAnswer() {
        return this.selectedAnswer;
    }

    public void setSelectedAnswer(int i){
        this.selectedAnswer = i;
    }

    public void setItemNumber(int number) {
        lblItemNumber.setText(String.valueOf(number));
    }
    public int getItemNumber() {
        return Integer.valueOf(lblItemNumber.getText());
    }

    public boolean isCellSelected() {
        return selectedAnswer != -1;
    }

    private int getSelectedCell() {
        return isCellSelected() ? selectedAnswer - 1 : selectedAnswer;
    }
}
