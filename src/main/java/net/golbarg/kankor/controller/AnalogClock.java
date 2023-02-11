package net.golbarg.kankor.controller;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import net.golbarg.kankor.MainApp;

import java.util.Calendar;

public class AnalogClock extends Group {
    final int HOUR_HAND_LENGTH = 50;
    final int MINUTE_HAND_LENGTH = 75;
    final int SECOND_HAND_LENGTH = 88;
    final int SECOND_HAND_OFFSET = 15;

    public AnalogClock(String brandName, double clockRadius){
        setId("analogueClock");
        getStylesheets().add(AnalogClock.class.getResource("/net/golbarg/kankor/analogue-clock.css").toExternalForm());

        final Circle face = createClockFace(clockRadius);
        final Label brand = createBrand(face, brandName);
        final Line hourhand = createHand("hourHand", clockRadius, 0, percentOf(HOUR_HAND_LENGTH, clockRadius));
        final Line minuteHand = createHand("minuteHand", clockRadius, 0, percentOf(MINUTE_HAND_LENGTH, clockRadius));
        final Line secondHand = createHand("secondHand", clockRadius, percentOf(SECOND_HAND_OFFSET, clockRadius), percentOf(SECOND_HAND_LENGTH, clockRadius));

        //Animation the hands with the time
        bindClockHandsToTime(hourhand, minuteHand, secondHand);

        getChildren().addAll(face, brand, createTicks(clockRadius), createSpindle(clockRadius), hourhand, minuteHand, secondHand);
    }

    private Group createTicks(double clockRadius){
        final double TICK_START_OFFSET = percentOf(83, clockRadius);
        final double TICK_END_OFFSET = percentOf(93, clockRadius);


        final Group ticks = new Group();
        for (int i = 0; i < 12; i++) {
            Line tick = new Line(0, -TICK_START_OFFSET, 0, -TICK_END_OFFSET);
            tick.getStyleClass().add("tick");
            tick.setLayoutX(clockRadius);
            tick.setLayoutY(clockRadius);
            tick.getTransforms().add(new Rotate(i * (360/12)));
            ticks.getChildren().add(tick);
        }
        return ticks;
    }
    /**
     *
     * @param clockRadius
     * @return a rendered spindle around wich the clockwork rotate
     *
     */
    private Circle createSpindle(double clockRadius){
        final Circle spindle = new Circle(clockRadius, clockRadius, 5);
        spindle.setId("spindle");
        return spindle;
    }
    private Circle createClockFace(double clockRadius){
        final Circle face = new Circle(clockRadius, clockRadius, clockRadius);
        face.setId("face");
        return face;
    }
    private Line createHand(String handId, double clockRadius, double handOffsetLength, double handLength){
        final Line secondHand = new Line(0, handOffsetLength, 0, -handLength);
        secondHand.setLayoutX(clockRadius);
        secondHand.setLayoutY(clockRadius);
        secondHand.setId(handId);
        return secondHand;
    }
    private Label createBrand(Circle face, String brandName){
        final Label brand = new Label(brandName);
        brand.setId("brand");
        brand.layoutXProperty().bind(face.centerXProperty().subtract(brand.widthProperty().divide(2)));
        brand.layoutYProperty().bind(face.centerYProperty().subtract(brand.heightProperty().divide(2)));
        return brand;
    }

    private void bindClockHandsToTime(final Line hourHand, final Line minuteHand, final Line secondHand){
        Calendar time = Calendar.getInstance();

        final double initialHourhandDegrees = calculateHourHandDegrees(time);
        final double initialMinuteHandDegrees = calculateMinuteHandDegrees(time);
        final double initialSecondHandDegrees = calculateSecondHandDegrees(time);


        //animate the clock movement using timelines
        createRotationTimeline(createRotate(hourHand, initialHourhandDegrees).angleProperty(), Duration.hours(12), initialHourhandDegrees);
        createRotationTimeline(createRotate(minuteHand, initialMinuteHandDegrees).angleProperty(), Duration.minutes(60), initialMinuteHandDegrees);
        createRotationTimeline(createRotate(secondHand, initialSecondHandDegrees).angleProperty(), Duration.seconds(60), initialSecondHandDegrees);

    }

    private Rotate createRotate(Line hand, double initialHandDegrees){
        final Rotate hourRotate = new Rotate(initialHandDegrees);
        hand.getTransforms().add(hourRotate);
        return hourRotate;
    }

    /**
     * Performs a 360 degree rotation of the angleProperty once in every duration.
     * rotation starts from initialRotation degrees.
     */
    private void createRotationTimeline(DoubleProperty angleProperty, Duration duration, double initialRotation){
        Timeline timeline = new Timeline(
                new KeyFrame(duration, new KeyValue(angleProperty, 360 + initialRotation, Interpolator.LINEAR))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private int calculateSecondHandDegrees(Calendar time){
        return time.get(Calendar.SECOND) * (360/60);
    }
    private double calculateMinuteHandDegrees(Calendar time){
        return (time.get(Calendar.MINUTE) + calculateSecondHandDegrees(time) / 360.0) * (360 / 60);
    }

    private double calculateHourHandDegrees(Calendar time){
        return (time.get(Calendar.HOUR) + calculateMinuteHandDegrees(time) / 360.0) * (360/12);
    }

    private double percentOf(double percent, double clockRadius){
        return percent / 100 * clockRadius;
    }
}
