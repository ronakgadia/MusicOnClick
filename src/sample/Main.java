package sample;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(new Button());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(stackPane, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
