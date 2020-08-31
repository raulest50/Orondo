/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.OrondoDb;

import Orondo.utils.Util;

/**
 *
 * @author Raul Alzate
 */
public class ItemVenta {
    
    /**
     * el producto correspondiente a este item de venta.
     * de aqui se toman los datos como codigo nombre costo etc
     */
    public transient Producto p;
    
    /**
     * codigo del producto
     */
    public String producto_id;
    
    public int Cantidad;
    
    public int subTotal;
    
    public boolean fraccionado;
    
    /**
     * precio de venta unitario que puede oscilar entre el valor de costo
     * y el precio de venta al publico, segun como se negocee
     */
    public int UnitPrecio;
    
    public ItemVenta(Producto p, int Cantidad, int UnitPrecio, boolean fraccionado){
        this.p = p;
        this.producto_id = p._id;
        this.Cantidad = Cantidad;
        this.UnitPrecio = UnitPrecio;
        this.fraccionado = fraccionado;
    }
    
    
    public String getDescripcion(){
        return p.descripcion;
    }
    
    public int getPVenta(){
        return UnitPrecio;
    }
    
    public int getCantidad(){
        return Cantidad;
    }
    
    public int getSubtotal(){
        int r;
        if(fraccionado){
            r = (int) (UnitPrecio*Util.dividir(Cantidad, p.PesoUnitario));
        } else{
            r = UnitPrecio*Cantidad;
        }
        return r;
    }
}
