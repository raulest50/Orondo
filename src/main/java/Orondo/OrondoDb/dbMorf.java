package Orondo.OrondoDb;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;


/**
 *
 * @author Raul Alzate
 */
public class dbMorf {
    
    
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
    
    public void GetAllProducts(){
        MongoCollection pcoll = this.getProductosCollection();
        MongoCursor<Producto> all = pcoll.find("{}").as(Producto.class);
    }
    
    /**
     * crea un respaldo de la base de datos como archivo .json
     */
    public void BackupJson(){
        
    }
    
}
