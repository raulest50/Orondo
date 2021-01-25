/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.OrondoDb;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Raul Alzate
 */
public class Venta {
    
    public String _id; // es el concecutivo del documento de venta
    
    public ArrayList<ItemVenta> items;
    
    public String fecha;
    
    public String tipo_id_Cliente;
    
    public String Cliente_id;
    
    public String Vendedor_id; // identificacion de quien registro la venta
    
    /**
     * constructor que se usa cuando se desea insertar una venta en la bd
     * @param items
     * @param fecha 
     * @param Vendedor_id 
     */
    public Venta(ArrayList<ItemVenta> items, String fecha, String Vendedor_id){
        this.items = items;
        this.fecha = fecha;
        this.Vendedor_id = Vendedor_id;
        // 1 corresponde a la identificacion de cliente generico para ventas mostrador
        this.Cliente_id = "1"; 
        // _id se genera al momento de la insercion
    }
    
    /**
     * constructor que se usa cuando se desea leer una venta de la base de datos
     */
    public Venta(){
        
    }
    
    /**
     * Get total 
     * @param items
     * @return 
     */
    public int getTotalVenta(ArrayList<ItemVenta> items){
        int s = 0;
        for (ItemVenta x : items){
            s += x.getSubTotal();
        }
        return s;
    }
    
    
    /**
     * Retorna un mapa con los valores correspondiete a cada porcentaje de
     * iva. los de 0 corresponderian a los excentos.
     * @return 
     */
    public HashMap calcularIvas(){
        HashMap<String, Integer> ivas = new HashMap<>();
        
        return ivas;
    }
}
