package screen.homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import data.APIDataManager;
import data.PreferenceManager;
import data.dto.Playlist;
import data.dto.Album;
import data.dto.Track;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import screen.widgets.MusicAlbumWidget.MusicAlbumCellFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    public JFXHamburger hamburger;

    @FXML
    public JFXDrawer drawer;

    @FXML
    public VBox drawer_pane;

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
    public JFXButton main_btn3;
    @FXML
    public Label stoptime;
    @FXML
    public Label starttime;

    @FXML
    private ListView listView;

    private static int click = 0;
    private static int fast_click = 0;
    private static int slow_click = 0;
    private MediaPlayer mp;
    private List myList = new ArrayList<>();
    private ObservableList myObservableList = FXCollections.observableList(myList);
    private APIDataManager apiDataManager = new APIDataManager();

    public HomePageController() {
        URL mediaUrl = getClass().getResource("../../images/ashqui.mp3");
        String mediaStringUrl = mediaUrl.toExternalForm();
        System.out.println(mediaStringUrl);
        Media media = new Media(mediaStringUrl);
        this.mp = new MediaPlayer(media);
    }

    public void playSong(MouseEvent event) {
        click++;
        if (click % 2 == 1) {
            playtime.setGlyphName("PAUSE");
            mp.play();
            System.out.println("Clicked to play");
        } else {
            playtime.setGlyphName("PLAY");
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
    public void logout(){
        PreferenceManager preferenceManager=PreferenceManager.getInstance();
        preferenceManager.logoutUser();
    }

    private void displayNewReleases() {
        listView.getItems().clear();
        myList.clear();
        ArrayList<Album> albums = apiDataManager.getNewReleases();
        myList.addAll(albums);
        listView.setItems(myObservableList);
        listView.setCellFactory(new MusicAlbumCellFactory());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayNewReleases();
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

        drawer.setSidePane(drawer_pane);
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }
}