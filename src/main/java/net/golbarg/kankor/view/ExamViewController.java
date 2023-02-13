package net.golbarg.kankor.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import net.golbarg.kankor.controller.AnalogClock;
import net.golbarg.kankor.controller.CountDownWorker;
import net.golbarg.kankor.controller.ExamController;
import net.golbarg.kankor.controller.StopWatchWorker;
import net.golbarg.kankor.model.Question;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ExamViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private Button btnEndExam;
    @FXML
    private ScrollPane spAnswerSheet;
    @FXML
    private HBox hboxAnswers;
    @FXML
    private Label lblUpTimer;
    @FXML
    private Label lblDownTimer;
    @FXML
    private Label lblClock;
    @FXML
    private Label lblMathQuestion;
    @FXML
    private Label lblNaturalQuestion;
    @FXML
    private Label lblSocialQuestion;
    @FXML
    private Label lblAlsanaQuestion;
    @FXML
    private ScrollPane spQuestion;
    @FXML
    private VBox vbQuestion;
    // Up Timer
    static StopWatchWorker stopWatchWorker;
    private Thread upTimer;
    // Down Timer
    static CountDownWorker countDownWorker;
    private Thread downTimer;

    private AnswerSheetViewController answerSheet;
    private ObservableList<Question> questionList = FXCollections.observableArrayList();
    private ObservableList<Label> subjectSections = FXCollections.observableArrayList();
    private ExamController examController;
    private Exam examProcess;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subjectSections.addAll(Arrays.asList(lblMathQuestion, lblNaturalQuestion, lblSocialQuestion, lblAlsanaQuestion));
        examController = new ExamController();

//        startExamProcess();
//        btnEndExam.setOnAction(event -> {
//            processQuestionAnswers();
//            System.out.println(examController);
//        });
    }

    public void processQuestionAnswers() {
        examController.checkAnswers(answerSheet, questionList);
    }

    public void startExamProcess() {
        try {
            examProcess = new Exam();
            examProcess.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopExamProcess() {
        stopWatchWorker.stop();
        countDownWorker.stop();
    }

    private class Exam extends Thread {
        @Override
        public void run() {

            try {
                //load answer sheet
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("answer-sheet-view.fxml"));
                VBox answerSheetView = fxmlLoader.load();
                answerSheet = fxmlLoader.getController();
                answerSheet.initAnswerSheet(160);
                hboxAnswers.getChildren().add(answerSheetView);

                // start timers
                setFont(lblUpTimer);
                setFont(lblDownTimer);
                startUpTimer();
                startDownTimer();
                lblDownTimer.textProperty().addListener((observer, oldValue, newValue) -> changeColor(lblDownTimer));
                lblClock.setText("");
                lblClock.setGraphic(new AnalogClock("", 20));
                lblClock.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                lblClock.setAlignment(Pos.CENTER);

                //init questions
                initializeQuestions();
                setQuestion();
                bindQuestionScrollPane();
                changeScrollPaneSize();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void changeColor(Label lbl) {
        if (countDownWorker.getDuration().toMillis() >= 10000) {
            lbl.setStyle("-fx-text-fill : green;");
        } else if (countDownWorker.getDuration().toMillis() >= 1000) {
            lbl.setStyle("-fx-text-fill : yellow;");
            lbl.setEffect(new DropShadow(5, Color.RED));
        } else {
            lbl.setStyle("-fx-text-fill : red;");
        }
    }

    private void setFont(Label lbl) {
        lbl.getStyleClass().add("lbl-timer");
    }

    private void startUpTimer() {
        stopWatchWorker = new StopWatchWorker();
        upTimer = new Thread(stopWatchWorker);
        lblUpTimer.textProperty().bind(stopWatchWorker.messageProperty());
        upTimer.setDaemon(true);
        upTimer.start();
    }

    private void startDownTimer() {
        countDownWorker = new CountDownWorker();
        downTimer = new Thread(countDownWorker);
        lblDownTimer.textProperty().bind(countDownWorker.messageProperty());
        downTimer.setDaemon(true);
        downTimer.start();
    }

    private void initializeQuestions() {
        questionList.clear();
        examController.generateQuestion();
        questionList.addAll(examController.getMathList());
        questionList.addAll(examController.getNaturalList());
        questionList.addAll(examController.getSocialList());
        questionList.addAll(examController.getAlsanaList());
    }

    private void setQuestion() {
        vbQuestion.getChildren().clear();
        try {
            for (int i = 0; i < questionList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("question-item-view.fxml"));
                VBox questionView = fxmlLoader.load();
                QuestionItemViewController questionController = fxmlLoader.getController();
                questionController.initData(questionList.get(i), (i+1));
                vbQuestion.getChildren().add(questionView);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void bindQuestionScrollPane() {
        int math = examController.getMathList().size();
        int natural = examController.getNaturalList().size() + math;
        int social = examController.getSocialList().size() + natural;

        lblMathQuestion.setOnMouseClicked(event -> spQuestion.setVvalue(0));
        lblNaturalQuestion.setOnMouseClicked(event -> spQuestion.setVvalue(math+1));
        lblSocialQuestion.setOnMouseClicked(event -> spQuestion.setVvalue(natural+1));
        lblAlsanaQuestion.setOnMouseClicked(event -> spQuestion.setVvalue(social+1));

        spQuestion.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                spAnswerSheet.setVvalue(newValue.doubleValue());
                if (newValue.doubleValue() >= 0 && newValue.doubleValue() <= math) {
                    highlightSubjectSection(0);
                } else if (newValue.doubleValue() > math && newValue.doubleValue() <= natural) {
                    highlightSubjectSection(1);
                } else if (newValue.doubleValue() > natural && newValue.doubleValue() <= social) {
                    highlightSubjectSection(2);
                } else if (newValue.doubleValue() > social) {
                    highlightSubjectSection(3);
                }
            }
        });
    }

    private void changeScrollPaneSize() {
        vbQuestion.setStyle("-fx-font-size: 16px;-fx-background-color:white;");
        spQuestion.setStyle("-fx-background:white;");
        vbQuestion.setPadding(new Insets(20));
        vbQuestion.setFillWidth(true);
        spQuestion.setVmin(0);
        spQuestion.setVmax(160);
        spQuestion.setHvalue(1);
        spAnswerSheet.setVmin(0);
        spAnswerSheet.setVmax(160);
    }

    private void highlightSubjectSection(int which) {
        for(int i = 0; i < subjectSections.size(); i++) {
            if(which == i) {
                subjectSections.get(i).getStyleClass().removeIf(style -> style.equals("lbl-subject-section"));
                subjectSections.get(i).getStyleClass().add("lbl-subject-section-selected");
            } else {
                subjectSections.get(i).getStyleClass().removeIf(style -> style.equals("lbl-subject-section-selected"));
                subjectSections.get(i).getStyleClass().add("lbl-subject-section");
            }
        }
    }

    public ExamController getExamController() {
        return examController;
    }

    public String getDuration() {
        return lblUpTimer.getText();
    }

    public ObservableList<Question> getQuestionList() {
        return questionList;
    }

    public AnswerSheetViewController getAnswerSheet() {
        return answerSheet;
    }
}
