package Orondo.ventas;

import Orondo.OrondoDb.ItemVenta;
import Orondo.OrondoDb.Venta;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Esteban
 * 
 * Controlador de la seccion de registro de ventas.
 * permite visualizar las ventas entre fechas especificadas.
 * 
 * por defecto muestra las ventas del dia actual.
 */
public class RegVentas_Controller {
    
    
    /**
     * tabla de ventas o facturas
     */
    @FXML
    private TableView<Venta> TV_Ventas;

    @FXML
    private TableColumn<String, Venta> TC_vent_consecutivo;

    @FXML
    private TableColumn<Integer, Venta> TC_Valor;

    @FXML
    private TableColumn<String, Venta> TC_Fecha;

    @FXML
    private TableColumn<String, Venta> TC_Cliente;

    
    /**
     * Tabla de Items ventas
     */
    @FXML
    private TableView<ItemVenta> TV_ItemVenta;

    @FXML
    private TableColumn<?, ItemVenta> TC_ItemVenta_Descripcion;

    @FXML
    private TableColumn<?, ItemVenta> TC_ItemVenta_Precio;

    @FXML
    private TableColumn<?, ItemVenta> TC_ItemVenta_N;

    @FXML
    private TableColumn<?, ItemVenta> TC_ItemVenta_SubT;
    
    // date picker lim inferior
    @FXML
    private DatePicker DTP_Desde;

    // date picker lim superior
    @FXML
    private DatePicker DTP_Hasta;
    
    
    public LinkedList<Venta> GetVentas(String date_inf, String date_sup){
        
        LinkedList<Venta> lv = new LinkedList<>();
        return lv;
        
    }
    
    
    public void initialize(){
        // cargar ventas del dia actual
    }
    
    
}
