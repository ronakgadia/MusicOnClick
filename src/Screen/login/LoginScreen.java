package Screen.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFxml.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("MusicOnCLick");
        primaryStage.setScene(new Scene(root,1000,600));
        primaryStage.show();
    }

    public static void  main(String[] args) {
        launch(args);
    }
}
