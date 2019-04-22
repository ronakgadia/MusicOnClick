package screen.widgets.MusicAlbumWidget;

import data.dto.Album;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MusicAlbumCellFactory implements Callback<ListView<Album>, ListCell<Album>> {
    @Override
    public ListCell<Album> call(ListView<Album> param) {
        return new MusicAlbumCell();
    }
}