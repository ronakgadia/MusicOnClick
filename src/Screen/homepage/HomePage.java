package screen.homepage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HomePage extends Application {
   // private static final String MEDIA_URL ="../../images/ashqui.mp3";
     //    //  "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";

    public void start(Stage primaryStage) {


        try {
            Router.g
            URL mediaUrl = getClass().getResource("../../images/ashqui.mp3");
            String mediaStringUrl = mediaUrl.toExternalForm();
            Media media = new Media(mediaStringUrl);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            HomePageController homePageController = new HomePageController(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
