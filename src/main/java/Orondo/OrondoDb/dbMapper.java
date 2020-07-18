package Orondo.OrondoDb;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Iterator;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;


/**
 *
 * @author Raul Alzate
 */
public class dbMapper {
    
    private final String dataBaseName = "Retail";
    private final String productosCollection = "productos";
    
    public MongoCollection getProductosCollection(){
        DB db = new MongoClient().getDB(dataBaseName);
        Jongo jongo = new Jongo(db);
        MongoCollection pcoll = jongo.getCollection(productosCollection);
        return pcoll;
    }
    
    public void SaveProduct(Producto p){
        this.getProductosCollection().save(p);
    }
    
    /**
     * trae todos los productos de la base de datos.
     * @return 
     */
    public ArrayList<Producto> GetAllProducts(){
        return getQuery("{}");
    }
    
    
    /**
     * la busqueda por codigo exacto solo puede retornar un producto o ninguno.
     * sin embargo se retorna en un arraylist para usar el size, si es 0
     * es porque el producto no se encontro.
     * https://beginnersbook.com/2017/10/java-string-format-method/
     * https://dzone.com/articles/java-string-format-examples
     * @param _id
     * @return 
     */
    public ArrayList<Producto> GetProductById(String _id){
        
        ArrayList<Producto> r = new ArrayList<>();
        
        String q = "{_id: '%1s'}"; // string formting. ojala en futuras verciones
        q = String.format(q, "x"); // de java agreguen string interpolation de alguna manera como en javascript.
        
        return getQuery(q);
    }
    
    
    /**
     * from https://docs.mongodb.com/manual/reference/operator/query/all/
     * The $all operator selects the documents where the value of a field is an 
     * array that contains all the specified elements.
     * 
     * metodo que devuelve los documentos que su descripcion contienen todas las
     * palabras separadas por whitespace " " en el String descri
     * @param descri
     * @return 
     */
    public ArrayList<Producto> GetByDescrpt(String descri){
        String arg = "";
        String[] kw = descri.split(" ");
        for(String x: kw){
            arg += String.format("{'descripcion':{$regex:'.*%1s.*', $options:'i'}},", x);
        }
        arg = arg.substring(0, arg.length() - 1);// para remover la ultima coma
        
        String q = "{$and:[%1s]}";
        q = String.format(q, arg);
        
        return getQuery(q);
    }
    
    /**
     * encuentra los productos cuyo codigo de barras termina en
     * los digitos especificados por el sting b
     * @return 
     */
    public ArrayList<Producto> getPrdByLastCod(String b){
        String q = "{_id:{$regex:'.*%1s$'}}";
        q = String.format(q, b);
        return getQuery(q);
    }
    
    /**
     * toma un string q y hace el query a la coleccion de productos usando jongo.
     * @param q
     * @return 
     */
    public ArrayList<Producto> getQuery(String q){
        ArrayList<Producto> r = new ArrayList<>();
        MongoCollection pcoll = this.getProductosCollection();
        MongoCursor<Producto> all = pcoll.find(q).as(Producto.class);
        Iterator rite = all.iterator();
        while(rite.hasNext()){
            r.add((Producto) rite.next());
        }
        return r;
    }
    
    
    /**
     * crea un respaldo de la base de datos como archivo .json
     */
    public void BackupJson(String dirfol){
        
        ArrayList<Producto> all = this.GetAllProducts();
    }
    
}
