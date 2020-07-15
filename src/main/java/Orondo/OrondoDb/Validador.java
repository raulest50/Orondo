package Orondo.OrondoDb;

/**
 *
 * @author Raul Alzate
 */
public class Validador {
    
    public static boolean ValidarIngresoProducto(Producto p){
        boolean r = true;
        String msg = "";
        if( p._id.isBlank() || p._id.isEmpty()) r=false;
        return r;
    }
    
}
