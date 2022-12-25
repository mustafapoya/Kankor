package net.golbarg.kankor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        loadMainView(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void loadMainView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Kankor");
        stage.setScene(scene);
        stage.show();
    }
}