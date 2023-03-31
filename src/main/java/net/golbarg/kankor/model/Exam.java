package net.golbarg.kankor.model;

import java.time.LocalDate;

public class Exam {
    private int id;
    private User user;
    private LocalDate date;
    private int mathCount;
    private int naturalCount;
    private int socialCount;
    private int alsanaCount;

    public Exam(int id, User user, LocalDate date, int mathCount, int naturalCount,
                int socialCount, int alsanaCount) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.mathCount = mathCount;
        this.naturalCount = naturalCount;
        this.socialCount = socialCount;
        this.alsanaCount = alsanaCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMathCount() {
        return mathCount;
    }

    public void setMathCount(int mathCount) {
        this.mathCount = mathCount;
    }

    public int getNaturalCount() {
        return naturalCount;
    }

    public void setNaturalCount(int naturalCount) {
        this.naturalCount = naturalCount;
    }

    public int getSocialCount() {
        return socialCount;
    }

    public void setSocialCount(int socialCount) {
        this.socialCount = socialCount;
    }

    public int getAlsanaCount() {
        return alsanaCount;
    }

    public void setAlsanaCount(int alsanaCount) {
        this.alsanaCount = alsanaCount;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", mathCount=" + mathCount +
                ", naturalCount=" + naturalCount +
                ", socialCount=" + socialCount +
                ", alsanaCount=" + alsanaCount +
                '}';
    }
}
