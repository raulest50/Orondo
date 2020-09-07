/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.OrondoDb;

import java.util.ArrayList;

/**
 *
 * @author Raul Alzate
 */
public class Venta {
    
    public String _id; // es el concecutivo de la documento de venta
    
    public ArrayList<ItemVenta> items;
    
    public String fecha;
    
    public String Cliente_id;
    
    public int total;
    
    /**
     * constructor que se usa cuando se desea insertar una venta en la bd
     * @param items
     * @param fecha
     * @param Cliente_id 
     */
    public Venta(ArrayList<ItemVenta> items, String fecha, String Cliente_id){
        this.items = items;
        this.fecha = fecha;
        this.Cliente_id = Cliente_id;
        this.total = SumPVs(items);
        // _id se genera al momento de la insercion
    }
    
    /**
     * constructor que se usa cuando se desea leer una venta de la base de datos
     */
    public Venta(){
        
    }
    
    public int SumPVs(ArrayList<ItemVenta> items){
        int s = 0;
        for (ItemVenta x : items){
            s += x.getSubTotal();
        }
        return s;
    }
    
    
}
