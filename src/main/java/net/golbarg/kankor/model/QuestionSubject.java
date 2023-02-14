package net.golbarg.kankor.model;

public class QuestionSubject {
    private int id;
    private String type;
    private String key;
    private String titlePersian;

    public QuestionSubject(int id, String type, String key, String titlePersian) {
        this.id = id;
        this.type = type;
        this.key = key;
        this.titlePersian = titlePersian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitlePersian() {
        return titlePersian;
    }

    public void setTitlePersian(String titlePersian) {
        this.titlePersian = titlePersian;
    }

    @Override
    public String toString() {
        return "QuestionSubject{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", titlePersian='" + titlePersian + '\'' +
                '}';
    }
}
