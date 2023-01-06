package net.golbarg.kankor.model;

public class TutorialDetail {
    private int id;
    private int tutorialId;
    private String title;
    private String shortDescription;
    private String fullDescription;
    private String shortDescriptionIcon;
    private String icon;

    public TutorialDetail(int id, int tutorialId, String title, String shortDescription, String fullDescription, String shortDescriptionIcon, String icon) {
        this.id = id;
        this.tutorialId = tutorialId;
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.shortDescriptionIcon = shortDescriptionIcon;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTutorialId() {
        return tutorialId;
    }

    public void setTutorialId(int tutorialId) {
        this.tutorialId = tutorialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getShortDescriptionIcon() {
        return shortDescriptionIcon;
    }

    public void setShortDescriptionIcon(String shortDescriptionIcon) {
        this.shortDescriptionIcon = shortDescriptionIcon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "TutorialDetail{" +
                "id=" + id +
                ", tutorialId=" + tutorialId +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", shortDescriptionIcon='" + shortDescriptionIcon + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
