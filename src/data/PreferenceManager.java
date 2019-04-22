package data;

import utils.Router;

import java.io.IOException;
import java.util.prefs.Preferences;

public class PreferenceManager {
    private static PreferenceManager instance = null;
    private Preferences preferences;

    private PreferenceManager() {
        preferences = Preferences.userNodeForPackage(this.getClass());
        System.out.println("In preference manager constructor");
    }

    public static PreferenceManager getInstance() {
        if (instance == null) {
            instance = new PreferenceManager();
        }
        return instance;
    }

    public void loginUser() {
        preferences.putBoolean("isLoggedIn", true);
    }

    public void logoutUser() {
        preferences.putBoolean("isLoggedIn", false);
        try {
            Router.goTo("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserLoggedIn() {
        return preferences.getBoolean("isLoggedIn", false);
    }

    public String getToken() {
        return preferences.get("token", null);
    }

    public void setToken(String tokenString) {
        System.out.println("Token in preferences:" + tokenString);
        preferences.put("token", tokenString);
    }

}