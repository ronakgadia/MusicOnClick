package screen.widgets.MusicTrackWidget;

import data.dto.Track;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import screen.widgets.MusicTrackWidget.MusicTrackCell;

public class MusicTrackCellFactory implements Callback<ListView<Track>, ListCell<Track>> {
    @Override
    public ListCell<Track> call(ListView<Track> param) {
        return new MusicTrackCell();
    }
}

