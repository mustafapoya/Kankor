package net.golbarg.kankor.model;

import java.time.LocalDate;

public class ExamResult {
    private int id;
    private Exam exam;
    private long examDuration;
    private double mathScore;
    private double naturalScore;
    private double socialScore;
    private double alsanaScore;
    private String passedField;

    public ExamResult(int id, Exam exam, long examDuration, double mathScore, double naturalScore,
                      double socialScore, double alsanaScore, String passedField) {
        this.id = id;
        this.exam = exam;
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
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

    public void setPassedField(String passedField) {
        this.passedField = passedField;
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "id=" + id +
                ", exam=" + exam +
                ", examDuration=" + examDuration +
                ", mathScore=" + mathScore +
                ", naturalScore=" + naturalScore +
                ", socialScore=" + socialScore +
                ", alsanaScore=" + alsanaScore +
                ", passedField='" + passedField + '\'' +
                '}';
    }
}
