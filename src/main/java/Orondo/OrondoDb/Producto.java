package Orondo.OrondoDb;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;


/**
 *
 * @author Raul Alzate
 *
 * Representacion de un producto
 */
@Entity("productos")
public class Producto {
    
    @Id
    public String codigo;
    
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
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.pv_mayor = pv_mayor;
        this.pv_publico = pv_publico;
        this.iva = iva;
        this.last_updt = last_updt;
        this.keywords = keywords;
    }
    
}
