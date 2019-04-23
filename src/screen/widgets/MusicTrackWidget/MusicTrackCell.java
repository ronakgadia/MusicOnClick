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
    private Label trackArtistName;

    @FXML
    private Label trackAlbumName;

    @FXML
    private Button trackPlayButton;

    @FXML
    private Label trackDuration;

    private String trackUrl;

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
        if (empty || trackTitle == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            trackTitle.setText(item.name);

            String text = "";
            for (int i = 0; i < item.artists.size() - 1; i++) {
                text = text + item.artists.get(i) + ",";
            }
            text = text + item.artists.get(item.artists.size() - 1);
            trackArtistName.setText(text);

            int time = (int) item.duration_ms / 1000;
            int timeInMin = time / 60;
            int timeInSec = time % 60;
            trackDuration.setText(timeInMin + ":" + timeInSec);

            trackUrl = item.preview_url;
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        /*trackPlayButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (trackUrl != null) {
                    Router.mediaPlayer = new MediaPlayer(new Media(trackUrl));
                }
            }
        });*/
    }
}
