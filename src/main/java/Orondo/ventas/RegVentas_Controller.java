package Orondo.ventas;

import Orondo.OrondoDb.ItemVenta;
import Orondo.OrondoDb.Venta;
import Orondo.OrondoDb.dbMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<String, Venta> TC_TV_Consecutivo;

    @FXML
    private TableColumn<Integer, Venta> TC_TV_Valor;

    @FXML
    private TableColumn<String, Venta> TC_TV_Fecha;

    @FXML
    private TableColumn<String, Venta> TC_TV_Cliente;

    
    /**
     * Tabla de Items ventas
     */
    @FXML
    private TableView<ItemVenta> TV_ItemVenta;

    @FXML
    private TableColumn<String, ItemVenta> TC_ItemVenta_Descripcion;

    @FXML
    private TableColumn<SimpleIntegerProperty, ItemVenta> TC_ItemVenta_Precio;

    @FXML
    private TableColumn<Integer, ItemVenta> TC_ItemVenta_N;

    @FXML
    private TableColumn<Integer, ItemVenta> TC_ItemVenta_SubT;
    
    
    // date picker lim inferior
    @FXML
    private DatePicker DTP_Desde;

    // date picker lim superior
    @FXML
    private DatePicker DTP_Hasta;
    
    
    public dbMapper dbm = new dbMapper();
    
    
    
    public void initialize(){
        ConfigTables();
        CargarVentasActuales();
    }
    
    public void ConfigTables(){
        TC_TV_Consecutivo.setCellValueFactory(new PropertyValueFactory<>("id"));
        TC_TV_Valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TC_TV_Fecha.setCellValueFactory(new PropertyValueFactory<>("fecha_str"));
        TC_TV_Cliente.setCellValueFactory(new PropertyValueFactory<>("Cliente_id"));
        
        TC_ItemVenta_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TC_ItemVenta_N.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TC_ItemVenta_Precio.setCellValueFactory(new PropertyValueFactory<>("pVenta"));
        TC_ItemVenta_SubT.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        
        
        // se agrega listener al tv de ventas para mostrar la lista de items venta
        // correcpondiente cada que se cambia de factura pos seleccionada.
        TV_Ventas.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                TV_ItemVenta.getItems().setAll(TV_Ventas.getSelectionModel().getSelectedItem().items);
            }
        });
    }
    
    /**
     * Si estan vacios los datepicker entonces pone las ventas del dia actual.
     * Es importante usar LocalDateTime y no Date, la parte de tiempo es vital 
     * en el query del rango en mongo traer las ventas de 1 dia en especifico 
     * se pone en el rango el mismo date y la parte de tiempo se pone el lim
     * inferior 00:00:00 y el limite superior 23:59:59.
     * p
     * @return 
     */
    public LocalDateTime[] getLDateRange(){
        LocalDateTime[] ldtr = new LocalDateTime[2];
        LocalDateTime df;
        LocalDateTime dto;
                
        try{
            df = DTP_Desde.getValue().atStartOfDay();
            dto = DTP_Hasta.getValue().atTime(23, 59, 59);
        } 
        catch(NullPointerException e){
            df = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
            dto = df.plusHours(23).plusMinutes(59).plusSeconds(59);
        }
        
        ldtr[0] = df;
        ldtr[1] = dto;
        return ldtr;
    }
    
    public void CargarVentasActuales(){
        LocalDateTime[] ldtr = getLDateRange();
        ArrayList<Venta> lv = dbm.getVentas(ldtr);
        TV_Ventas.getItems().setAll(lv);
        
    }
    
}
