package net.golbarg.kankor.model;

public class Question {
    private int id;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int correctChoice;
    private String relatedClass;
    private String subjectName;
    private String questionType;
    private int questionUpdate;

    public Question(int id, String question, String choice1, String choice2, String choice3, String choice4,
                    int correctChoice, String relatedClass, String subjectName, String questionType, int questionUpdate) {
        this.id = id;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctChoice = correctChoice;
        this.relatedClass = relatedClass;
        this.subjectName = subjectName;
        this.questionType = questionType;
        this.questionUpdate = questionUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }

    public String getRelatedClass() {
        return relatedClass;
    }

    public void setRelatedClass(String relatedClass) {
        this.relatedClass = relatedClass;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getQuestionUpdate() {
        return questionUpdate;
    }

    public void setQuestionUpdate(int questionUpdate) {
        this.questionUpdate = questionUpdate;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", choice1='" + choice1 + '\'' +
                ", choice2='" + choice2 + '\'' +
                ", choice3='" + choice3 + '\'' +
                ", choice4='" + choice4 + '\'' +
                ", correctChoice=" + correctChoice +
                ", relatedClass='" + relatedClass + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionUpdate=" + questionUpdate +
                '}';
    }
}
