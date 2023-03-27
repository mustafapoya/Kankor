package net.golbarg.kankor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.golbarg.kankor.db.TableUniversityFaculty;
import net.golbarg.kankor.db.TableQuestionSubject;
import net.golbarg.kankor.model.UniversityFaculty;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.QuestionSubject;
import net.golbarg.kankor.view.exam.component.AnswerSheetViewController;
import net.golbarg.kankor.view.exam.component.FieldSelectionViewController;

import java.util.ArrayList;

public class ExamController {
    private ObservableList<Question> mathList = FXCollections.observableArrayList();
    private ObservableList<Question> naturalList = FXCollections.observableArrayList();
    private ObservableList<Question> socialList = FXCollections.observableArrayList();
    private ObservableList<Question> alsanaList = FXCollections.observableArrayList();
    private int mathCorrect, naturalCorrect, socialCorrect, alsanaCorrect, totalCorrect;
    private QuestionGenerator questionGenerator;
    private ArrayList<QuestionSubject> subjects = new ArrayList<>();

    public ExamController() {
        questionGenerator = new QuestionGenerator();
        subjects = new TableQuestionSubject().getAll();
    }

    public ExamController(int math, int natural, int social, int alsana) {
        questionGenerator = new QuestionGenerator(math, natural, social, alsana);
        subjects = new TableQuestionSubject().getAll();
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
                checkSubjectType(questions.get(i).getSubject().getId());
                System.out.println("Math Correct -> " + mathCorrect);
                System.out.println("Social Correct -> " + socialCorrect);
                System.out.println("Natural Correct -> " + naturalCorrect);
                System.out.println("Alsana Correct -> " + alsanaCorrect);
            }
        }
    }

    public ObservableList<UniversityFaculty> getUniversity(ObservableList<FieldSelectionViewController> fields) {
        String [] codes = new String[5];

        for (int i = 0; i < 5; i++) {
            codes[i] = fields.get(i).getSelectedFieldValue();
        }

        TableUniversityFaculty tableUniversityFaculty = new TableUniversityFaculty();

        ObservableList<UniversityFaculty> universityList = FXCollections.observableArrayList();

        universityList.addAll(tableUniversityFaculty.getFacultiesByCode(codes));

        return universityList;
    }

    public UniversityFaculty getPassedField(ObservableList<UniversityFaculty> list, double score) {
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

    private void checkSubjectType(int subjectId) {
        QuestionSubject subject = subjects.stream().filter(o -> o.getId() == subjectId).findAny().orElse(null);
        if(subject != null) {
            String value = subject.getType();

            switch (value) {
                case "math":
                    mathCorrect++;
                    break;
                case "natural":
                    naturalCorrect++;
                    break;
                case "social":
                    socialCorrect++;
                    break;
                case "alsana":
                    alsanaCorrect++;
                    break;
                default:
                    break;
            }
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
