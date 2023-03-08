package net.golbarg.kankor.model;

import java.util.Date;

public class Exam {
    private int id;
    private int userId;
    private String kankorId;
    private Date examDate;
    private long examDuration;
    private double mathGrade;
    private double naturalGrade;
    private double socialGrade;
    private double alsanaGrade;
    private double examGrade;
    private String examPassedField;

    public Exam(int id, int userId, String kankorId, Date examDate, long examDuration, double mathGrade,
                double naturalGrade, double socialGrade, double alsanaGrade, double examGrade, String examPassedField) {
        this.id = id;
        this.userId = userId;
        this.kankorId = kankorId;
        this.examDate = examDate;
        this.examDuration = examDuration;
        this.mathGrade = mathGrade;
        this.naturalGrade = naturalGrade;
        this.socialGrade = socialGrade;
        this.alsanaGrade = alsanaGrade;
        this.examGrade = examGrade;
        this.examPassedField = examPassedField;
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

    public String getKankorId() {
        return kankorId;
    }

    public void setKankorId(String kankorId) {
        this.kankorId = kankorId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public long getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(long examDuration) {
        this.examDuration = examDuration;
    }

    public double getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(double mathGrade) {
        this.mathGrade = mathGrade;
    }

    public double getNaturalGrade() {
        return naturalGrade;
    }

    public void setNaturalGrade(double naturalGrade) {
        this.naturalGrade = naturalGrade;
    }

    public double getSocialGrade() {
        return socialGrade;
    }

    public void setSocialGrade(double socialGrade) {
        this.socialGrade = socialGrade;
    }

    public double getAlsanaGrade() {
        return alsanaGrade;
    }

    public void setAlsanaGrade(double alsanaGrade) {
        this.alsanaGrade = alsanaGrade;
    }

    public double getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(double examGrade) {
        this.examGrade = examGrade;
    }

    public String getExamPassedField() {
        return examPassedField;
    }

    public void setExamPassedField(String examPassedField) {
        this.examPassedField = examPassedField;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", userId=" + userId +
                ", kankorId='" + kankorId + '\'' +
                ", examDate=" + examDate +
                ", examDuration=" + examDuration +
                ", mathGrade=" + mathGrade +
                ", naturalGrade=" + naturalGrade +
                ", socialGrade=" + socialGrade +
                ", alsanaGrade=" + alsanaGrade +
                ", examGrade=" + examGrade +
                ", examPassedField='" + examPassedField + '\'' +
                '}';
    }
}
