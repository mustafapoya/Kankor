package net.golbarg.kankor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.golbarg.kankor.db.TableExam;
import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.Question;

public class QuestionGenerator {
    public static final int KANKOR_TOTAL_QUESTIONS = 160;
    private int math;
    private int natural;
    private int social;
    private int alsana;

    public static final String[] MATHEMATICS = { "math", "triangles", "geometry" };
    public static final String[] NATURALS = { "chemistry", "physic", "biology" };
    public static final String[] SOCIALS = { "islamic", "history", "geography" };
    public static final String[] ALSANA = { "dari", "pashto", "general" };

    public QuestionGenerator() {
        setNumberOfQuestions(46, 40, 38, 36);
    }

    public QuestionGenerator(int math, int natural, int social, int alsana) {
        setNumberOfQuestions(math, natural, social, alsana);
    }

    private void setNumberOfQuestions(int math, int natural, int social, int alsana) {
        if (math + natural + social + alsana <= 160) {
            this.math = math;
            this.natural = natural;
            this.social = social;
            this.alsana = alsana;
        } else {
            this.math = 47;
            this.natural = 41;
            this.social = 38;
            this.alsana = 35;
        }
    }

    public ObservableList<Question> getMathQuestions() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(MATHEMATICS, math));

        return questions;
    }

    public ObservableList<Question> getNaturalQuestion() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(NATURALS, natural));

        return questions;
    }

    public ObservableList<Question> getSocialQuestion() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(SOCIALS, social));

        return questions;
    }

    public ObservableList<Question> getAlsanaQuestion() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        TableQuestion tableQuestion = new TableQuestion();
        questions.addAll(tableQuestion.getQuestionsOf(ALSANA, alsana));

        return questions;
    }

    public static ObservableList<Exam> getExamResults() {
        ObservableList<Exam> examResults = FXCollections.observableArrayList();

        TableExam tableExam = new TableExam();
        examResults.addAll(tableExam.getAll());

        return examResults;
    }

    public int getMath() {
        return math;
    }

    public int getAlsana() {
        return alsana;
    }

    public int getNatural() {
        return natural;
    }

    public int getSocial() {
        return social;
    }

    public int getTotalQuestion() {
        return getMath() + getNatural() + getSocial() + getAlsana();
    }

    @Override
    public String toString() {
        return "QuestionGenerator{" +
                "math=" + math +
                ", natural=" + natural +
                ", social=" + social +
                ", alsana=" + alsana +
                '}';
    }
}
