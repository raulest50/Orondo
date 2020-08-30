/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.OrondoDb;

/**
 *
 * @author Raul Alzate
 */
public class ItemVenta {
    
    /**
     * el producto correspondiente a este item de venta.
     * de aqui se toman los datos como codigo nombre costo etc
     */
    public Producto p;
    public int Cantidad;
    
    /**
     * precio de venta unitario que puede oscilar entre el valor de costo
     * y el precio de venta al publico, segun como se negocee
     */
    public int UnitPrecio;
    
    
    
}
