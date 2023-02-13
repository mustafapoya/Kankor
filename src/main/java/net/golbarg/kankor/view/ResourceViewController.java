package net.golbarg.kankor.view;

import com.dansoftware.pdfdisplayer.PDFDisplayer;
import com.dansoftware.pdfdisplayer.PdfJSVersion;
import javafx.application.Platform;
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
import net.golbarg.kankor.custom.ListViewCellFactory;
import net.golbarg.kankor.db.TableLocation;
import net.golbarg.kankor.db.TableResource;
import net.golbarg.kankor.db.TableResourceCategory;
import net.golbarg.kankor.db.TableTutorialDetail;
import net.golbarg.kankor.model.*;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.swing.plaf.BorderUIResource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResourceViewController implements Initializable {
    @FXML
    private BorderPane root;
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

        //
        listViewResources.setItems(filteredResourceList);
        ListViewCellFactory<Resource> resourceCellFactory = new ListViewCellFactory<>("bi-file-earmark-richtext", 16);
        listViewResources.setCellFactory(resourceCellFactory.createCellFactory());

        listViewResources.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Resource>() {
            @Override
            public void changed(ObservableValue<? extends Resource> observable, Resource oldValue, Resource newValue) {
                if (newValue != null) {
                    currentResource = newValue;

                    PDFDisplayer displayer = new PDFDisplayer(PdfJSVersion._2_2_228);
                    pdfContainer.setCenter(displayer.toNode());
                    File file = new File("assets/resource/" + newValue.getFileName());
                    Platform.runLater(() -> {
                        try {
                            displayer.loadPDF(file);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
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
}
