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
    private int mathCorrect, naturalCorrect, socialCorrect, alsanaCorrect, totalCorrect;
    private QuestionGenerator questionGenerator;

    public ExamController() {
        questionGenerator = new QuestionGenerator();
    }

    public ExamController(int math, int natural, int social, int alsana) {
        questionGenerator = new QuestionGenerator(math, natural, social, alsana);
    }

    public void generateQuestion() {
        mathList = questionGenerator.getMathQuestions();
        naturalList = questionGenerator.getNaturalQuestion();
        socialList = questionGenerator.getSocialQuestion();
        alsanaList = questionGenerator.getAlsanaQuestion();
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

    public void checkAnswers(AnswerSheetViewController answers, ObservableList<Question> questions) {
        mathCorrect = 0;
        socialCorrect = 0;
        naturalCorrect = 0;
        alsanaCorrect = 0;
        totalCorrect = 0;

        for (int i = 0; i < questions.size(); i++) {
            int selectedAnswer = answers.getRowList().get(i).getSelectedAnswer();
            int correctAnswer = questions.get(i).getCorrectChoice();

            if (selectedAnswer == correctAnswer) {
                totalCorrect++;
                System.out.println("correct");
                checkSubjectType(questions.get(i).getSubjectName());
                System.out.println("Math Correct -> " + mathCorrect);
                System.out.println("Social Correct -> " + socialCorrect);
                System.out.println("Natural Correct -> " + naturalCorrect);
                System.out.println("Alsana Correct -> " + alsanaCorrect);
            }
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

    public int getMathCorrect() {
        return mathCorrect;
    }

    public int getAlsanaCorrect() {
        return alsanaCorrect;
    }

    public int getNaturalCorrect() {
        return naturalCorrect;
    }

    public int getSocialCorrect() {
        return socialCorrect;
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
                mathCorrect++;
                break;
            // Natural
            case "chemistry":
            case "physic":
            case "biology":
                naturalCorrect++;
                break;
            // Social
            case "islamic":
            case "history":
            case "geography":
                socialCorrect++;
                break;
            // Alsana
            case "dari":
            case "pashto":
            case "general":
                alsanaCorrect++;
                break;
            default:
                break;
        }
    }

    public double getKankorScore() {
        return totalCorrect * 2;
    }

    public QuestionGenerator getQuestionGenerator() {
        return questionGenerator;
    }

    @Override
    public String toString() {
        return "ExamController{" +
                "mathCorrect=" + mathCorrect +
                ", naturalCorrect=" + naturalCorrect +
                ", socialCorrect=" + socialCorrect +
                ", alsanaCorrect=" + alsanaCorrect +
                ", totalCorrect=" + totalCorrect +
                '}';
    }
}
