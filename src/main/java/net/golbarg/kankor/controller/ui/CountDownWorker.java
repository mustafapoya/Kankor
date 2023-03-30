package net.golbarg.kankor.controller.ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;

import java.time.Duration;
import java.time.LocalDateTime;

public class CountDownWorker extends Task<Void> {
    private BooleanProperty stop = new SimpleBooleanProperty(false);
    private LocalDateTime startDateTime;
    private LocalDateTime stopDateTime;
    private BooleanProperty pause = new SimpleBooleanProperty(false);
    private Duration duration;
    private int seconds;
    private boolean displayMilliSeconds;
    public CountDownWorker(int seconds, boolean displayMilliSeconds) {
        this.seconds = seconds;
        this.displayMilliSeconds = displayMilliSeconds;
    }

    public CountDownWorker() {
        this(5400, false);
    }

    @Override
    protected Void call() throws Exception {
        startDateTime = LocalDateTime.now();
        stopDateTime = LocalDateTime.now().plusSeconds(seconds);
        CharSequence s = "00:00:00";

        while(!stop.getValue() || !startDateTime.equals(LocalDateTime.parse(s))) {
            if(!pause.getValue()) {
                startDateTime = LocalDateTime.now();
                duration = Duration.between(startDateTime, stopDateTime);

                long hours = Math.max(0, duration.toHours());
                long minutes = Math.max(0, duration.toMinutes() - 60 * duration.toHours());
                long seconds = Math.max(0, duration.getSeconds() - 60 * duration.toMinutes());
                long millis = Math.max(0, duration.toMillis() - duration.getSeconds() * 1000);


                String message = String.format("%02d", hours) + ":" +
                                String.format("%02d", minutes) + ":" +
                                String.format("%02d", seconds) +
                                (displayMilliSeconds ? ":" + String.format("%03d", millis) : "");

                updateMessage(message);

                if(duration.getSeconds() == 0){
                    millis = 0;
                    updateMessage(message);
                    stop();
                }

                Thread.sleep(3);

            }
        }

        return null;
    }

    public boolean getPause() {
        return pause.getValue();
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public boolean getStop() {
        return stop.getValue();
    }

    public LocalDateTime getStopDateTime() {
        return stopDateTime;
    }

    public BooleanProperty pauseProperty() {
        return this.pause;
    }

    public void setPause(boolean pause) {
        this.pause.setValue(pause);
    }

    public void setStop(boolean stop) {
        this.stop.setValue(stop);
    }

    public void stop() {
        setStop(true);
    }

    public BooleanProperty stopProperty() {
        return this.stop;
    }

    public Duration getDuration() {
        return duration;
    }
}
