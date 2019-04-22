package screen.widgets.MusicAlbumWidget;

import data.dto.Album;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import screen.tracklist.TrackListController;
import utils.Router;

import java.io.IOException;

public class MusicAlbumCell extends ListCell<Album> {
    @FXML
    private ImageView albumImageText;

    @FXML
    private Label albumNameText;

    @FXML
    private Label albumTypeText;

    @FXML
    private Label albumArtistText;

    @FXML
    private Label albumReleaseText;

    @FXML
    private Button nextPage;

    public MusicAlbumCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicAlbumFxml.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Album item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || albumNameText == null || albumTypeText == null || albumArtistText == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            albumNameText.setText(item.name);
            albumTypeText.setText(item.album_type);
            String text = "";
            for (int i = 0; i < item.artists.size(); i++) {
                text = text + item.artists.get(i);
            }
            albumArtistText.setText(text);
            albumReleaseText.setText(item.release_date);
            if (item.image != null && item.image != "")
                albumImageText.setImage(new Image(item.image));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        nextPage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                try {
                    TrackListController.type = "Album";
                    TrackListController.object = item;
                    Router.goTo("tracklist");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
