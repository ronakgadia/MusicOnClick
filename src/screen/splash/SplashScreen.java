package screen.splash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SplashScreen  extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SplashFxml.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("MusicOnCLick");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }
}
