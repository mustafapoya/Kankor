package net.golbarg.kankor.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ProgressBar;

public class Download {
    private SimpleIntegerProperty id;
    private SimpleStringProperty url;
    private SimpleStringProperty fileName;
    private SimpleStringProperty fileSize;
    private SimpleStringProperty state;
    private SimpleStringProperty status;

    public Download(SimpleIntegerProperty id, SimpleStringProperty url, SimpleStringProperty fileName, SimpleStringProperty fileSize, SimpleStringProperty state, SimpleStringProperty status) {
        this.id = id;
        this.url = url;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.state = state;
        this.status = status;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUrl() {
        return url.get();
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public String getFileSize() {
        return fileSize.get();
    }

    public SimpleStringProperty fileSizeProperty() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize.set(fileSize);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
