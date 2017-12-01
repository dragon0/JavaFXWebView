package jfxwebview;

public class ConsoleShim {
    public void log(String message) {
        System.err.println(message);
    }
}

