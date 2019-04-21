package screen.tracklist;

import data.APIDataManager;
import data.dto.Playlist;
import data.dto.Track;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import screen.widgets.MusicTrackCellFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TrackListController implements Initializable {


    @FXML
    public Slider timeSlider;

    @FXML
    public FontAwesomeIcon backward;

    @FXML
    public FontAwesomeIcon playtime;

    @FXML
    public FontAwesomeIcon forward;

    @FXML
    public FontAwesomeIcon volume;

    @FXML
    public Slider volumeSlider;

    @FXML
    private ListView<Track> listView;

    private static int click = 0;
    static int fast_click = 0;
    static int slow_click = 0;
    private MediaPlayer mp;

    public TrackListController() {
        URL mediaUrl = getClass().getResource("../../images/ashqui.mp3");
        String mediaStringUrl = mediaUrl.toExternalForm();
        Media media = new Media(mediaStringUrl);
        MediaPlayer mp2 = new MediaPlayer(media);
        this.mp = mp2;
    }

    public void playSong(MouseEvent event) {
        click++;
        if (click % 2 == 1) {
            mp.play();
            System.out.println("Clicked to play");
        } else {
            FontAwesomeIcon pause = new FontAwesomeIcon();
            mp.pause();
            System.out.println("Clicked to pause");
        }
    }

    public void fast(MouseEvent event) {
        fast_click++;
        if (fast_click % 22 == 1)
            mp.setRate(2);
        else {
            mp.setRate(1);
        }
    }

    public void slow(MouseEvent event) {
        slow_click++;
        if (slow_click % 2 == 1) {
            mp.setRate(.5);
        } else {
            mp.setRate(1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        APIDataManager apiDataManager = new APIDataManager();
        ArrayList<Playlist> playlists = apiDataManager.getFeaturedPlaylist();
        List<Track> myList = new ArrayList<Track>();

        ArrayList<Track> tracks = apiDataManager.getTracksFromAPlaylist(playlists.get(0));
        for (Track track : tracks) {
            if (track.preview_url != null)
                myList.add(track);
        }
        ObservableList<Track> myObservableList = FXCollections.observableList(myList);
        listView.setItems(myObservableList);
        listView.setCellFactory(new MusicTrackCellFactory());
        volumeSlider.setValue(mp.getVolume() * 100.0);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volumeSlider.getValue() / 100);
            }
        });

        mp.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (mp.getCurrentTime() != null && mp.getStopTime() != null) {
                    timeSlider.setValue(mp.getCurrentTime().toMillis() / mp.getStopTime().toMillis() * 100);
                }
            }
        });


        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    mp.seek(new Duration((timeSlider.getValue() / 100) * mp.getStopTime().toMillis()));
                }
            }
        });
    }
}