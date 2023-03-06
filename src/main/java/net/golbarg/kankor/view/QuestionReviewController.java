package net.golbarg.kankor.view;

import javafx.application.Platform;
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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.golbarg.kankor.custom.CellFactorySample;
import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.db.TableQuestionSubject;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.QuestionSubject;
import net.golbarg.kankor.model.QuestionUpdate;
import org.controlsfx.control.ToggleSwitch;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionReviewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private Button btnNote;
    @FXML
    private ToggleSwitch toggleSwitchShowAnswer;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private ComboBox<QuestionSubject> comboSubject;

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    @FXML
    private ScrollPane scrollPaneContent;
    @FXML
    private VBox vbContentContainer;
    @FXML
    private Label lblSummary;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;

    //
    ObservableList<QuestionSubject> subjectList = FXCollections.observableArrayList();
    ObservableList<Question> questionList = FXCollections.observableArrayList();
    ObservableList<QuestionItemViewController> questionItems = FXCollections.observableArrayList();
    TableQuestion tableQuestion;

    private int current_page = 1;
    private int data_per_page = 30;
    private int number_of_pages = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setVisible(false);
        toggleSwitchShowAnswer.setSelected(true);
        tableQuestion = new TableQuestion();

        toggleSwitchShowAnswer.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                for(QuestionItemViewController questionItem: questionItems) {
                    if(newValue) {
                        questionItem.selectCorrectAnswer();
                    } else {
                        questionItem.resetCorrectAnswer();
                    }
                }
            }
        });

        // load question subjects
        subjectList.addAll(new TableQuestionSubject().getAll());
        comboSubject.setItems(subjectList);

        Callback<ListView<QuestionSubject>, ListCell<QuestionSubject>> cellFactorySubject
                = CellFactorySample.getComboBoxQuestionSubject(null, 0);
        comboSubject.setButtonCell(cellFactorySubject.call(null));
        comboSubject.setCellFactory(cellFactorySubject);
        comboSubject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<QuestionSubject>() {
            @Override
            public void changed(ObservableValue<? extends QuestionSubject> observableValue, QuestionSubject oldValue, QuestionSubject newValue) {
                if(newValue != null) {
                    questionList.clear();
                    questionList.addAll(tableQuestion.getQuestionOf(newValue.getId()));
                    number_of_pages = (int) Math.ceil(questionList.size() / (double)data_per_page);
                    current_page = 1;
                    lblSummary.setText("مجموع سوالات: " + questionList.size());
                    loadQuestions();
                }
            }
        });
        comboSubject.getSelectionModel().selectFirst();

        btnNote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Load bookmarked questions");
            }
        });

        //
        btnNext.setOnAction(event -> {
            if(current_page < number_of_pages) {
                current_page++;
                loadQuestions();
            }
        });

        btnPrevious.setOnAction(event -> {
            if(current_page > 1) {
                current_page--;
                loadQuestions();
            }
        });

        scrollPaneContent.setStyle("-fx-background:white;");
        vbContentContainer.setStyle("-fx-background-color:white;");
        vbContentContainer.setPadding(new Insets(5));
        vbContentContainer.setFillWidth(true);
    }

    private void loadQuestions() {
        if(current_page <= number_of_pages && current_page >= 1) {
            vbContentContainer.getChildren().clear();
            scrollPaneContent.setVvalue(0.0);
            questionItems.clear();
            try {
                Platform.runLater(() -> {
                    try {
                        for (int i = (current_page - 1) * data_per_page; i < current_page * data_per_page && i < questionList.size(); i++) {
                            FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("question-item-view.fxml"));
                            VBox questionView = fxmlLoader.load();
                            QuestionItemViewController questionController = fxmlLoader.getController();
                            questionController.initData(questionList.get(i), (i+1));
                            vbContentContainer.getChildren().add(questionView);
                            if(toggleSwitchShowAnswer.isSelected()) {
                                questionController.setCorrectAnswer(questionList.get(i).getCorrectChoice());
                            }
                            questionItems.add(questionController);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if(current_page >= number_of_pages) {
            btnNext.setDisable(true);
        } else {
            btnNext.setDisable(false);
        }

        if(current_page <= 1) {
            btnPrevious.setDisable(true);
        } else {
            btnPrevious.setDisable(false);
        }
    }

}
