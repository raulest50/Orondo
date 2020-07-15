package Orondo.productos;

import Orondo.OrondoDb.Producto;
import Orondo.OrondoDb.dbMapper;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Raul Alzate
 */
public class modificarController {
    
    
    @FXML
    Button Button_Buscar;
    
    @FXML
    TextField TF_Buscar;
    
    
    @FXML
    TableView<Producto> TV_Productos;
    
    @FXML
    TableColumn<String, Producto> TC_Codigo;
    
    @FXML
    TableColumn<String, Producto> TC_Descripcion;
    
    @FXML
    TableColumn<Integer, Producto> TC_Costo;
    
    @FXML
    TableColumn<Integer, Producto> TC_xMayor;
    
    @FXML
    TableColumn<Integer, Producto> TC_pvPublico;
    
    @FXML
    TableColumn<String, Producto> TC_LastUp;
    
    @FXML
    TableColumn<Double, Producto> TC_Iva;
    
    
    public void initialize() {
        
        TC_Codigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        TC_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TC_Costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        TC_pvPublico.setCellValueFactory(new PropertyValueFactory<>("pv_publico"));
        TC_xMayor.setCellValueFactory(new PropertyValueFactory<>("pv_mayor"));
        TC_LastUp.setCellValueFactory(new PropertyValueFactory<>("last_updt"));
        TC_Iva.setCellValueFactory(new PropertyValueFactory<>("iva"));
        
    }
    
    
    @FXML
    public void onAction_Button_Buscar(ActionEvent event){
        this.BuscarProducto();
    }
    
    @FXML
    public void onAction_TF_Buscar(ActionEvent event){
        this.BuscarProducto();
    }
    
    public void BuscarProducto(){
        dbMapper dbm = new dbMapper();
        ArrayList<Producto> lista = dbm.GetAllProducts();
        TV_Productos.getItems().addAll(lista);
    }
    
}
