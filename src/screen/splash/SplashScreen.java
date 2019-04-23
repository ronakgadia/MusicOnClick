package screen.splash;

import data.PreferenceManager;
import utils.Router;

import java.io.IOException;

public class SplashScreen {
    public SplashScreen() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PreferenceManager preferenceManager = PreferenceManager.getInstance();
        if
        (preferenceManager.isUserLoggedIn()) {
            System.out.println("User is logged in:"+preferenceManager.getToken());
            try {
                Router.goTo("homepage");
            } catch (IOException e) {
                e.printStackTrace();
            }
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
