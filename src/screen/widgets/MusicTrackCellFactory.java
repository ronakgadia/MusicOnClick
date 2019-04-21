package screen.widgets;

import data.dto.Track;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MusicTrackCellFactory implements Callback<ListView<Track>, ListCell<Track>> {
    @Override
    public ListCell<Track> call(ListView<Track> param) {
        return new MusicTrackCell();
    }
}

