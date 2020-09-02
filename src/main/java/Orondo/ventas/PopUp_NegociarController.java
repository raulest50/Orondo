/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.ventas;

import Orondo.OrondoDb.ItemVenta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 *
 * @author Raul Alzate
 */
public class PopUp_NegociarController {
    
    @FXML
    Label L_Descripcion;
    
    hacerVentasController hv;
    
    ItemVenta iv;
    
    void Initialize(){
        
    }

    void setItemVenta(ItemVenta iv, hacerVentasController hv){
        this.iv = iv;
        this.hv = hv;
        
        this.L_Descripcion.setText(this.iv.p.descripcion);
    }
    
}
