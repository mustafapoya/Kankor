package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import net.golbarg.kankor.controller.QuestionGenerator;
import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.model.Question;
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
    private ComboBox<String> comboSubject;
    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    @FXML
    private ScrollPane scrollPaneContent;
    @FXML
    private VBox vbContentContainer;
    @FXML
    private Pagination pagination;

    //
    ObservableList<String> searchList = FXCollections.observableArrayList();
    ObservableList<String> subjectList = FXCollections.observableArrayList("همه", "جغرافیه", "تاریخ", "کیمیا", "ریاضی",
            "بیولوژی", "فزیک", "پشتو", "هندسه", "دری", "عمومی", "مثلثات", "اسلامی");
    ObservableList<QuestionUpdate> typeList = FXCollections.observableArrayList();
    ObservableList<Question> questionList = FXCollections.observableArrayList();
    double scrollPosition;
    TableQuestion tableQuestion;
    boolean isShowAnswer = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        progressBar.setVisible(false);

        toggleSwitchShowAnswer.setSelected(true);
        comboSubject.setItems(subjectList);
        comboSubject.getSelectionModel().selectFirst();

        //add all questions
        tableQuestion = new TableQuestion();
        questionList.addAll(tableQuestion.getAll());

        vbContentContainer.getChildren().clear();

        try {
            for (int i = 0; i < 100; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("question-item-view.fxml"));
                VBox questionView = fxmlLoader.load();
                QuestionItemViewController questionController = fxmlLoader.getController();
                questionController.initData(questionList.get(i), (i+1));
                vbContentContainer.getChildren().add(questionView);
                if(toggleSwitchShowAnswer.isSelected()) {
                    questionController.setCorrectAnswer(questionList.get(i).getCorrectChoice());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}
