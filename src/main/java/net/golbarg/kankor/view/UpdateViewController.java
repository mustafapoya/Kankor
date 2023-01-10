package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import net.golbarg.kankor.model.Download;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnResume;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnClear;

    //
    @FXML
    private TableView<Download> tableUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
