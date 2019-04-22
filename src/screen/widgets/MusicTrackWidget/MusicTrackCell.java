package screen.widgets.MusicTrackWidget;

import data.dto.Track;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utils.Router;

import java.io.IOException;

public class MusicTrackCell extends ListCell<Track> {
    @FXML
    private ImageView trackImage;

    @FXML
    private Label trackTitle;

    @FXML
    private Label trackSubtitle;

    @FXML
    private Button playButton;

    private String TrackUrl;

    public MusicTrackCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicTrackFxml.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Track item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || trackTitle == null || trackSubtitle == null || trackImage == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            trackTitle.setText(item.name);
//            trackSubtitle.setText(item.trackAlbumName);
            if (item.trackAlbumImage != null)
                trackImage.setImage(new Image(item.trackAlbumImage));
            if (item.preview_url != null) {
                TrackUrl = item.preview_url;
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (TrackUrl != null) {
                    Router.mediaPlayer = new MediaPlayer(new Media(TrackUrl));
                }
            }
        });
    }
}
