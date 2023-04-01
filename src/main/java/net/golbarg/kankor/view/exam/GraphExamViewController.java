package net.golbarg.kankor.view.exam;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.Duration;
import net.golbarg.kankor.custom.CellFactorySample;
import net.golbarg.kankor.db.TableExamResult;
import net.golbarg.kankor.model.ExamResult;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphExamViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabExam;
    @FXML
    private Tab tabExamSubject;

    // Exam Graph
    @FXML private LineChart<String, Number> lineChartExam;
    @FXML private CategoryAxis xAxisExam;
    @FXML private NumberAxis yAxisExam;

    // Subject Graph
    @FXML
    private ComboBox<ExamResult> comboExam;
    @FXML
    private BarChart<String, Number> barChartSubject;
    @FXML
    private CategoryAxis xAxisSubject;
    @FXML
    private NumberAxis yAxisSubject;
    private ObservableList<ExamResult> examResults = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        examResults.addAll(new TableExamResult().getAll());

        comboExam.getItems().addAll(examResults);

        Callback<ListView<ExamResult>, ListCell<ExamResult>> cellFactoryExam
                = CellFactorySample.getComboBoxExamDate(null, 0);
        comboExam.setButtonCell(cellFactoryExam.call(null));
        comboExam.setCellFactory(cellFactoryExam);

        initExamGraph();
        initSubjectGraph();
    }

    private void initExamGraph() {
        xAxisExam.setLabel("امتحانات");
        yAxisExam.setLabel("نمرات");
        yAxisExam.setAnimated(true);
        yAxisExam.setLowerBound(0);
        yAxisExam.setTickUnit(20);
        yAxisExam.setUpperBound(360);
        yAxisExam.setAutoRanging(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("پروسه امتحان");

        for(int i = 0; i < examResults.size(); i++) {
            series.getData().add(new XYChart.Data<>(examResults.get(i).getExam().getDate().toString(), examResults.get(i).getCorrectAnswerCount().getScore()));
        }
        lineChartExam.getData().add(series);
    }

    private void initSubjectGraph() {
        xAxisSubject.setLabel("مضامین");
        yAxisSubject.setLabel("نظر به صد");
        yAxisSubject.setAnimated(true);
        yAxisSubject.setLowerBound(0);
        yAxisSubject.setTickUnit(10);
        yAxisSubject.setUpperBound(100);
        yAxisSubject.setAutoRanging(false);
        barChartSubject.setBarGap(5);
        barChartSubject.setCategoryGap(10);

        comboExam.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ExamResult>() {
            @Override
            public void changed(ObservableValue<? extends ExamResult> observable, ExamResult oldValue, ExamResult newValue) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(" امتحان " + newValue.getExam().getDate().toString());
                series.getData().add(new XYChart.Data<String, Number>("ریاضی", newValue.getCorrectAnswerCount().getMath()));
                series.getData().add(new XYChart.Data<String, Number>("علوم طبیعی", newValue.getCorrectAnswerCount().getNatural()));
                series.getData().add(new XYChart.Data<String, Number>("علوم اجتماعی", newValue.getCorrectAnswerCount().getSocial()));
                series.getData().add(new XYChart.Data<String, Number>("السنه", newValue.getCorrectAnswerCount().getAlsana()));
                barChartSubject.getData().clear();
                barChartSubject.getData().add(series);
//                animateBar();
            }
        });

    }

    private void animateBar() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(800), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (XYChart.Series<String, Number> series : barChartSubject.getData()) {
                    for(XYChart.Data<String, Number> data : series.getData()){
                        data.setYValue(data.getYValue());
                    }
                }


            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }
}
