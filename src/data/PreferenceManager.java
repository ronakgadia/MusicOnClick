package data;

import java.util.prefs.Preferences;

public class PreferenceManager {
    private Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    void loginUser(String username,String password) {
        preferences.put("username",username);
        preferences.put("password",password);
        preferences.putBoolean("isLoggedIn",true);
    }

    void logoutUser() {
        preferences.put("username","");
        preferences.put("password","");
        preferences.getBoolean("isLoggedIn",false);
    }

    boolean isUserLoggedIn() {
        return preferences.getBoolean("isLoggedIn",true);
    }
}