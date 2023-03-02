package net.golbarg.kankor;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.golbarg.kankor.view.UserLoginViewController;

import java.io.IOException;

public class MainApp extends Application {
    public static Stage stage;
    public static HostServices hostServices;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.hostServices = getHostServices();
//        loadMainView();
        loadView("view/news-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    private void loadMainView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view/main-view.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Kankor");
        stage.setScene(scene);
        stage.show();
    }
    
    private void displayLoginPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view/user-login-view.fxml"));
        BorderPane root = fxmlLoader.load();
        UserLoginViewController controller = fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Kankor");
        stage.setScene(scene);
        stage.show();
    }

    private void loadView(String viewPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(viewPath));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Kankor");
        stage.setScene(scene);
        stage.show();
    }
}