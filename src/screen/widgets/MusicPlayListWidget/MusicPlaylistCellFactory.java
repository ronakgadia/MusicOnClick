package screen.widgets.MusicPlayListWidget;

import data.dto.Playlist;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MusicPlaylistCellFactory implements Callback<ListView<Playlist>, ListCell<Playlist>> {
    @Override
    public ListCell<Playlist> call(ListView<Playlist> param) {
        return new MusicPlaylistCell();
    }
}