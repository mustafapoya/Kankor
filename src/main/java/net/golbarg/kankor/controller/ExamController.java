package net.golbarg.kankor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.University;

import java.util.ArrayList;

public class ExamController {
    private ObservableList<Question> mathList = FXCollections.observableArrayList();
    private ObservableList<Question> naturalList = FXCollections.observableArrayList();
    private ObservableList<Question> socialList = FXCollections.observableArrayList();
    private ObservableList<Question> alsanaList = FXCollections.observableArrayList();
    private int math, natural, social, alsana, totalCorrect;
    ObservableList<Question> questions = FXCollections.observableArrayList();

    AnswerSheet answerSheet;
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

    public void compare(AnswerSheet answers, ObservableList<Question> questions) {
        this.answerSheet = answers;
        this.questions = questions;
        math = 0;
        social = 0;
        natural = 0;
        alsana = 0;
        totalCorrect = 0;
        for (int i = 0; i < questions.size(); i++) {
            int selectedAnswer = answers.getRows().get(i).getSelectedCellValue() + 1;
            int correctAnswer = questions.get(i).getCorrect();
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

    public ObservableList<University> getUniversity(ObservableList<FieldSelection> fields) {
        ArrayList<String> codes = new ArrayList<>();
        ObservableList<University> universityList = FXCollections.observableArrayList();
        for (int i = 0; i < fields.size(); i++) {
            codes.add(fields.get(i).getSelectedFieldValue());
        }
        String query = "SELECT UNIVERSITIES.UNI_NAME,FACULTY.FAC_NAME,FACULTY.FAC_DEPARTMENT, FACULTY.FAC_CODE, FACULTY.FAC_MINIMUM_GRADE  FROM FACULTY JOIN UNIVERSITIES ON UNIVERSITIES.UNI_ID = FACULTY.FAC_UNI_ID WHERE FACULTY.FAC_CODE IN (?, ?, ?, ?, ?) ORDER BY FAC_MINIMUM_GRADE DESC;";
        try {
            PreparedStatement statement = DatabaseConnection.getLocalConnection().prepareStatement(query);
            statement.setString(1, codes.get(0));
            statement.setString(2, codes.get(1));
            statement.setString(3, codes.get(2));
            statement.setString(4, codes.get(3));
            statement.setString(5, codes.get(4));

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                UniversityFaculty u = new UniversityFaculty(result.getString("UNI_NAME"), result.getString("FAC_NAME"),
                        result.getString("FAC_DEPARTMENT"), result.getString("FAC_CODE"),
                        result.getString("FAC_MINIMUM_GRADE"));
                universityList.add(u);
            }
        } catch (Exception e) {

        }
        return universityList;
    }

}
