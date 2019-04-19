package screen.splash;

import data.PreferenceManager;
import screen.home.Home;
import utils.Router;

import java.io.IOException;

public class SplashController {
    public SplashController() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PreferenceManager preferenceManager = PreferenceManager.getInstance();
        if (preferenceManager.isUserLoggedIn()) {
            System.out.println("User is logged in");
            Home home = new Home();
//               Router.goTo("home");
        } else {
            System.out.println("User is not logged in");
            try {
                Router.goTo("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}