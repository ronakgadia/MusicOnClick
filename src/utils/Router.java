package utils;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.HashMap;


public final class Router {
    private static final String WINDOW_TITLE = "";
    private static final Double WINDOW_WIDTH = 1000.0;
    private static final Double WINDOW_HEIGHT = 600.0;
    private static final Double FADE_ANIMATION_DURATION = 800.0;
    private static final boolean IS_WINDOW_RESIZABLE = true;
    public static MediaPlayer mediaPlayer = new MediaPlayer(new Media("file:/C:/Users/User/Documents/GitHub/MusicOnClick/src/images/ashqui.mp3"));
    // FXRouter Singleton
    private static Router router;
    // FXRouter Main Class reference to get main package
    private static Object mainRef;
    // FXRouter Application Stage reference to set scenes
    private static Stage window;

    private static String windowTitle;
    private static Double windowWidth;
    private static Double windowHeight;
    // routes switching animation
    private static String animationType;
    private static Double animationDuration;

    // FXRouter routes map
    private static AbstractMap<String, RouteScene> routes = new HashMap<>();
    // FXRouter current route
    private static RouteScene currentRoute;

    /**
     * FXRouter Inner Class used into routes map
     */
    private static class RouteScene {
        // route .fxml Scene path
        private String scenePath;
        // Scene (Stage) title
        private String windowTitle;
        private double sceneWidth;
        private double sceneHeight;
        // route data passed from goTo()
        private Object data;

        private RouteScene(String scenePath) {
            this(scenePath, getWindowTitle(), getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, String windowTitle) {
            this(scenePath, windowTitle, getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, double sceneWidth, double sceneHeight) {
            this(scenePath, getWindowTitle(), sceneWidth, sceneHeight);
        }


        private RouteScene(String scenePath, String windowTitle, double sceneWidth, double sceneHeight) {
            this.scenePath = scenePath;
            this.windowTitle = windowTitle;
            this.sceneWidth = sceneWidth;
            this.sceneHeight = sceneHeight;
        }

        private static String getWindowTitle() {
            return router.windowTitle != null ? router.windowTitle : WINDOW_TITLE;
        }

        private static double getWindowWidth() {
            return router.windowWidth != null ? router.windowWidth : WINDOW_WIDTH;
        }

        private static double getWindowHeight() {
            return router.windowHeight != null ? router.windowHeight : WINDOW_HEIGHT;
        }
    }

    /**
     * FXRouter constructor kept private to apply Singleton pattern
     */
    private Router() {
    }

    public static void bind(Object ref, Stage win) {
        checkInstances(ref, win);
    }

    public static void bind(Object ref, Stage win, String winTitle) {
        checkInstances(ref, win);
        windowTitle = winTitle;
    }

    public static void bind(Object ref, Stage win, double winWidth, double winHeight) {
        checkInstances(ref, win);
        windowWidth = winWidth;
        windowHeight = winHeight;
    }

    public static void bind(Object ref, Stage win, String winTitle, double winWidth, double winHeight) {
        checkInstances(ref, win);
        windowTitle = winTitle;
        windowWidth = winWidth;
        windowHeight = winHeight;
    }

    private static void checkInstances(Object ref, Stage win) {
        if (mainRef == null)
            mainRef = ref;
        if (router == null)
            router = new Router();
        if (window == null)
            window = win;
    }

    public static void when(String routeLabel, String scenePath) {
        RouteScene routeScene = new RouteScene(scenePath);
        routes.put(routeLabel, routeScene);
    }

    public static void when(String routeLabel, String scenePath, String winTitle) {
        RouteScene routeScene = new RouteScene(scenePath, winTitle);
        routes.put(routeLabel, routeScene);
    }

    public static void when(String routeLabel, String scenePath, double sceneWidth, double sceneHeight) {
        RouteScene routeScene = new RouteScene(scenePath, sceneWidth, sceneHeight);
        routes.put(routeLabel, routeScene);
    }

    public static void when(String routeLabel, String scenePath, String winTitle, double sceneWidth, double sceneHeight) {
        RouteScene routeScene = new RouteScene(scenePath, winTitle, sceneWidth, sceneHeight);
        routes.put(routeLabel, routeScene);
    }

    public static void goTo(String routeLabel) throws IOException {
        // get corresponding route
        RouteScene route = routes.get(routeLabel);
        loadNewRoute(route);
    }

    public static void goTo(String routeLabel, Object data) throws IOException {
        // get corresponding route
        RouteScene route = routes.get(routeLabel);
        // set route data
        route.data = data;
        loadNewRoute(route);
    }

    private static void loadNewRoute(RouteScene route) throws IOException {
        // get Main Class package name to get correct files path
//        String pathRef = mainRef.getClass().getPackage().getName();

        // set FXRouter current route reference
        currentRoute = route;

        // create correct file path.  "/" doesn't affect any OS
        String scenePath = "/" + route.scenePath;

        // load .fxml resource
        Parent resource = FXMLLoader.load(new Object() {
        }.getClass().getResource(scenePath));

        // set window title from route settings or default setting
        window.setTitle(route.windowTitle);
        // set new route scene
        window.setScene(new Scene(resource, route.sceneWidth, route.sceneHeight));

        window.setResizable(IS_WINDOW_RESIZABLE);
        // show the window
        window.show();

        // set scene animation
        routeAnimation(resource);
    }

    public static void startFrom(String routeLabel) throws Exception {
        goTo(routeLabel);
    }

    public static void startFrom(String routeLabel, Object data) throws Exception {
        goTo(routeLabel, data);
    }

    public static void setAnimationType(String anType) {
        animationType = anType;
    }

    public static void setAnimationType(String anType, double anDuration) {
        animationType = anType;
        animationDuration = anDuration;
    }

    private static void routeAnimation(Parent node) {
        String anType = animationType != null ? animationType.toLowerCase() : "";
        switch (anType) {
            case "fade":
                Double fd = animationDuration != null ? animationDuration : FADE_ANIMATION_DURATION;
                FadeTransition ftCurrent = new FadeTransition(Duration.millis(fd), node);
                ftCurrent.setFromValue(0.0);
                ftCurrent.setToValue(1.0);
                ftCurrent.play();
                break;
            default:
                break;
        }
    }

    /**
     * Get current route data
     */
    public static Object getData() {
        return currentRoute.data;
    }

    public static void setMediaPlayer(MediaPlayer mp) {
        mediaPlayer = mp;
    }
}
