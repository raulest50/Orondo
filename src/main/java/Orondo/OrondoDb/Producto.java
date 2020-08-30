package Orondo.OrondoDb;

import org.jongo.marshall.jackson.oid.MongoId;



/**
 *
 * @author Raul Alzate
 *
 * Representacion de un producto
 */

public class Producto {
    
    /**
     * equivalente al codigo de barras del producto o solo codigo.
     * pero para mejor claridad se dejo _id que es el nombre por defecto que
     * usa mongo y jongo para la llave primaria. para cambiar el nombre por
     * defecto a uno personalizado toca agregar otra anotacion, entonces preferi
     * dejarlo asi para mas simplisidad.
     */
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
    
    // ultima frecha de actualizacion del producto
    public String last_updt;
    
    /** palabras clave que se deseen asignar al producto. se pueden usar para
     * efectos de busqueda o tambien de analisis de productos pero aun no lo he
     * definido, en todo caso deje este atributo para dejar abierta la posibilidad.
     */ 
     public String keywords;
     
    
    /**
     * indica si el producto se puede dividir para ser vendido. el caso mas
     * comun es el queso cuajada granos en paquetes de libra salchichones etc.
     */
    public boolean fraccionable;
    
    /**
     * solo tiene sentido si fraccionable=true. es el peso en gramos del producto
     * el cual se usa en regla de 3 con el precio de venta para calcular
     * el valor correspondiente a la fraccion. Ej. frijol por libra 450g
     * a 4000. si este producto se establece como fraccionable entonces
     * el software permitira que se registren 225g de frijol en una venta.
     * con el peso unitario se calcularia el valor correspondiente a cobrar
     * y descontaria 0.5 en stock.
     */
    public int PesoUnitario;

    public Producto(String codigo, String descripcion, int costo,
            int pv_mayor, int pv_publico, Double iva, String last_updt, String keywords, 
            boolean fraccionable, int PesoUnitario) {
        this._id = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.pv_mayor = pv_mayor;
        this.pv_publico = pv_publico;
        this.iva = iva;
        this.last_updt = last_updt;
        this.keywords = keywords;
        this.fraccionable = fraccionable;
        this.PesoUnitario = PesoUnitario;
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
