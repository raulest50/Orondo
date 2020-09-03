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
    private final String producto_id;
    
    private int Cantidad;
    
    private int subTotal;
    
    public boolean fraccionado;
    
    /**
     * precio de venta unitario que puede oscilar entre el valor de costo
     * y el precio de venta al publico, segun como se negocee
     */
    private int UnitPrecio;
    
    
    /**
     * En esta clase si hago uso del esquema tipico java de hacer los 
     * atributos privados y solo accecibles desde los metodos provistos por la 
     * clase, ya que hay una relacion estricta entre cantidad, subtotal y precio
     * de venta. De esta forma dificulto que por error haga cambios en estas
     * variables que generen inconsistencias. si se cambia
     * el precio de venta o la cantidad obligatoriamente se debe
     * poner el valor correspondiente en el subtotal para mantener la consistencia.
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
    
    public void setCantidad(int c){
        this.Cantidad = c;
        RefreshSubTotal();
    }
    
    public void setPrecioVenta(int nuevoPrecio){
        if(nuevoPrecio >= this.p.costo){
            this.UnitPrecio = nuevoPrecio;
        } else{
            this.UnitPrecio = this.p.costo;
        }
        RefreshSubTotal();
    }
    
    /**
     * se debe invocar despues de cambiar la cantidad o el precio unitario,
     * para actualizar el subtotal
     */
    public final void RefreshSubTotal(){
        if(fraccionado){
            this.subTotal = (int) (UnitPrecio*Util.dividir(Cantidad, p.PesoUnitario));
        } else{
            this.subTotal = UnitPrecio*Cantidad;
        }
    }
    
    /**
     * a veces por comodidad tal vez se desee negociar no el precio por unidad
     * de un producto sino sobre el total.Ej si se tienen 20 und de un producto
     * x, y las 20 cuestan 30200. si se desea dejar a 30000 las 20 habria que
     * calcular el valor unitario tal que por 20 de 30000 pero seria engorroso
     * para un usuario. en su lugar se la  posibilidad de poner directamente los
     * 30000 y que el software calcule el correspondiente precio unitario
     * y verifique que no este por debajo del costo. no es una funcion
     * necesaria sino mas por comodidad y agilidad para el usuario.
     * @param sut
     */
    public void setSubTotal(int sut){
        int minsut = p.costo*Cantidad;
        if(sut >= minsut){
            subTotal = sut;
            UnitPrecio = (int) Util.dividir(sut, Cantidad);
        } else{
            subTotal = minsut;
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
