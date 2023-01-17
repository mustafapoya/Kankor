package net.golbarg.kankor.view;

import com.dansoftware.pdfdisplayer.PDFDisplayer;
import com.dansoftware.pdfdisplayer.PdfJSVersion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PdfViewController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Button btnOpenFile;
    @FXML
    private ComboBox<String> comboCategory;
    @FXML
    private ListView<String> listViewResources;

    @FXML
    private VBox vbPdfContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            PDFDisplayer displayer = new PDFDisplayer(PdfJSVersion._2_2_228);
            root.setCenter(displayer.toNode());
            displayer.loadPDF(new File("C:\\Users\\mine\\Downloads\\Documents\\Mustafa new CV.pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
