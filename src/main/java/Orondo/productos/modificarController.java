package Orondo.productos;

import Orondo.OrondoDb.Producto;
import Orondo.OrondoDb.dbMapper;
import Orondo.inicio.InfoDialogs;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    ComboBox CB_OpcionesB;
    
    
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
    
    ObservableList<String> itemsComboB = FXCollections.observableArrayList();
    
    //para busqueda por codigo exacto
    public final String B_CODIGO_EXACT = "Codigo Exacto";
    
    // busqueda por coincidencias parciaes en la descripcion
    public final String B_DESCRI = "Descripcion";
    
    //
    public final String B_LAST_CODE = "Ultimos del Codigo";
    
    public void initialize() {
        
        TC_Codigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        TC_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TC_Costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        TC_pvPublico.setCellValueFactory(new PropertyValueFactory<>("pv_publico"));
        TC_xMayor.setCellValueFactory(new PropertyValueFactory<>("pv_mayor"));
        TC_LastUp.setCellValueFactory(new PropertyValueFactory<>("last_updt"));
        TC_Iva.setCellValueFactory(new PropertyValueFactory<>("iva"));
        
        
        itemsComboB.addAll(this.B_DESCRI, this.B_CODIGO_EXACT, this.B_LAST_CODE);
        CB_OpcionesB.setItems(itemsComboB);
        CB_OpcionesB.getSelectionModel().selectFirst();
        
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
        ArrayList<Producto> lista = new ArrayList<>();
        
        String selected = (String) CB_OpcionesB.getSelectionModel().getSelectedItem();
        String b = TF_Buscar.getText();
        
        switch(selected){
            case B_CODIGO_EXACT:
                lista = dbm.GetProductById(b);
                break;
            case B_DESCRI:
                lista = dbm.GetByDescrpt(b);
                break;
            case B_LAST_CODE:
                lista = dbm.getPrdByLastCod(b);
                break;
        }
        
        TV_Productos.getItems().setAll(lista);
    }
    
}
