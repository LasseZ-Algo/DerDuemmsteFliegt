package graphicComponents;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("testoffx.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        String css = this.getClass().getResource("Style.css").toExternalForm();
        scene.getStylesheets().add(css);
        //scene2.getStylesheets().add(css);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
