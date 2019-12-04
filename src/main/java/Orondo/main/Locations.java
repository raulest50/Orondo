package Orondo.main;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Para guardar de forma estatica los nombres de los resources y otros archivos
 * y asi poder cambiarlos mas facilmente en el codigo en caso de ser necesario.
 * importantes.
 * 
 * se debe recordar que el metodo que se esta usando para cargar los resources
 * es mediante el uso de los metodos getClass.getResource los cuales permiten
 * acceder directamente al respectivo folder de un .classes dentro de la carpeta
 * build. Se accede al folder en una linea, sin usar rutas absolutas.
 * para que funcione es importante que se copien los resources junto con los
 * respectivos .classes en cada una de las carpetas. en esta caso se usa gradle 
 * el cual se le adiciono una directiva para asegurar que sean copiados.
 * @author Raul Alzate
 */
public class Locations {
    
    /**
     * FXML view de inicio de la aplicacion. Tiene botones con los diferentes 
     * modulos del programa
     */
    public static String main_fxml = "main.fxml";
    
    /**
     * archivo de estilizado del view principal
     */
    public static String main_css = "main.css";
    
    /**
     * view de productos
     */
    public static String productos_fxml = "productos.fxml";
    
    
    /**
     * metodo para cambiar de pantalla.Con el Doc controller se accede al 
     * respectivo folder de la carpeta de build por medio de getClass.getResource. 
     * destino es el nombre del resource, el cual esta guardado en esta clase
     * como static String.
     * @param p : AnchorPane del actual FXLM o FXML de origen
     * @param cont : controlador del FXML al que se desea cambiar o destino
     * @param destino : nombre del FXML al que se desea cambiar o destino
     * @throws java.io.IOException
     */
    public static void CambiarStage(AnchorPane p, Object cont, String destino) 
            throws IOException{
        Scene scene = new Scene(FXMLLoader.load(cont.getClass().getResource(destino)));
        Stage stage = (Stage) p.getScene().getWindow();
        stage.setScene(scene);
    }
    
}
