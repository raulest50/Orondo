/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.productos;

import Orondo.Styling.Styler;
import Orondo.main.Locations;
import Orondo.main.mainController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Raul Alzate
 */
public class productosController {
    
    /**
     * bp = bigpane. el panel padre o root panel
     */
    @FXML
    public AnchorPane AnchorPane_bp;
    
    @FXML
    public AnchorPane AnchorPane_center;
    
    @FXML
    public BorderPane BorderPane_bp;

    @FXML
    public VBox VBox_Atras;

    @FXML
    public VBox VBox_Codificar;

    @FXML
    public VBox VBox_Modificar;
    
    public void initialize() {
        Styler.AddFadingAnimation_onHover(VBox_Atras);
        Styler.AddFadingAnimation_onHover(VBox_Codificar);
        Styler.AddFadingAnimation_onHover(VBox_Modificar);
    }
    
    @FXML
    public void onClick_VBox_Atras(MouseEvent event) throws IOException {
        Locations.CambiarStage(AnchorPane_bp, new mainController(), Locations.main_fxml);
    }

    @FXML
    public void onClick_VBox_Codificar(MouseEvent event) throws IOException {
        Locations.CambiarStage(BorderPane_bp, new codificarController(), Locations.codificar_fxml);
        //Locations.CambiarStage(BorderPane_bp, new codificarController(), Locations.codificar_fxml);
    }

    @FXML
    public void onClick_VBox_Modificar(MouseEvent event) throws IOException {
        Locations.CambiarStage(BorderPane_bp, new modificarController(), Locations.modificar_fxml);
    }
    
}
