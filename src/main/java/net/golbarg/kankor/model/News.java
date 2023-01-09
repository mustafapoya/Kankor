package net.golbarg.kankor.model;

import java.util.Date;

public class News {
    private int id;
    private String title;
    private String content;
    private Date date;
    private String url;
    private String description;

    public News(int id, String title, String content, Date date, String url, String description) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.url = url;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
