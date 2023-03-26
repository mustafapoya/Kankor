package net.golbarg.kankor.model;

import java.time.LocalDate;

public class Exam {
    private int id;
    private int userId;
    private User user;
    private LocalDate examDate;
    private long examDuration;
    private double mathScore;
    private double naturalScore;
    private double socialScore;
    private double alsanaScore;
    private String passedField;

    public Exam(int id, int userId, LocalDate examDate, long examDuration, double mathScore, double naturalScore, double socialScore, double alsanaScore, String passedField) {
        this.id = id;
        this.userId = userId;
        this.examDate = examDate;
        this.examDuration = examDuration;
        this.mathScore = mathScore;
        this.naturalScore = naturalScore;
        this.socialScore = socialScore;
        this.alsanaScore = alsanaScore;
        this.passedField = passedField;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public long getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(long examDuration) {
        this.examDuration = examDuration;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public double getNaturalScore() {
        return naturalScore;
    }

    public void setNaturalScore(double naturalScore) {
        this.naturalScore = naturalScore;
    }

    public double getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(double socialScore) {
        this.socialScore = socialScore;
    }

    public double getAlsanaScore() {
        return alsanaScore;
    }

    public void setAlsanaScore(double alsanaScore) {
        this.alsanaScore = alsanaScore;
    }

    public double getTotalScore() {
        return getMathScore() + getNaturalScore() + getSocialScore() + getAlsanaScore();
    }

    public String getPassedField() {
        return passedField;
    }

    public void setPassedField(String examPassedField) {
        this.passedField = examPassedField;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", userId=" + userId +
                ", examDate=" + examDate +
                ", examDuration=" + examDuration +
                ", mathScore=" + mathScore +
                ", naturalScore=" + naturalScore +
                ", socialScore=" + socialScore +
                ", alsanaScore=" + alsanaScore +
                ", passedField='" + passedField + '\'' +
                '}';
    }
}
