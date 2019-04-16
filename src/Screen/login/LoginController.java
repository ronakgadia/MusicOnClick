package Screen.login;

import data.PreferenceManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    public TextField usernameText;

    @FXML
    public PasswordField passwordText;

    public void loginUser(MouseEvent event) {
        String username, password;
        username = usernameText.getText();
        password = passwordText.getText();
    }
}
