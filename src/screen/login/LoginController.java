package screen.login;

import data.APIDataManager;
import data.PreferenceManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import screen.home.Home;
import utils.Router;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField usernameText;

    @FXML
    public PasswordField passwordText;

    public void loginUser(MouseEvent event) {
        String username, password;
        username = usernameText.getText();
        password = passwordText.getText();
        PreferenceManager preferenceManager = PreferenceManager.getInstance();
        APIDataManager apiDataManager = new APIDataManager();
        preferenceManager.loginUser();
        preferenceManager.setToken(apiDataManager.requestToken());
//        Home home = new Home();
        try {
            Router.goTo("homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToSignup() {
        try {
            Router.goTo("signup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
