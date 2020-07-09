package Orondo.OrondoDb;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;

/**
 *
 * @author Raul Alzate
 */
public class dbMorf {
    
    
    private final String dataBaseName = "Retail";
    
    public Datastore getDataStore(String dataBaseName){
        Morphia morphia = new Morphia();
        //morphia.mapPackage("com.baeldung.morphia");
        //morphia.mapPackage("Orondo.OrondoDb");
        Datastore datastore = morphia.createDatastore(new MongoClient(), dataBaseName);
        datastore.ensureIndexes();
        return datastore;
    }
    
    public void SaveProduct(Producto p){
        this.getDataStore(dataBaseName).save(p);
    }
    
    public void GetAllProducts(){
        Query<Producto> query = getDataStore(dataBaseName).find(Producto.class);
        
    }
    
    /**
     * crea un respaldo de la base de datos como archivo .json
     */
    public void BackupJson(){
        
    }
    
}
