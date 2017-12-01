package jfxwebview;

import javafx.application.Platform;

public class JavaApp {
    public String getGreeting() {
        return "Hello from Java";
    }

    public void sayHi(String to) {
        System.out.println("Hello, " + to);
    }

    public void exit() {
        Platform.exit();
    }
}

