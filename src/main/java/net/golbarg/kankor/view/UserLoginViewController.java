package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import net.golbarg.kankor.MainApp;
import net.golbarg.kankor.db.TableLocation;
import net.golbarg.kankor.db.TableUser;
import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.User;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginViewController implements Initializable {

    @FXML
    private ImageView imgUserImage;
    @FXML
    private ComboBox comboUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;

    Stage parentStage;
    ObservableList<User> userList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshData();

        Callback<ListView<User>, ListCell<User>> cellFactory = new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell call(ListView<User> param) {
                final Label lblLead = new Label();
                FontIcon iconUser = new FontIcon("fas-user");
                iconUser.setIconSize(14);

                final ListCell<User> cell = new ListCell<User>(){
                    @Override
                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            lblLead.setText(item.getName() + " " + item.getLastName());
                            setText(item.getName() + " " + item.getLastName());
                            setGraphic(iconUser);
                        } else {
                            lblLead.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };

                return cell;
            }
        };

        comboUsername.setButtonCell(cellFactory.call(null));
        comboUsername.setCellFactory(cellFactory);
        comboUsername.getSelectionModel().selectFirst();

        btnLogin.setOnAction(event -> {
            if(!comboUsername.getSelectionModel().isEmpty()) {
                User user = (User) comboUsername.getSelectionModel().getSelectedItem();
                String password = txtPassword.getText();
                if(user.getPassword().equals(password)) {
                    System.out.println("Login Successful");
                    txtPassword.getStyleClass().removeIf(style -> style.equals("input-error"));
                } else {
                    System.out.println("Login Failed");
                    txtPassword.getStyleClass().add("input-error");
                }
            }
        });

        btnRegister.setOnAction(event -> {
            try {
                parentStage.hide();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-register-view.fxml"));
                BorderPane root = fxmlLoader.load();
                UserRegisterControllerView controller = fxmlLoader.getController();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(MainApp.class.getResource("style.css").toExternalForm());
                Stage stage = new Stage();
                stage.setTitle("Register");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        parentStage.show();
                        refreshData();
                    }
                });

                controller.getBtnCancel().setOnAction(event1 -> {
                    stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
                });

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void setStage(Stage stage) {
        this.parentStage = stage;
    }

    private void closeSplash() {
        if(this.parentStage != null) {
            this.parentStage.close();
        }
    }

    private void refreshData() {
        userList.clear();
        userList.addAll(new TableUser().getAll());
        comboUsername.setItems(userList);
    }
}
