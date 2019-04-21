package screen.widgets.MusicPlayListWidget;

import data.dto.Playlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MusicPlaylistCell extends ListCell<Playlist> {
    @FXML
    private ImageView playlistImage;

    @FXML
    private javafx.scene.control.Label playlistTitle;

    @FXML
    private javafx.scene.control.Label playlistSubtitle;

    @FXML
    private Button playButton;

    public MusicPlaylistCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicPlaylistFxml.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Playlist item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || playlistTitle == null || playlistSubtitle == null || playlistImage == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
           /* playlistTitle.setText(item.name);
            playlistSubtitle.setText(item.trackAlbumName);
            if (item.AlbumImage != null)
                playlistImage.setImage(new Image(item.trackAlbumImage));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);*/
        }
    }
}
