import javafx.application.Application;
import javafx.stage.Stage;
import utils.Router;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Router.bind(this, primaryStage, "MusicOnClick", 1000, 600);
        Router.when("splash", "Screen/splash/SplashFxml.fxml");
        Router.when("login", "Screen/login/LoginFxml.fxml");
        Router.when("signup", "Screen/signup/SignupFxml.fxml");
        Router.goTo("splash");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
