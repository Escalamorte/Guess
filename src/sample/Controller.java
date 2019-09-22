package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    public VBox vbox;
    public TextField usernameField;
    public PasswordField passwordFiled;
    public AnchorPane welcomePane;
    public TextField newUserNameFiled;
    public PasswordField newPassField;
    public PasswordField newPassRepeatFiled;

    @FXML
    private Parent fxml;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void open_sgnUp() {
        TranslateTransition t= new TranslateTransition(Duration.seconds(0.3), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("View/SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void open_sngIn(ActionEvent actionEvent) {
        TranslateTransition t= new TranslateTransition(Duration.seconds(0.3), vbox);
        t.setToX(vbox.getLayoutX() + 310);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("View/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    public void auth(ActionEvent actionEvent) {
        String userName = usernameField.getText().trim();
        String password = passwordFiled.getText().trim();

        if(userName.equals("elnur") & password.trim().equals("elnur")) {
            try {
                fxml = FXMLLoader.load(getClass().getResource("View/MainWindow.fxml"));
                Scene scene = new Scene(fxml);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();

                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error!");
            System.out.println(passwordFiled.getText());
        }
    }

    public void register() {
        String newUserName = newUserNameFiled.getText().trim();
        String newPassword = newPassField.getText().trim();
        String newPassRepeat = newPassRepeatFiled.getText().trim();

        DBHandler dbHandler = new DBHandler();
        if((!newUserName.equals("") || !newPassword.equals("") || !newPassRepeat.equals("")) && newPassword.equals(newPassRepeat)){
            dbHandler.singUp(newUserName, newPassword);
        }
    }


    public void checkField(ActionEvent actionEvent) {
        System.out.println("here");
    }
}
