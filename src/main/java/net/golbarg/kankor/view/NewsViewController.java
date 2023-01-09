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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import net.golbarg.kankor.db.TableNews;
import net.golbarg.kankor.model.News;
import net.golbarg.kankor.model.Tutorial;
import net.golbarg.kankor.model.TutorialDetail;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class NewsViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private Button btnRefresh;

    /* news list */
    @FXML
    private TextField txtSearchNews;
    @FXML
    private ListView<News> listViewNews;

    @FXML
    private WebView webContent;
    @FXML
    private Slider zoomSlider;

    static double zoom = 1;
    // measurement of Zoom
    public static final double increment = 0.25;
    //
    private WebEngine webEngine;

    ObservableList<News> offlineNewsList = FXCollections.observableArrayList();
    FilteredList<News> filteredNewsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeZoomSlider();
        createProgressReport(webContent.getEngine());
        initializeHtmlViewer();
        initOfflineNews();

        txtSearchNews.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                filterTutorialDetails(txtSearchNews.getText(), filteredNewsList);
            }
        });
    }

    public void initializeZoomSlider() {
        zoomSlider.setValue(1);
        zoomSlider.setShowTickMarks(true);
        zoomSlider.setMajorTickUnit(2);
        zoomSlider.setBlockIncrement(increment);
        zoomSlider.setMin(0);
        zoomSlider.setMax(10);

        zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                webContent.setZoom(newValue.doubleValue());
                zoom = newValue.doubleValue();
            }
        });
    }

    private void createProgressReport(WebEngine engine) {
        final LongProperty startTime = new SimpleLongProperty();
        final LongProperty endTime = new SimpleLongProperty();
        final LongProperty elapsedTime = new SimpleLongProperty();

        // compute elapsed time
        elapsedTime.bind(Bindings.subtract(endTime, startTime));
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
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

    public void initializeHtmlViewer() {
        webEngine = webContent.getEngine();
        webContent.setStyle("-fx-font-size:15px;");
        webEngine.load("http://www.microcis.net/news/");
    }

    public void initOfflineNews() {
        offlineNewsList.clear();
        listViewNews.getItems().clear();
        offlineNewsList.addAll(new TableNews().getAll());
        filteredNewsList = new FilteredList<>(offlineNewsList, data -> true);
        listViewNews.setItems(filteredNewsList);

        listViewNews.setCellFactory(createNewsListViewCellFactory());

        listViewNews.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<News>() {
            @Override
            public void changed(ObservableValue<? extends News> observableValue, News oldValue, News newValue) {
                try {
                    if (newValue != null) {
                        webEngine.loadContent(newValue.getContent());
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private void filterTutorialDetails(String searchText, FilteredList<News> filteredList) {
        filteredList.setPredicate(data -> {
            if(searchText == null || searchText.isEmpty()) {
                return true;
            }

            if(data.getTitle().contains(searchText)) {
                return true;
            } else {
                return false;
            }
        });
    }

    private Callback<ListView<News>, ListCell<News>> createNewsListViewCellFactory() {
        return new Callback<ListView<News>, ListCell<News>>() {
            @Override
            public ListCell<News> call(ListView<News> param) {
                final Label leadLbl = new Label();
                FontIcon listIcon = new FontIcon("bi-journal-text");
                listIcon.setIconSize(16);

                final ListCell<News> cell = new ListCell<News>() {
                    @Override
                    protected void updateItem(News item, boolean empty) {
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
