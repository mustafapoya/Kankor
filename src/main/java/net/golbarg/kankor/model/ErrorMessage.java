package net.golbarg.kankor.model;

public class ErrorMessage {
    private String title;
    private String message;

    public ErrorMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
