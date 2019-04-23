package screen.tracklist;

import data.APIDataManager;
import data.dto.Album;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import screen.widgets.MusicAlbumWidget.MusicAlbumCellFactory;
import screen.widgets.MusicPlayListWidget.MusicPlaylistCell;
import screen.widgets.MusicTrackWidget.MusicTrackCellFactory;
import utils.Router;

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

    @FXML
    private ImageView albumImage;

    private static int click = 0;
    private static int fast_click = 0;
    private static int slow_click = 0;
    static public String type;
    static public Object object;

    private List<Track> myList = new ArrayList<Track>();
    private ObservableList<Track> myObservableList = FXCollections.observableList(myList);

    private APIDataManager apiDataManager = new APIDataManager();

    public void playSong1(MouseEvent event) {
        click++;
        if (click % 2 == 1) {
            Router.mediaPlayer.play();
            System.out.println("Clicked to play");
        } else {
            Router.mediaPlayer.pause();
            System.out.println("Clicked to pause");
        }
    }

    public void fast1(MouseEvent event) {
        fast_click++;
        if (fast_click % 2 == 1)
            Router.mediaPlayer.setRate(2);
        else {
            Router.mediaPlayer.setRate(1);
        }
    }

    public void slow1(MouseEvent event) {
        slow_click++;
        if (slow_click % 2 == 1) {
            Router.mediaPlayer.setRate(.5);
        } else {
            Router.mediaPlayer.setRate(1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (type.equals("Album")) {
            Album album = (Album) object;
            if (album != null) {
                ArrayList<Track> tracks = apiDataManager.getTrackFromAnAlbum(album.id);
                if (tracks != null) {
                    myList.addAll(tracks);
                    listView.setItems(myObservableList);
                    listView.setCellFactory(new MusicTrackCellFactory());
                }
                if (album.image != null) {
                    albumImage.setImage(new Image(album.image));
                }
            }
        } else if (type.equals("Playlist")) {
            Playlist playlist = (Playlist) object;
            ArrayList<Track> tracks = apiDataManager.getTracksFromAPlaylist(playlist.id);
            myList.addAll(tracks);
            listView.setItems(myObservableList);
        }
        volumeSlider.setValue(Router.mediaPlayer.getVolume() * 100.0);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Router.mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });

        Router.mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (Router.mediaPlayer.getCurrentTime() != null && Router.mediaPlayer.getStopTime() != null) {
                    timeSlider.setValue(Router.mediaPlayer.getCurrentTime().toMillis() / Router.mediaPlayer.getStopTime().toMillis() * 100);
                }
            }
        });

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    Router.mediaPlayer.seek(new Duration((timeSlider.getValue() / 100) * Router.mediaPlayer.getStopTime().toMillis()));
                }
            }
        });
    }
}