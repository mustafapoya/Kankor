package net.golbarg.kankor.model;

public class ExamResult {
    private int id;
    private double score;
    private int correctAnswer;
    private int wrongAnswer;
    private String result;
    private String duration;

    public ExamResult(int id, double score, int correctAnswer, int wrongAnswer, String result, String duration) {
        this.id = id;
        this.score = score;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.result = result;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "id=" + id +
                ", score=" + score +
                ", correctAnswer=" + correctAnswer +
                ", wrongAnswer=" + wrongAnswer +
                ", result='" + result + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
