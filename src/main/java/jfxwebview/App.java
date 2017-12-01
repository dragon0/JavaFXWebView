package jfxwebview;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class App extends Application {

    WebView browser;
    WebEngine engine;
    JSObject jsapi;

    @Override
    public void start(Stage stage) throws Exception {
        browser = new WebView();
        engine = browser.getEngine();
        engine.load(getClass().getResource("index.html").toExternalForm());
        JSObject win = (JSObject) engine.executeScript("window");

        //win.setMember("app", new JavaApp());
        JavaApp ja = new JavaApp();
        win.setMember("app", ja);

        ConsoleShim c = new ConsoleShim();
        win.setMember("console", c);

        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    @Override
                    public void changed(ObservableValue<? extends State> ov,
                            State oldState, State newState) {
                        if (newState == State.SUCCEEDED) {
                            jsapi = (JSObject) engine.executeScript("api");
                            System.out.println(jsapi.call("getGreeting"));
                        }
                    }
                }
        );

        String pageTitle = engine.getTitle();
        stage.setTitle(pageTitle);

        Scene scene = new Scene(browser);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
