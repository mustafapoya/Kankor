package net.golbarg.kankor.model;

import java.time.LocalDate;

public class ExamResult {
    private int id;
    private Exam exam;
    private long examDuration;
    private ExamCorrectAnswerCount correctAnswerCount;
    private String passedField;

    public ExamResult(int id, Exam exam, long examDuration, ExamCorrectAnswerCount correctAnswerCount, String passedField) {
        this.id = id;
        this.exam = exam;
        this.examDuration = examDuration;
        this.correctAnswerCount = correctAnswerCount;
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

    public ExamCorrectAnswerCount getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(ExamCorrectAnswerCount correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
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
                ", correctAnswerCount=" + correctAnswerCount +
                ", passedField='" + passedField + '\'' +
                '}';
    }
}
