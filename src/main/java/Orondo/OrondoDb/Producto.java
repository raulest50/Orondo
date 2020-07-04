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
    public String nombre;
    public String descripcion;
    public Double costo;
    
    /**
     * precio de venta para tienda o pormmayor
     */
    public Double pv_mayor;
    
    /**
     * precio de venta al publico
     */
    public Double pv_publico;
    
    /**
     * si no tiene iva se pone 0
     */
    public Double iva;

    public Producto(String codigo, String nombre, String descripcion, Double costo, Double pv_mayor, Double pv_publico, Double iva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.pv_mayor = pv_mayor;
        this.pv_publico = pv_publico;
        this.iva = iva;
    }
    
}
