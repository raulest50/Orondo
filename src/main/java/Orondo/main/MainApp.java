package Orondo.main;

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
        scene.getStylesheets().add(getClass().getResource(Locations.main_css).toExternalForm());

        stage.setTitle("Orondo from Tentactil");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
