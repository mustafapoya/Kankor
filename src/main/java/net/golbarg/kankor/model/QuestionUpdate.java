package net.golbarg.kankor.model;

public class QuestionUpdate {
    private int id;
    private String text;
    private String description;

    public QuestionUpdate(int id, String text, String description) {
        this.id = id;
        this.text = text;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "QuestionUpdate{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
