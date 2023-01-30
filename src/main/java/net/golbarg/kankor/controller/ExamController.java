package net.golbarg.kankor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.golbarg.kankor.db.TableFaculty;
import net.golbarg.kankor.model.Faculty;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.view.AnswerSheetViewController;
import net.golbarg.kankor.view.FieldSelectionViewController;

public class ExamController {
    private ObservableList<Question> mathList = FXCollections.observableArrayList();
    private ObservableList<Question> naturalList = FXCollections.observableArrayList();
    private ObservableList<Question> socialList = FXCollections.observableArrayList();
    private ObservableList<Question> alsanaList = FXCollections.observableArrayList();
    private int math, natural, social, alsana, totalCorrect;
    ObservableList<Question> questions = FXCollections.observableArrayList();

    AnswerSheetViewController answerSheet;
    QuestionGenerator generator;

    public ExamController() {
        generator = new QuestionGenerator();
    }

    public void generateQuestion() {
        mathList = generator.getMathQuestions();
        naturalList = generator.getNaturalQuestion();
        socialList = generator.getSocialQuestion();
        alsanaList = generator.getAlsanaQuestion();
    }

    public ObservableList<Question> getMathList() {
        return mathList;
    }

    public ObservableList<Question> getNaturalList() {
        return naturalList;
    }

    public ObservableList<Question> getSocialList() {
        return socialList;
    }

    public ObservableList<Question> getAlsanaList() {
        return alsanaList;
    }

    public void compare(AnswerSheetViewController answers, ObservableList<Question> questions) {
        this.answerSheet = answers;
        this.questions = questions;
        math = 0;
        social = 0;
        natural = 0;
        alsana = 0;
        totalCorrect = 0;
        for (int i = 0; i < questions.size(); i++) {
            int selectedAnswer = answers.getRowList().get(i).getSelectedAnswer();
            int correctAnswer = questions.get(i).getCorrectChoice();

            if (selectedAnswer == correctAnswer) {
                totalCorrect++;
                System.out.println("correct");
                checkSubjectType(questions.get(i).getSubjectName());
                System.out.println("Math Correct -> " + math);
                System.out.println("Social Correct -> " + social);
                System.out.println("Natural Correct -> " + natural);
                System.out.println("Alsana Correct -> " + alsana);
            }
//			System.out.println("my selection -> " + answers.getRows().get(i).getSelectedCellValue() + 1);
        }
    }

    public ObservableList<Faculty> getUniversity(ObservableList<FieldSelectionViewController> fields) {
        String [] codes = new String[5];

        for (int i = 0; i < 5; i++) {
            codes[i] = fields.get(i).getSelectedFieldValue();
        }

        TableFaculty tableFaculty = new TableFaculty();

        ObservableList<Faculty> universityList = FXCollections.observableArrayList();

        universityList.addAll(tableFaculty.getFacultiesByCode(codes));

        return universityList;
    }

    public Faculty getPassedField(ObservableList<Faculty> list, double score) {
        for (int i = 0; i < list.size(); i++) {
            if (score > list.get(i).getAdmission()) {
                return list.get(i);
            }
        }
        return null;
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
    public int getTotalCorrect() {
        return totalCorrect;
    }

    private void checkSubjectType(String value) {
        switch (value) {
            // Mathematic
            case "math":
            case "triangles":
            case "geometry":
                math++;
                break;
            // Natural
            case "chemistry":
            case "physic":
            case "biology":
                natural++;
                break;
            // Social
            case "islamic":
            case "history":
            case "geography":
                social++;
                break;
            // Alsana
            case "dari":
            case "pashto":
            case "general":
                alsana++;
                break;
            default:
                break;
        }
    }

    public AnswerSheetViewController getAnswerSheet() {
        return answerSheet;
    }

    public double getKankorScore() {
        return totalCorrect * 2;
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }
}
