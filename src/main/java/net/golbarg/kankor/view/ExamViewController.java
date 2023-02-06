package net.golbarg.kankor.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
import net.golbarg.kankor.db.TableExam;
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.Faculty;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.User;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class ExamViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabExam;
    @FXML
    private Tab tabUniversity;
    @FXML
    private Tab tabResult;
    @FXML
    private Tab tabReview;

    // Exam Tab Section
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

    // University tab
    @FXML
    private BorderPane borderPaneUniversity;
    @FXML
    private Button btnUniversity;
    UniversityFormViewController universityViewController;

    //
    @FXML
    private BorderPane borderPaneExamResult;
    ExamResultViewController examResultViewController;

    //
    @FXML
    private BorderPane borderPaneExamReview;

    // Up Timer
    static StopWatchWorker stopWatchWorker;
    Thread upTimer;

    // Down Timer
    CountDownWorker countDownWorker;
    Thread downTimer;

    //
    User user;
    ObservableList<Question> questionList = FXCollections.observableArrayList();
    ExamController examController;
    AnswerSheetViewController answerSheet;

    ObservableList<Label> subjectSections = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subjectSections.addAll(Arrays.asList(lblMathQuestion, lblNaturalQuestion, lblSocialQuestion, lblAlsanaQuestion));

        disableTabs();

//        setStartExamAction();

        try {
            Exam e = new Exam();
            e.run();

            SelectUniversity selectUniversity = new SelectUniversity();
            selectUniversity.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void disableTabs() {
//        tabUniversity.setDisable(true);
//        tabResult.setDisable(true);
//        tabReview.setDisable(true);
    }

    class Exam extends Thread {
        @Override
        public void run() {
            examController = new ExamController();

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

        public void setFont(Label lbl) {
            lbl.getStyleClass().add("lbl-timer");
        }

        public void startUpTimer() {
            stopWatchWorker = new StopWatchWorker();
            upTimer = new Thread(stopWatchWorker);
            lblUpTimer.textProperty().bind(stopWatchWorker.messageProperty());
            upTimer.setDaemon(true);
            upTimer.start();
        }

        public void startDownTimer() {
            countDownWorker = new CountDownWorker();
            downTimer = new Thread(countDownWorker);
            lblDownTimer.textProperty().bind(countDownWorker.messageProperty());
            downTimer.setDaemon(true);
            downTimer.start();
        }

        public void changeColor(Label lbl) {
            if (countDownWorker.getDuration().toMillis() >= 10000) {
                lbl.setStyle("-fx-text-fill : green;");
            } else if (countDownWorker.getDuration().toMillis() >= 1000) {
                lbl.setStyle("-fx-text-fill : yellow;");
                lbl.setEffect(new DropShadow(5, Color.RED));
            } else {
                lbl.setStyle("-fx-text-fill : red;");
                tabPane.getTabs().get(0).setDisable(true);
                tabPane.getTabs().get(1).setDisable(false);
                tabPane.getSelectionModel().select(1);
                try {
                    // TODO: load university
//                    universitySelection = new SelectUniversity();
//                    universitySelection.run();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        public void initializeQuestions() {
            questionList.clear();
            examController.generateQuestion();
            questionList.addAll(examController.getMathList());
            questionList.addAll(examController.getNaturalList());
            questionList.addAll(examController.getSocialList());
            questionList.addAll(examController.getAlsanaList());
        }

        public void setQuestion() {
            vbQuestion.getChildren().clear();
            try {
                for (int i = 0; i < questionList.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("question-item-view.fxml"));
                    VBox questionView = fxmlLoader.load();
                    QuestionItemViewController questionController = fxmlLoader.getController();
                    questionController.initData(questionList.get(i));
                    vbQuestion.getChildren().add(questionView);

                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        public void bindQuestionScrollPane() {
            spQuestion.vvalueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    spAnswerSheet.setVvalue(newValue.doubleValue());
                    if (newValue.doubleValue() >= 0 && newValue.doubleValue() <= 46) {
                        highlightSubjectSection(0);
                    } else if (newValue.doubleValue() >= 47 && newValue.doubleValue() <= 85) {
                        highlightSubjectSection(1);
                    } else if (newValue.doubleValue() >= 86 && newValue.doubleValue() <= 124) {
                        highlightSubjectSection(2);
                    } else if (newValue.doubleValue() >= 125) {
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
    }

    class SelectUniversity extends Thread {
        @Override
        public void run() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("university-form-view.fxml"));
                BorderPane universityFormView = fxmlLoader.load();
                universityViewController = fxmlLoader.getController();
                borderPaneUniversity.setCenter(universityFormView);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ExamResult extends Thread {
        SelectUniversity university;

        public ExamResult(SelectUniversity university) {
            this.university = university;
        }

        @Override
        public void run() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("exam-result-view.fxml"));
                BorderPane examResultView = fxmlLoader.load();
                examResultViewController = fxmlLoader.getController();
                borderPaneExamResult.setCenter(examResultView);
                examResultViewController.setDuration(stopWatchWorker.messageProperty().get());

                setUniversity();
                saveExamResult();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // TODO: date parameter, user should be checked its error prone
        public void saveExamResult() {
            LocalDate l = LocalDate.now();
            String date = l.getYear() + "-" + l.getMonthValue() + "-" + l.getDayOfMonth();
            net.golbarg.kankor.model.Exam e =
                    new net.golbarg.kankor.model.Exam(0, user.getId(), "0000", new Date(date),
                                                new Date(stopWatchWorker.getMessage().toString()),
                                                examController.getMath(), examController.getNatural(),
                                                examController.getSocial(),
                                                examController.getAlsana(),examController.getKankorScore(),
                                                examResultViewController.getKankorResult());
            new TableExam().create(e);
        }

        public void setUniversity() {
            ObservableList<FieldSelectionViewController> list = universityViewController.getFields();
            ObservableList<Faculty> universityList = examController.getUniversity(list);

            for (int i = 0; i < universityList.size(); i++) {
                System.out.println("selected -> " + universityList.get(i).toString());
            }
            System.out.println("set university: " + examController.getKankorScore());

            if (universityList.size() > 0) {
                Faculty faculty = examController.getPassedField(universityList, 100);
                if (faculty != null) {
                    examResultViewController.setKankorResult(faculty.getUniversity() + ", " + faculty.getName());
                } else {
                    examResultViewController.setKankorResult("بی نتیجه !");
                }
            } else {
                examResultViewController.setKankorResult("شما هیچ رشته ای را انتخاب نکرده اید!");
            }
        }

        public void shareOnWeb() {
            examResultViewController.getBtnShare().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (examController.getKankorScore() > 250) {
                        share();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        String text = "برای انتشار در ویب نمره شما باید بالای 250 باشد!";
                        alert.setContentText(text);
                        alert.showAndWait();
                    }
                }
            });
        }

        public void share() {

        }
    }


}
