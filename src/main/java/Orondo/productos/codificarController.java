package Orondo.productos;

import Orondo.OrondoDb.Producto;
import Orondo.OrondoDb.dbMorf;
import Orondo.Styling.Styler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Raul Alzate
 */
public class codificarController {
    
    @FXML
    public TextField TextField_Codigo;
    
    @FXML
    public TextField TextField_Iva;
    
    @FXML
    public TextField TextField_Nombre;

    @FXML
    public TextField TextField_StockInit;

    @FXML
    public TextField TextField_Costo;

    @FXML
    public TextField TextField_PvMayor;

    @FXML
    public TextField TextField_PvPublico;

    @FXML
    public Button Button_Codificar;

    @FXML
    public Button Button_Borrar;
    
    public void initialize() {
        // se configuran estos textFields para que solo reciban numeros
        Styler.SetTextFieldAsNumeric(TextField_Costo);
        Styler.SetTextFieldAsNumeric(TextField_PvMayor);
        Styler.SetTextFieldAsNumeric(TextField_PvPublico);
        Styler.SetTextFieldAsNumeric(TextField_StockInit);
        Styler.SetTextFieldAsNumeric(TextField_Iva);
    }
    
    @FXML
    void onClickButtonCodificar(MouseEvent event) {
        String codigo = TextField_Codigo.getText();
        String nombre = TextField_Nombre.getText();
        Double costo = Double.parseDouble(TextField_Costo.getText());
        Double pvmayor = Double.parseDouble(TextField_PvMayor.getText());
        Double pvpublico = Double.parseDouble(TextField_PvPublico.getText());
        Double stock = Double.parseDouble(TextField_StockInit.getText());
        Double iva = Double.parseDouble(TextField_Iva.getText());
        
        Producto p = new Producto(codigo, nombre, "", costo, pvmayor, pvpublico,iva);
        dbMorf db = new dbMorf();
        db.SaveProduct(p);
    }
}
