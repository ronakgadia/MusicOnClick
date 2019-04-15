package sample;

import com.sun.deploy.panel.TextFieldProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button btn;
    public TextArea enter;
    public Label lab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void message(ActionEvent actionEvent) {
    String name= enter.textProperty().get();
    lab.textProperty().set("Your name is"+name);

    }

}
