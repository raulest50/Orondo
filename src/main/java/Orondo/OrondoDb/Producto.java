package Orondo.OrondoDb;

import org.jongo.marshall.jackson.oid.MongoId;



/**
 *
 * @author Raul Alzate
 *
 * Representacion de un producto
 */

public class Producto {
    
    @MongoId
    public String _id;
    
    public String descripcion;
    
    public int costo;
    
    /**
     * precio de venta para tienda o pormmayor
     */
    public int pv_mayor;
    
    /**
     * precio de venta al publico
     */
    public int pv_publico;
    
    /**
     * si no tiene iva se pone 0
     */
    public Double iva;
    
    public String last_updt;
    
    public String keywords;

    public Producto(String codigo, String descripcion, int costo,
            int pv_mayor, int pv_publico, Double iva, String last_updt, String keywords) {
        this._id = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.pv_mayor = pv_mayor;
        this.pv_publico = pv_publico;
        this.iva = iva;
        this.last_updt = last_updt;
        this.keywords = keywords;
    }
    
    public Producto(){
        
    }

    public String getId() {
        return _id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public int getPv_mayor() {
        return pv_mayor;
    }

    public int getPv_publico() {
        return pv_publico;
    }

    public Double getIva() {
        return iva;
    }

    public String getLast_updt() {
        return last_updt;
    }

    public String getKeywords() {
        return keywords;
    }
    
    
    
}
