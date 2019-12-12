package Orondo.main;

import Orondo.Styling.Styler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {    

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource(Locations.main_fxml));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(new Styler().getClass().getResource(Locations.main_css).toExternalForm());
        
        stage.setTitle("ORONDO");
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
