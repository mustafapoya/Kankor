package net.golbarg.kankor.model;

public class Update {
    private int id;
    private String content;
    private String description;

    public Update(int id, String content, String description) {
        this.id = id;
        this.content = content;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Update{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
