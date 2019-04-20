package screen.home;

import data.APIDataManager;

public class Home {

    public Home() {
        APIDataManager apiDataManager = new APIDataManager();
        apiDataManager.getFeaturedPlaylist();
    }
}