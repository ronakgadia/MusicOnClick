import screen.splash.SplashScreen;
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
        Router.when("homepage", "screen/homepage/HomePageFxml.fxml");
        Router.goTo("splash");
        SplashScreen splashScreen = new SplashScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
