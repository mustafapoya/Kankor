package net.golbarg.kankor.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import net.golbarg.kankor.custom.CellFactorySample;
import net.golbarg.kankor.db.TableTutorial;
import net.golbarg.kankor.db.TableTutorialDetail;
import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Tutorial;
import net.golbarg.kankor.model.TutorialDetail;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private ComboBox<Tutorial> comboTutorial;

    @FXML
    private ListView<TutorialDetail> listTutorialDetail;
    /*  */
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private WebView webContent;
    WebEngine webEngine;
    @FXML
    Slider zoomSlider;

    private double zoom = 1;
    private double increment = 0.25;
    ObservableList<Tutorial> tutorialList = FXCollections.observableArrayList();
    ObservableList<TutorialDetail> tutorialDetailList = FXCollections.observableArrayList();
    FilteredList<TutorialDetail> filteredList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // list of tutorial details
        tutorialDetailList.addAll(new TableTutorialDetail().getAll());
        filteredList = new FilteredList<>(tutorialDetailList, data -> true);

        listTutorialDetail.setItems(filteredList);
        listTutorialDetail.setCellFactory(createTutorialDetailListViewCellFactory());

        // combo tutorial
        tutorialList.addAll(new TableTutorial().getAll());
        comboTutorial.setItems(tutorialList);
        Callback<ListView<Tutorial>, ListCell<Tutorial>> cellFactoryTutorial = CellFactorySample.getComboBoxTutorial(null, 0);
        comboTutorial.setButtonCell(cellFactoryTutorial.call(null));
        comboTutorial.setCellFactory(cellFactoryTutorial);

        comboTutorial.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tutorial>() {
            @Override
            public void changed(ObservableValue<? extends Tutorial> observableValue, Tutorial oldValue, Tutorial newValue) {
                filterTutorialDetails(newValue, filteredList);
            }
        });

        comboTutorial.getSelectionModel().selectFirst();

        //
        listTutorialDetail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TutorialDetail>() {
            @Override
            public void changed(ObservableValue<? extends TutorialDetail> observableValue, TutorialDetail oldValue, TutorialDetail newValue) {
                try {
                    if (newValue != null) {
                        webEngine.loadContent(newValue.getFullDescription());
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        initWebEngine();
        initializeSlider();

        zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                webContent.setZoom(newValue.doubleValue());
                zoom = newValue.doubleValue();
            }
        });

        createProgressReport(webContent.getEngine());
    }

    private void filterTutorialDetails(Tutorial newValue, FilteredList<TutorialDetail> filteredList) {
        filteredList.setPredicate(data -> {
            if(newValue == null) {
                return true;
            }
            int tutorialId = newValue.getId();

            if(data.getTutorialId() == tutorialId) {
                return true;
            } else {
                return false;
            }
        });
    }

    private void createProgressReport(WebEngine engine) {
        final LongProperty startTime = new SimpleLongProperty();
        final LongProperty endTime = new SimpleLongProperty();
        final LongProperty elapsedTime = new SimpleLongProperty();

        // compute elapsed time
        elapsedTime.bind(Bindings.subtract(endTime, startTime));
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                switch (newValue) {
                    case RUNNING:
                        startTime.set(System.nanoTime());
                        break;
                    case SUCCEEDED:
                        endTime.set(System.nanoTime());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initWebEngine() {
        webEngine = webContent.getEngine();
        webContent.setStyle("-fx-font-size:15px;");
//        String url = this.getClass().getResource("../html/Microcis Software Solutions.html").toExternalForm();
//        webEngine.load(url);
    }

    public void initializeSlider() {
        zoomSlider.setValue(1);
        zoomSlider.setShowTickMarks(true);
        zoomSlider.setMajorTickUnit(2);
        zoomSlider.setBlockIncrement(increment);
        zoomSlider.setMin(0);
        zoomSlider.setMax(10);
    }
    private static Callback<ListView<TutorialDetail>, ListCell<TutorialDetail>> createTutorialDetailListViewCellFactory() {
        return new Callback<ListView<TutorialDetail>, ListCell<TutorialDetail>>() {
            @Override
            public ListCell<TutorialDetail> call(ListView<TutorialDetail> param) {
                final Label leadLbl = new Label();
                FontIcon listIcon = new FontIcon("bi-journal-text");
                listIcon.setIconSize(16);

                final ListCell<TutorialDetail> cell = new ListCell<TutorialDetail>() {
                    @Override
                    protected void updateItem(TutorialDetail item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            leadLbl.setText(item.getTitle().trim());
                            setText(item.getTitle().trim());
                            setGraphic(listIcon);
                        } else {
                            leadLbl.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        };
    }

}
