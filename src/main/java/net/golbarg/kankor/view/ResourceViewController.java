package net.golbarg.kankor.view;

import com.dansoftware.pdfdisplayer.PDFDisplayer;
import com.dansoftware.pdfdisplayer.PdfJSVersion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.golbarg.kankor.custom.CellFactorySample;
import net.golbarg.kankor.db.TableLocation;
import net.golbarg.kankor.db.TableResource;
import net.golbarg.kankor.db.TableResourceCategory;
import net.golbarg.kankor.db.TableTutorialDetail;
import net.golbarg.kankor.model.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResourceViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Button btnOpenFile;
    @FXML
    private ComboBox<ResourceCategory> comboCategory;
    @FXML
    private ListView<Resource> listViewResources;
    @FXML
    private BorderPane pdfContainer;

    private ObservableList<ResourceCategory> categoryList = FXCollections.observableArrayList();
    private ObservableList<Resource> resourceList = FXCollections.observableArrayList();
    FilteredList<Resource> filteredResourceList;
    Resource currentResource;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // list view of resources
        resourceList.addAll(new TableResource().getAll());
        filteredResourceList = new FilteredList<>(resourceList, data -> true);

        listViewResources.setItems(filteredResourceList);
        listViewResources.setCellFactory(createResourceListViewCellFactory());

        listViewResources.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Resource>() {
            @Override
            public void changed(ObservableValue<? extends Resource> observable, Resource oldValue, Resource newValue) {
                if (newValue != null) {
                    try {
                        PDFDisplayer displayer = new PDFDisplayer(PdfJSVersion._2_2_228);
                        pdfContainer.setCenter(displayer.toNode());
                        File file = new File("assets/resource/" + newValue.getFileName());
                        displayer.loadPDF(file);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // combo box
        categoryList.addAll(new TableResourceCategory().getAll());
        comboCategory.setItems(categoryList);
        Callback<ListView<ResourceCategory>, ListCell<ResourceCategory>> cellFactoryResourceCategory
                = CellFactorySample.getComboBoxResourceCategory(null, 0);

        comboCategory.setButtonCell(cellFactoryResourceCategory.call(null));
        comboCategory.setCellFactory(cellFactoryResourceCategory);

        comboCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ResourceCategory>() {
            @Override
            public void changed(ObservableValue<? extends ResourceCategory> observableValue, ResourceCategory oldValue, ResourceCategory newValue) {
                filterResources(newValue, filteredResourceList);
            }
        });
        comboCategory.getSelectionModel().selectFirst();

    }

    private void filterResources(ResourceCategory newValue, FilteredList<Resource> filteredList) {
        filteredList.setPredicate(data -> {
            if(newValue == null) {
                return true;
            }
            int tutorialId = newValue.getId();

            if(data.getCategory().getId() == tutorialId) {
                return true;
            } else {
                return false;
            }
        });
    }

    private Callback<ListView<Resource>, ListCell<Resource>> createResourceListViewCellFactory() {
        return new Callback<ListView<Resource>, ListCell<Resource>>() {
            @Override
            public ListCell<Resource> call(ListView<Resource> param) {
                final Label leadLbl = new Label();
                FontIcon listIcon = new FontIcon("bi-file-earmark-richtext");
                listIcon.setIconSize(16);

                final ListCell<Resource> cell = new ListCell<Resource>() {
                    @Override
                    protected void updateItem(Resource item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            leadLbl.setText(item.getName().trim());
                            setText(item.getName().trim());
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
