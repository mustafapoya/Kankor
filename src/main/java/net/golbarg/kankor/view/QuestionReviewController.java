package net.golbarg.kankor.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.golbarg.kankor.controller.QuestionGenerator;
import net.golbarg.kankor.custom.CellFactorySample;
import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.db.TableQuestionSubject;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.QuestionSubject;
import net.golbarg.kankor.model.QuestionUpdate;
import net.golbarg.kankor.model.University;
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
    ObservableList<QuestionSubject> subjectList = FXCollections.observableArrayList();
    ObservableList<QuestionUpdate> typeList = FXCollections.observableArrayList();
    ObservableList<Question> questionList = FXCollections.observableArrayList();
    TableQuestion tableQuestion;
    double scrollPosition;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setVisible(false);
        toggleSwitchShowAnswer.setSelected(true);
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
                    System.out.println("do the action");
                }
            }
        });

        comboSubject.getSelectionModel().selectFirst();
        //
        tableQuestion = new TableQuestion();
        questionList.addAll(tableQuestion.getAll());

        loadQuestions();

        pagination.setPageCount(5);
        pagination.setCurrentPageIndex(1);
        pagination.setMaxPageIndicatorCount(10);

        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
    }

    public int itemsPerPage() {
        return 8;
    }

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            VBox element = new VBox();
            Hyperlink link = new Hyperlink("Item " + (i+1));
            link.setVisited(true);
            Label text = new Label("Search results\nfor "+ link.getText());
            element.getChildren().addAll(link, text);
            box.getChildren().add(element);
        }
        return box;
    }

    private void loadQuestions() {
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
