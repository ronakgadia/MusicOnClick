import screen.splash.SplashController;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.Router;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Router.bind(this, primaryStage, "MusicOnClick", 1000, 600);
        Router.when("splash", "screen/splash/SplashFxml.fxml");
        Router.when("login", "screen/login/LoginFxml.fxml");
        Router.when("signup", "screen/signup/SignupFxml.fxml");
        Router.goTo("splash");
        SplashController splashController = new SplashController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
