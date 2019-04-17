package Screen.signup;

import data.PreferenceManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.Router;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField usernameText;

    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private PasswordField confirmPasswordText;

    public void signupUser(MouseEvent event) {
        String username, email, password, confirmPassword;
        username = usernameText.getText();
        email = emailText.getText();
        password = passwordText.getText();
        confirmPassword = confirmPasswordText.getText();

        if (password != confirmPassword) {

        }
        else {
            PreferenceManager preferenceManager = new PreferenceManager();
            preferenceManager.loginUser();
        }
    }

    public void goToLogin(MouseEvent event) {
        try {
            Router.goTo("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
