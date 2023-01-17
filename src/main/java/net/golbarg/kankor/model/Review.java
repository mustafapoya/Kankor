package net.golbarg.kankor.model;

public class Review {
    private int id;
    private int questionId;
    private String subjectName;

    public Review(int id, int questionId, String subjectName) {
        this.id = id;
        this.questionId = questionId;
        this.subjectName = subjectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
