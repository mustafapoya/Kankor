package net.golbarg.kankor.model;


import java.time.LocalDateTime;

public class News {
    private int id;
    private String category;
    private String title;
    private String description;
    private String urlLink;
    private String content;
    private LocalDateTime newsDate;

    public News(int id, String category, String title, String description, String urlLink,
                String content, LocalDateTime newsDate) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.urlLink = urlLink;
        this.content = content;
        this.newsDate = newsDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(LocalDateTime newsDate) {
        this.newsDate = newsDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", urlLink='" + urlLink + '\'' +
                ", content='" + content + '\'' +
                ", newsDate=" + newsDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof News) {
            return this.id == ((News) obj).id;
        }
        return false;
    }
}
