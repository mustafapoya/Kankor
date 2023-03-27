package net.golbarg.kankor.view.exam;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.db.TableQuestionSubject;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.QuestionSubject;
import net.golbarg.kankor.model.QuestionUpdate;
import net.golbarg.kankor.model.SubjectSelection;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ExamSubjectSelectViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private ScrollPane spContent;

    @FXML
    private Label lblType;
    @FXML
    private ComboBox<String> comboType;

    @FXML
    private CheckBox checkAll;
    @FXML
    private CheckBox checkMath;
    @FXML
    private CheckBox checkTrigonometry;
    @FXML
    private CheckBox checkGeometry;
    @FXML
    private CheckBox checkChemical;
    @FXML
    private CheckBox checkPhysic;
    @FXML
    private CheckBox checkBiology;
    @FXML
    private CheckBox checkIslamic;
    @FXML
    private CheckBox checkHistory;
    @FXML
    private CheckBox checkGeography;
    @FXML
    private CheckBox checkDari;
    @FXML
    private CheckBox checkPashto;
    @FXML
    private CheckBox checkGeneral;

    @FXML
    private Spinner<Integer> spinnerAll;
    @FXML
    private Spinner<Integer> spinnerMath;
    @FXML
    private Spinner<Integer> spinnerTrigonometry;
    @FXML
    private Spinner<Integer> spinnerGeometry;
    @FXML
    private Spinner<Integer> spinnerChemical;
    @FXML
    private Spinner<Integer> spinnerPhysic;
    @FXML
    private Spinner<Integer> spinnerBiology;
    @FXML
    private Spinner<Integer> spinnerIslamic;
    @FXML
    private Spinner<Integer> spinnerHistory;
    @FXML
    private Spinner<Integer> spinnerGeography;
    @FXML
    private Spinner<Integer> spinnerDari;
    @FXML
    private Spinner<Integer> spinnerPashto;
    @FXML
    private Spinner<Integer> spinnerGeneral;

    @FXML
    private Label lblAll;
    @FXML
    private Label lblMath;
    @FXML
    private Label lblTrigonometry;
    @FXML
    private Label lblGeometry;
    @FXML
    private Label lblChemical;
    @FXML
    private Label lblPhysic;
    @FXML
    private Label lblBiology;
    @FXML
    private Label lblIslamic;
    @FXML
    private Label lblHistory;
    @FXML
    private Label lblGeography;
    @FXML
    private Label lblDari;
    @FXML
    private Label lblPashto;
    @FXML
    private Label lblGeneral;
    @FXML
    private Label lblTotalQuestions;

    HashMap<String, QuestionSubject> subjects = new HashMap<>();
    private HashMap<String, SubjectSelection> hashSubjects = new HashMap<>();
    ObservableList<Question> questionList = FXCollections.observableArrayList();
    TableQuestion tableQuestion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initQuestionSubjects();
        lblTotalQuestions.setText("");
        tableQuestion = new TableQuestion();
        initSubjectsDetails();

    }
    private void initQuestionSubjects() {
        ArrayList<QuestionSubject> subjectList = new TableQuestionSubject().getAll();
        subjects.put("all", new QuestionSubject(-1, "all", "all", "همه"));

        for(int i = 0; i < subjectList.size(); i++) {
            subjects.put(subjectList.get(i).getKey(), subjectList.get(i));
        }
    }

    private void initSubjectsDetails() {
        hashSubjects.put("all", new SubjectSelection(subjects.get("all"), checkAll, spinnerAll, lblAll));
        hashSubjects.put("math", new SubjectSelection(subjects.get("math"), checkMath, spinnerMath, lblMath));
        hashSubjects.put("triangles", new SubjectSelection(subjects.get("triangles"), checkTrigonometry, spinnerTrigonometry, lblTrigonometry));
        hashSubjects.put("geometry", new SubjectSelection(subjects.get("geometry"), checkGeometry, spinnerGeometry, lblGeometry));
        hashSubjects.put("chemistry", new SubjectSelection(subjects.get("chemistry"), checkChemical, spinnerChemical, lblChemical));
        hashSubjects.put("physic", new SubjectSelection(subjects.get("physic"), checkPhysic, spinnerPhysic, lblPhysic));
        hashSubjects.put("biology", new SubjectSelection(subjects.get("biology"), checkBiology, spinnerBiology, lblBiology));
        hashSubjects.put("islamic", new SubjectSelection(subjects.get("islamic"), checkIslamic, spinnerIslamic, lblIslamic));
        hashSubjects.put("history", new SubjectSelection(subjects.get("history"), checkHistory, spinnerHistory, lblHistory));
        hashSubjects.put("geography", new SubjectSelection(subjects.get("geography"), checkGeography, spinnerGeography, lblGeography));
        hashSubjects.put("dari", new SubjectSelection(subjects.get("dari"), checkDari, spinnerDari, lblDari));
        hashSubjects.put("pashto", new SubjectSelection(subjects.get("pashto"), checkPashto, spinnerPashto, lblPashto));
        hashSubjects.put("general", new SubjectSelection(subjects.get("general"), checkGeneral, spinnerGeneral, lblGeneral));


        for(String key: hashSubjects.keySet()) {
            hashSubjects.get(key).getSpinner().setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0, 10)
            );

            hashSubjects.get(key).getSpinner().visibleProperty()
                    .bind(hashSubjects.get(key).getCheckBox().selectedProperty());

            hashSubjects.get(key).getLabel().visibleProperty()
                    .bind(hashSubjects.get(key).getCheckBox().selectedProperty());
            hashSubjects.get(key).getLabel().setText("");

            hashSubjects.get(key).getSpinner().valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    hashSubjects.get(key).getLabel().setText(newValue + " سوال ");
                    int questionTotal = numberOfQuestions();
                    if(questionTotal > 0)
                        lblTotalQuestions.setText(String.valueOf(questionTotal) + " سوال ");
                }
            });
        }

        checkAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                for(String key: hashSubjects.keySet()) {
                    if(!key.equals("all")) {
                        hashSubjects.get(key).getCheckBox().setDisable(newValue);
                        hashSubjects.get(key).getSpinner().setDisable(newValue);
                    }
                }
            }
        });
    }

    public void showSelectedChecks() {
        for(String key: hashSubjects.keySet()) {
            if (hashSubjects.get(key).getCheckBox().isSelected()) {
                System.out.println(key + " is selected");
            }
        }
    }

    public int numberOfQuestions() {
        int sum = 0;
        if (checkAll.isSelected()) {
            sum = hashSubjects.get("all").getSpinner().getValue();
        } else {
            for(String key: hashSubjects.keySet()) {
                if (!key.equals("all") && hashSubjects.get(key).getCheckBox().isSelected()) {
                    sum += hashSubjects.get(key).getSpinner().getValue();
                }
            }
        }
        return sum;
    }

    public ObservableList<Question> getQuestions() {
        questionList.clear();
        if (checkAll.isSelected()) {
            questionList.addAll(tableQuestion.getQuestions(hashSubjects.get("all").getSpinner().getValue()));
        } else {
            for(String key: hashSubjects.keySet()) {
                if (!key.equals("all")) {
                    if (hashSubjects.get(key).getCheckBox().isSelected()) {
                        int subjectId = hashSubjects.get(key).getSubject().getId();
                        int numberOfQuestion = hashSubjects.get(key).getSpinner().getValue();
                        questionList.addAll(
                                tableQuestion.getQuestionsOf(subjectId, numberOfQuestion));
                    }
                }
            }
        }
        return questionList;
    }

}
