package net.golbarg.kankor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.Question;

public class QuestionGenerator {
    private Exam exam;

    public static final int[] MATHEMATICS = { 9, 12, 6 };
    public static final int[] NATURALS = { 2, 11, 1 };
    public static final int[] SOCIALS = { 8, 7, 5 };
    public static final int[] ALSANA = { 3, 10, 4 };

    public QuestionGenerator() {
//        setNumberOfQuestions(46, 40, 38, 36);
        exam = SystemController.DEFAULT_EXAM;
    }

    public QuestionGenerator(Exam exam) {
        this.exam = exam;
    }

    public ObservableList<Question> getMathQuestions() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(MATHEMATICS, exam.getMathCount()));

        return questions;
    }

    public ObservableList<Question> getNaturalQuestion() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(NATURALS, exam.getNaturalCount()));

        return questions;
    }

    public ObservableList<Question> getSocialQuestion() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(SOCIALS, exam.getSocialCount()));

        return questions;
    }

    public ObservableList<Question> getAlsanaQuestion() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(ALSANA, exam.getAlsanaCount()));

        return questions;
    }

    public Exam getExam() {
        return exam;
    }

    @Override
    public String toString() {
        return "QuestionGenerator{" +
                "exam=" + exam +
                '}';
    }
}
