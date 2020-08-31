package Orondo.ventas;

import Orondo.OrondoDb.ItemVenta;
import Orondo.OrondoDb.Producto;
import Orondo.OrondoDb.dbMapper;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Raul Alzate
 */
public class hacerVentasController {
    
    
    @FXML
    TextField TF_BuscarProducto;
    
    
    @FXML
    TableView TV_Busqueda;
    
    @FXML
    TableColumn TCBuscar_Codigo;
    
    @FXML
    TableColumn TCBuscar_Descripcion;
    
    @FXML
    TableColumn TCBuscar_Costo;
    
    @FXML
    TableColumn TCBuscar_PVmayor;
    
    @FXML
    TableColumn TCBuscar_PVPublico;
    
    
    
    @FXML
    TableView TV_Ventas;
    
    @FXML
    TableColumn TCVentas_Descripcion;
    
    @FXML
    TableColumn TCVentas_Pventa;
    
    @FXML
    TableColumn TCVentas_Cantidad;
    
    @FXML
    TableColumn TCVentas_SubTotal;
    
    
    @FXML
    ComboBox CB_OpcionesB;
    
    
    @FXML
    ToggleButton ToggButton_TipoVenta;
    
    
    ObservableList<String> itemsComboB = FXCollections.observableArrayList();
    
    ArrayList<ItemVenta> ListaVenta;
    
    //para busqueda por codigo exacto
    public final String B_CODIGO_EXACT = "Codigo Exacto";
    
    // busqueda por coincidencias parciaes en la descripcion
    public final String B_DESCRI = "Descripcion";
    
    //
    public final String B_LAST_CODE = "Ultimos del Codigo";
    
    
    public final String STR_VENTA_MAYOR="MAYORISTA";
    public final String STR_VENTA_PUBLICO="DETAL";
    
    public void initialize(){
        
        // Table view busqeuda productos para luego adicionar a table view ventas
        TCBuscar_Codigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        TCBuscar_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TCBuscar_Costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        TCBuscar_PVmayor.setCellValueFactory(new PropertyValueFactory<>("pv_publico"));
        TCBuscar_PVPublico.setCellValueFactory(new PropertyValueFactory<>("pv_mayor"));
        
        //Table view ventas
        TCVentas_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TCVentas_Cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TCVentas_Pventa.setCellValueFactory(new PropertyValueFactory<>("pVenta"));
        TCVentas_SubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        
        // se inicializa las opciones del ComboBox
        itemsComboB.addAll(this.B_DESCRI, this.B_CODIGO_EXACT, this.B_LAST_CODE);
        CB_OpcionesB.setItems(itemsComboB);
        CB_OpcionesB.getSelectionModel().selectFirst();
        
        ToggButton_TipoVenta.setText(this.STR_VENTA_PUBLICO);
        ToggButton_TipoVenta.selectedProperty().addListener((Obs, oldv, newv)->{
            if(newv){
                ToggButton_TipoVenta.setText(this.STR_VENTA_MAYOR);
            } else{
                ToggButton_TipoVenta.setText(this.STR_VENTA_PUBLICO);
            }
        });
        
    }
    
    
    @FXML
    public void onAction_BuscarProducto(ActionEvent event){
        BuscarProducto();
    }
    
    @FXML
    public void onAction_ToggButton(ActionEvent event){
        this.TF_BuscarProducto.requestFocus();
    }
    
    public void BuscarProducto(){
        dbMapper dbm = new dbMapper();
        ArrayList<Producto> lista = new ArrayList<>();
        
        String selected = (String) CB_OpcionesB.getSelectionModel().getSelectedItem();
        String b = TF_BuscarProducto.getText();
        
        switch(selected){
            case B_CODIGO_EXACT:
                lista = dbm.GetProductById(b);
                if(!lista.isEmpty()) AddItemVenta(lista.get(0));
                break;
            case B_DESCRI:
                lista = dbm.GetByDescripcion(b);
                break;
            case B_LAST_CODE:
                lista = dbm.getPrdByLastCod(b);
                break;
        }
        
        TV_Busqueda.getItems().setAll(lista);
    }
    
    /**
     * coloca el precio del producto en la tabla de ventas
     * segun el estado del toggleButton.
     * @param p 
     */
    public void AddItemVenta(Producto p){
        if(ToggButton_TipoVenta.getText().equals(this.STR_VENTA_MAYOR))
            TV_Ventas.getItems().add(new ItemVenta(p, 1, p.pv_mayor, false));
        else TV_Ventas.getItems().add(new ItemVenta(p, 1, p.pv_publico, false));
    }
    
    
    
}
