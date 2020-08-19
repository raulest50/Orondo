/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.productos;

import Orondo.OrondoDb.Producto;
import Orondo.OrondoDb.Validador;
import Orondo.OrondoDb.dbMapper;
import Orondo.inicio.GenericDialogs;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Raul Alzate
 */
public class modificarDialogController {
    
    Producto p;
    
    modificarController mc;
    
    public void initialize() {
        
    }
    
    @FXML
    private TextField TF_Codigo;

    @FXML
    private Label L_Codigo;

    @FXML
    private TextField TF_Descripcion;

    @FXML
    private Label L_Descripcion;

    @FXML
    private TextField TF_Costo;

    @FXML
    private Label L_Costo;

    @FXML
    private TextField TF_PVMayor;

    @FXML
    private Label L_PVMayor;

    @FXML
    private TextField TF_PVPublico;

    @FXML
    private Label L_PVPublico;

    @FXML
    private TextField TF_IVA;

    @FXML
    private Label L_IVA;

    @FXML
    private TextArea TA_KeyWords;

    @FXML
    private TextArea CP_KeyWords;

    @FXML
    void onAction_B_Cancelar(ActionEvent event) {
        cerrar();
    }

    @FXML
    void onAction_B_Modificar(ActionEvent event) {
        String codigo = TF_Codigo.getText();
        String descripcion = TF_Descripcion.getText();
        int costo = Integer.parseInt(TF_Costo.getText());
        int pvmayor = Integer.parseInt(TF_PVMayor.getText());
        int pvpublico = Integer.parseInt(TF_PVPublico.getText());
        double iva = Double.parseDouble(TF_IVA.getText());
        String keywords = TA_KeyWords.getText();
        
        // si los cambios son validos se procede a actualizar el producto en mongo.
        dbMapper dbm = new dbMapper();
        Producto modp = new Producto(codigo, descripcion, costo, pvmayor, pvpublico, iva, dbm.now(), keywords);
        
        ArrayList relval;
        
        // el _id se puede alterar en el textfield pero no en el label y por eso
        // se usa este ultimo para saber si el codigo se modifico
        if(modp._id.equals(L_Codigo.getText())) relval = Validador.ModificacionValida(modp);
        else relval = Validador.CodificacionValida(modp);
        
        if((boolean) relval.get(0)){
            dbm.UpdateProducto(modp, L_Codigo.getText());
            GenericDialogs.Info("Modificacion", "Operacion Exitosa :)",
                    "La modificacion se realizo correctamente.");
            mc.BuscarProducto();
            cerrar();
        } else{
            GenericDialogs.Info("Modificacion", "Hay que corregir los datos."
                    + " \n a continuacion se muestra que se debe corregir:",
                    (String) relval.get(1));
        }
        
    }

    @FXML
    void onAction_B_Reset(ActionEvent event) {
        ResetTextFields();
    }
    
    @FXML
    void onAction_B_Eliminar(ActionEvent event){
        // dialogo de confirmacion para la eliminacion del producto.
        boolean yn = GenericDialogs.Confirmation("Eliminacion Producto",
                "ADVERTENCIA lea con cuidado", "presione OK si desea eliminar este producto."
                        + "\n recuerde que esta operacion no se puede deshacer.");
        if(yn){
            (new dbMapper()).EliminarProducto(p._id);
            GenericDialogs.Info("Eliminacion", "Operacion Exitosa :)", "El producto ha sido eliminado exitosamente");
            mc.BuscarProducto();
            cerrar();
        }
        
    }

    
    /**
     * Lo usa el controlador de modificar para pasar al digolo de modificacion
     * el producto seleccionado en la tabla y tambien el controlador de modificar
     * para actualizar la tabla una vez modificado el producto.
     * @param p
     * @param mc
     */
    public void setProducto(Producto p, modificarController mc){
        this.p = p;
        this.mc = mc;
        
        L_Codigo.setText(p._id);
        L_Descripcion.setText(p.descripcion);
        L_Costo.setText(Integer.toString(p.costo));
        L_PVMayor.setText(Integer.toString(p.pv_mayor));
        L_PVPublico.setText(Integer.toString(p.pv_publico));
        L_IVA.setText(Double.toString(p.iva));
        CP_KeyWords.setText(p.keywords);
        
        ResetTextFields();
    }
    
    public void ResetTextFields(){
        TF_Codigo.setText(p._id);
        TF_Descripcion.setText(p.descripcion);
        TF_Costo.setText(Integer.toString(p.costo));
        TF_PVMayor.setText(Integer.toString(p.pv_mayor));
        TF_PVPublico.setText(Integer.toString(p.pv_publico));
        TF_IVA.setText(Double.toString(p.iva));
        TA_KeyWords.setText(p.keywords);
    }
    
    public void cerrar(){ // metodo para cerrar la ventana
        // se obtiene un handle de la ventana actual mediante un boton de la  misma ventana
        //en este caso el mismo asociado a la operacion de cancelar
        Stage stage = (Stage) TF_Codigo.getScene().getWindow();
        // se cierra la ventana de modificacion
        stage.close();
        mc.BuscarProducto();
    }
}
