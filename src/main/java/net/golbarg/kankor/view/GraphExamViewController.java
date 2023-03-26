package net.golbarg.kankor.view;

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
import net.golbarg.kankor.db.TableExam;
import net.golbarg.kankor.model.Exam;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
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
    private ComboBox<Exam> comboExam;
    @FXML
    private BarChart<String, Number> barChartSubject;
    @FXML
    private CategoryAxis xAxisSubject;
    @FXML
    private NumberAxis yAxisSubject;
    private ObservableList<Exam> exams = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exams.addAll(new TableExam().getAll());

        comboExam.getItems().addAll(exams);

        Callback<ListView<Exam>, ListCell<Exam>> cellFactoryExam
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

        for(int i = 0; i < exams.size(); i++) {
            series.getData().add(new XYChart.Data<>(exams.get(i).getExamDate().toString(), exams.get(i).getTotalScore()));
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

        comboExam.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Exam>() {
            @Override
            public void changed(ObservableValue<? extends Exam> observable, Exam oldValue, Exam newValue) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(" امتحان " + newValue.getExamDate().toString());
                series.getData().add(new XYChart.Data<String, Number>("ریاضی", newValue.getMathScore()));
                series.getData().add(new XYChart.Data<String, Number>("علوم طبیعی", newValue.getNaturalScore()));
                series.getData().add(new XYChart.Data<String, Number>("علوم اجتماعی", newValue.getSocialScore()));
                series.getData().add(new XYChart.Data<String, Number>("السنه", newValue.getAlsanaScore()));
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
