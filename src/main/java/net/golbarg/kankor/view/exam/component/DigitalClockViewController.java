package net.golbarg.kankor.view.exam.component;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import net.golbarg.kankor.controller.Util;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DigitalClockViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private Label lblClockTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindToTime();
    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Calendar time = Calendar.getInstance();
                        String hourString = Util.pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                        String minuteString = Util.pad(2, '0', time.get(Calendar.MINUTE) + "");
                        String secondString = Util.pad(2, '0', time.get(Calendar.SECOND) + "");
                        String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                        lblClockTime.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
                    }
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
