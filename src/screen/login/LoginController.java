package screen.login;

import data.APIDataManager;
import data.DatabaseManager;
import data.PreferenceManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.Router;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField usernameText;

    @FXML
    public PasswordField passwordText;
    public Label invalid;

    public void loginUser(MouseEvent event) {
        String username, password;
        username = usernameText.getText().toString();
        password = passwordText.getText().toString();
        DatabaseManager databasemanager=DatabaseManager.getInstance();
        if(databasemanager.isUserValid(username,password)) {
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
        else
        {
            try {

                Router.goTo("login");
                invalid.setText("Invalid Username or password entered");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Not a valid user");
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
