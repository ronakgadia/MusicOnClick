package screen.homepage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController extends Parent implements Initializable {

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

    public  MediaPlayer mp;
     HomePageController(final MediaPlayer mp)
    {
        this.mp=mp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int click = 0;

        playtime.setOnMouseClicked(new EventHandler<MouseEvent>() {
             click=click+1;
            @Override
            public void handle(MouseEvent event) {

                if(click%2==1){
                    System.out.println("hello");
                    mp.play();
                }
                else{
                    mp.pause();
                }
            }
        });
    }
}