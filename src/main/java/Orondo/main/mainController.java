package Orondo.main;

//import java.net.URL;
//import java.util.ResourceBundle;
import Orondo.productos.productosController;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;




public class mainController {

    /**
    *estas variables se inyectan por defecto, las pongo como comentario
    *a manera de recordatorio por si en algun momento es util pero no he pensado
    *en un posible uso
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    */
    
    @FXML
    private VBox VBox_Productos;
    
    @FXML
    private VBox VBox_Analitica;
    
    @FXML
    private VBox VBox_Simulador;
    
    @FXML
    private VBox VBox_Configuracion;
    
    @FXML
    public AnchorPane AnchorPane_main;

    public void initialize() {
        AddFadingAnimation_onHover(VBox_Productos);
        AddFadingAnimation_onHover(VBox_Analitica);
        AddFadingAnimation_onHover(VBox_Simulador);
        AddFadingAnimation_onHover(VBox_Configuracion);
    }
    
    /**
     * cuando se hace click en el VBox de producto se debe cambiar a la
     * pantalla de productos
     * @param event 
     */
    @FXML
    void onClick_VBox_Productos(MouseEvent event) throws IOException {
        Locations.CambiarStage(AnchorPane_main, new productosController(), Locations.productos_fxml);
    }
    
    /**
     * Agrega una transicion de opacidad a un VBox.
     * Meramente visual, no afecta la logica.
     * @param v 
     */
    public void AddFadingAnimation_onHover(VBox v){
        FadeTransition ft = new FadeTransition();
        ft.setNode(v);
        ft.setDuration(new Duration(300));
        ft.setFromValue(1.0);
        ft.setToValue(0.6);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        v.setOnMouseEntered((MouseEvent me) -> ft.play());
    }
}
