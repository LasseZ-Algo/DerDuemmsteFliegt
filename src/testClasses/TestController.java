package testClasses;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TestController {

    @FXML
    private Label label;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, deine Mutter nutzt JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }
}