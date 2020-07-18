package Orondo.ventas;

import Orondo.Styling.Styler;
import Orondo.inicio.Locations;
import Orondo.inicio.mainController;
import Orondo.productos.modificarController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ventasController {
    
    @FXML
    public AnchorPane AnchorPane_bp;
    
    @FXML
    public BorderPane BorderPane_bp;
    
    @FXML
    public VBox VBox_Atras;
    
    @FXML
    public VBox VBox_HacerVentas;
    
    @FXML
    public VBox VBox_Monitorear;
    
    public mainController maincont;
    
    public void initialize() {
        Styler.AddFadingAnimation_onHover(VBox_Atras);
        Styler.AddFadingAnimation_onHover(VBox_HacerVentas);
        Styler.AddFadingAnimation_onHover(VBox_Monitorear);
    }
    
    @FXML
    public void onClick_VBox_Atras(MouseEvent event) throws IOException{
  //      Locations.CambiarStage(AnchorPane_bp, new mainController(), Locations.main_fxml);
    }
    
    @FXML
    public void onClick_VBox_HacerVentas(MouseEvent event) throws IOException{
//        Locations.CambiarStage(BorderPane_bp, new hacerVentasController(), Locations.hacer_ventas_fxml);
    }
    
    @FXML
    public void onClick_VBox_Monitorear(MouseEvent event){
        
    }
}
