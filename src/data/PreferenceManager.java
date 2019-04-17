package data;

import java.util.prefs.Preferences;

public class PreferenceManager {
    private Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    public void loginUser() {
        preferences.putBoolean("isLoggedIn",true);
    }

    public void logoutUser() {
        preferences.getBoolean("isLoggedIn",false);
    }

    public boolean isUserLoggedIn() {
        return preferences.getBoolean("isLoggedIn",true);
    }
}