package net.golbarg.kankor.controller.ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;

import java.time.Duration;
import java.time.LocalDateTime;

public class StopWatchWorker extends Task<Void> {
    private BooleanProperty stop = new SimpleBooleanProperty(false);
    private LocalDateTime startDateTime;
    private LocalDateTime stopDateTime;
    private BooleanProperty pause = new SimpleBooleanProperty(false);
    private Duration duration;
    private boolean displayMilliSeconds;

    public StopWatchWorker(boolean displayMilliSeconds) {
        this.displayMilliSeconds = displayMilliSeconds;
    }

    public StopWatchWorker() {
        this(false);
    }

    @Override
    protected Void call() throws Exception {

        startDateTime = LocalDateTime.now();

        while(!stop.getValue()){
            if(!pause.getValue()){
                stopDateTime = LocalDateTime.now();
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
                Thread.sleep(3);
            }
        }
        return null;
    }

    public boolean getPause(){
        return pause.getValue();
    }

    public LocalDateTime getStartDateTime(){
        return startDateTime;
    }

    public boolean getStop(){
        return stop.getValue();
    }

    public LocalDateTime getStopDateTime(){
        return stopDateTime;
    }

    public BooleanProperty pauseProperty(){
        return this.pause;
    }

    public void setPause(boolean pause){
        this.pause.setValue(pause);
    }

    public void setStop(boolean stop){
        this.stop.setValue(stop);
    }

    public void stop(){
        setStop(true);
    }

    public BooleanProperty stopProperty(){
        return this.stop;
    }

    public Duration getDuration(){
        return duration;
    }
}
