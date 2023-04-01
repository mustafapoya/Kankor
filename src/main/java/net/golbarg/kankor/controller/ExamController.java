package net.golbarg.kankor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.golbarg.kankor.db.TableUniversityFaculty;
import net.golbarg.kankor.db.TableQuestionSubject;
import net.golbarg.kankor.model.*;
import net.golbarg.kankor.view.exam.component.AnswerSheetViewController;
import net.golbarg.kankor.view.exam.component.FieldSelectionViewController;

import java.util.ArrayList;

public class ExamController {
    private QuestionGenerator questionGenerator;
    private ArrayList<QuestionSubject> subjects;
    private Exam exam;
    private ExamCorrectAnswerCount answerCount;

    public ExamController() {
        this(SystemController.DEFAULT_EXAM);
    }

    public ExamController(Exam exam) {
        this.exam = exam;
        questionGenerator = new QuestionGenerator(exam);
        subjects = new TableQuestionSubject().getAll();
        answerCount = new ExamCorrectAnswerCount();
    }

    public void generateQuestion() {
        exam.setMathList(questionGenerator.getMathQuestions());
        exam.setNaturalList(questionGenerator.getNaturalQuestion());
        exam.setSocialList(questionGenerator.getSocialQuestion());
        exam.setAlsanaList(questionGenerator.getAlsanaQuestion());
    }

    public void checkAnswers(AnswerSheetViewController answers, ObservableList<Question> questions) {
        for (int i = 0; i < questions.size(); i++) {
            int selectedAnswer = answers.getRowList().get(i).getSelectedAnswer();
            int correctAnswer = questions.get(i).getCorrectChoice();

            if (selectedAnswer == correctAnswer) {
                int subjectId = questions.get(i).getSubject().getId();
                checkSubject(subjectId, answerCount);
            }
        }
    }

    private void checkSubject(int subjectId, ExamCorrectAnswerCount answerCount) {
        QuestionSubject subject = subjects.stream().filter(o -> o.getId() == subjectId).findAny().orElse(null);
        if(subject != null) {
            String value = subject.getType();

            switch (value) {
                case "math":
                    answerCount.incrementMath();
                    break;
                case "natural":
                    answerCount.incrementNatural();
                    break;
                case "social":
                    answerCount.incrementSocial();
                    break;
                case "alsana":
                    answerCount.incrementAlsana();
                    break;
                default:
                    break;
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

    public Exam getExam() {
        return exam;
    }

    public ExamCorrectAnswerCount getAnswerCount() {
        return answerCount;
    }
    public QuestionGenerator getQuestionGenerator() {
        return questionGenerator;
    }
}
