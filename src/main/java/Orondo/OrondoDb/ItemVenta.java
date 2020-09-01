/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.OrondoDb;

import Orondo.utils.Util;
import javafx.beans.property.SimpleIntegerProperty;

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
    private final String producto_id;
    
    private int Cantidad;
    
    private int subTotal;
    
    public boolean fraccionado;
    
    /**
     * precio de venta unitario que puede oscilar entre el valor de costo
     * y el precio de venta al publico, segun como se negocee
     */
    public int UnitPrecio;
    
    
    /**
     * En esta clase si hago uso del esquema tipico java de hacer los 
     * atributos privados y solo accecibles desde los metodos provistos por la 
     * clase, ya que hay unaa relacion estricta entre cantidad, subtotal y precio
     * de venta. De esta forma dificulto que por error haga cambios en estas
     * variables, cambios tales que generen inconsistencias. si se cambia
     * el precio de venta o la cantidad eso se refleja en el subtotal.
     * @param p
     * @param Cantidad
     * @param UnitPrecio
     * @param fraccionado 
     */
    public ItemVenta(Producto p, int Cantidad, int UnitPrecio, boolean fraccionado){
        this.p = p;
        this.producto_id = p._id;
        this.Cantidad = Cantidad;
        this.UnitPrecio = UnitPrecio;
        this.fraccionado = fraccionado;
        RefreshSubTotal();
    }
    
    
    public void Add2Cantidad(int add){
        this.Cantidad += add;
        RefreshSubTotal();
    }
    
    public void setPrecioVenta(int nuevoPrecio){
        if(nuevoPrecio >= this.p.costo){
            this.UnitPrecio = nuevoPrecio;
        } else{
            this.UnitPrecio = this.p.costo;
        }
    }
    
    /**
     * 
     */
    public final void RefreshSubTotal(){
        if(fraccionado){
            this.subTotal = (int) (UnitPrecio*Util.dividir(Cantidad, p.PesoUnitario));
        } else{
            this.subTotal = UnitPrecio*Cantidad;
        }
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
        return subTotal;
    }
}
