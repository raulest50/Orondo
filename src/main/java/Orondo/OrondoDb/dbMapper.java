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
        
        ArrayList<Producto> r = new ArrayList<>();
        
        MongoCollection pcoll = this.getProductosCollection();
        MongoCursor<Producto> all = pcoll.find("{}").as(Producto.class);
        Iterator rite = all.iterator();
        while(rite.hasNext()){
            r.add((Producto) rite.next());
        }
        return r;
    }
    
    /**
     * crea un respaldo de la base de datos como archivo .json
     */
    public void BackupJson(){
        
    }
    
}
