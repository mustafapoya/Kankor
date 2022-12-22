package net.golbarg.kankor.util;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

public class FileChooseDialog {
    public static final ExtensionFilter IMAGE_FILTER = new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");

    private FileChooser fileChooser;
    private ExtensionFilter filter;
    private Stage parent;

    public FileChooseDialog(Stage parent, ExtensionFilter filter, String title) {
        this.parent = parent;

        fileChooser = new FileChooser();

        if(filter != null) {
            fileChooser.getExtensionFilters().add(filter);
        }

        if(title != null) {
            fileChooser.setTitle(title);
        }
    }

    public void setBaseLocation(String directory) {
        fileChooser.setInitialDirectory(new File(directory));
    }

    public File openDialog() {
        return fileChooser.showOpenDialog(parent);
    }

}
