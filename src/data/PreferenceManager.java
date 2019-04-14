package data;

import java.util.prefs.Preferences;

public class PreferenceManager {
    Preferences preferences = Preferences.userNodeForPackage(this.getClass());
}