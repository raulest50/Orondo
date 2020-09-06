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
    
    String _id; // es el concecutivo de la documento de venta
    
    public ArrayList<ItemVenta> items;
    
    public String fecha;
    
    public String Cliente_id;
    
    public int total;
    
    public void Venta(ArrayList<ItemVenta> items, String fecha, String Cliente_id){
        this.items = items;
        this.fecha = fecha;
        this.Cliente_id = Cliente_id;
        this.total = SumPVs(items);
        this._id = generateID();
    }
    
    public int SumPVs(ArrayList<ItemVenta> items){
        int s = 0;
        for (ItemVenta x : items){
            s += x.getSubtotal();
        }
        return s;
    }
    
    public String generateID(){
        String r = "";
        
        dbMapper dbm = new dbMapper();
        
        
        return r;
    }
    
}
