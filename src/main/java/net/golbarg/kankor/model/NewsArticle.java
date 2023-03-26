package net.golbarg.kankor.model;


import java.time.LocalDate;

public class NewsArticle {
    private int id;
    private String title;
    private String content;
    private String urlLink;
    private LocalDate date;
    private String description;
    private int category;

    public NewsArticle(int id, String title, String content, String urlLink, LocalDate date, String description, int category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.urlLink = urlLink;
        this.date = date;
        this.description = description;
        this.category = category;
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

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", urlLink='" + urlLink + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
