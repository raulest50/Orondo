package Orondo.OrondoDb;



import com.mongodb.MongoClientSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;



/**
 *
 * @author Raul Alzate
 * 
 * Inicialmente se implemento con morphia pero no dio buena impresion ya que se
 * seguian los pasos documentacion para la ultima vercion y mostraba el uso de
 * funciones ya deprecated y para acabar de completar no funcionaban tal cual.
 * la incongruencia sumado a lo complejo de morphia llevo al uso de jongo que
 * es notablemente mas simple. jongo parecia ser perfecto ya que las operaciones
 * se parecen mucho a como se hacen en la shell, incluso jongo para mi merece 5
 * estrellas. sin embargo sebe usarse el conector de mongo para java version 3
 * y por tanto no se puede hacer uso de transacciones. este pequeño detalle
 * lo arruino todo. las transacciones en resumen es cuando se modifican multiples
 * documentos se requiere que se modifiquen todos o no se modifique ninguno para
 * garantizar la consistencia de los datos. Ademas despues de la version 4 el
 * conector de mongo para java ya soporta el uso directo de pojos. aunque seria
 * un condigo mucho mas hermoso con jongo, por la imperiosa necesidad se debe 
 * usar el conector directamente por las multitransactions. Esta caracteristica
 * es critica en la generacion de la facturacion para garantizar la altisima
 * robustez del programa.
 */
public class dbMapper {
    
    // CONSTANTES
    private final String DBNAME = "Retail";
    
    private final String CLN_PRODUCTOS = "productos";
    
    private final String CLN_VENTAS = "ventas";
    
    private final String CLN_CLIENTES = "clientes";
    
    private final String CLN_RANGOS_FAC = "rangofacturacion";
    
    
    // MOGODB CONECTOR OBJECTS
    private MongoDatabase db;
    
    private MongoClient mongo_client;
    
    private MongoClientSettings settings;
    
    CodecRegistry pojoCodecRegistry;
    
    /**
     * Hay 2 caracteristicas muy importantes como se explica arriba.
     * 1. transactions
     * 2. el uso de pojos
     * para 2. se requiere pojoCodecRegistry y settings, entonces hay que
     * garantizar la inicializacion de las variables para que se puedan usar 
     * pojos.
     */
    public dbMapper(){
        this.pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
        this.settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        
        this.mongo_client = MongoClients.create(settings);
        
        this.db = this.mongo_client.getDatabase(this.DBNAME);
    }
    
    
    private MongoCollection<Producto> getProductosCollection(){
        MongoCollection<Producto> cln = db.getCollection(this.CLN_PRODUCTOS, Producto.class);
        return cln;
    }
    
    private MongoCollection<Venta> getVentaCollection(){
        MongoCollection<Venta> cln = db.getCollection(this.CLN_VENTAS, Venta.class);
        return cln;
    }
    
    private MongoCollection getRangoFCollection(){
        return db.getCollection(this.CLN_RANGOS_FAC);
        
    }
    
    
    // ################ Productos  ################
    
    public void SaveProduct(Producto p){
        getProductosCollection().insertOne(p);
    }
    
    /**
     * si al producto no se modifico el atributo _id o tambien llamado codigo,
     * entonces el codigo anterior y actual es el mismo, es decir, que en
     * el argumento de esta funcion se podria colocar (Producto p, String p._id).
     * En caso contrario el metodo eliminara el record con el codigo anterior
     * y hara un save del producto (que tiene el codigo nuevo encapsulado).
     * @param p producto que se desea actualizar
     * @param _id codigo anterior
     */
    public void UpdateProducto(Producto p, String _id){
        
        MongoCollection<Producto> cln = getProductosCollection();
        // _id anterior y nuevo iguales
        if(_id.equals(p._id)) cln.replaceOne(eq("_id", _id), p);
        else{ // _id anterior y nuevo diferentes
            cln.deleteOne(eq("_id", _id)); // se borra el anterior
            
        }
    }
    
    public void EliminarProducto(String _id){
        MongoCollection cln = getProductosCollection();
        cln.deleteOne(eq("_id", _id));
    }
    
    /**
     * trae todos los productos de la base de datos.
     * @return 
     */
    public ArrayList<Producto> GetAllProducts(){
        Iterator<Producto> it = getProductosCollection().find().iterator();
        ArrayList<Producto> li = new ArrayList();
        it.forEachRemaining( (x)-> { li.add(x); });
        return li;
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
        Iterator<Producto> it = getProductosCollection().find(eq("_id", _id)).iterator();
        ArrayList<Producto> li = new ArrayList();
        it.forEachRemaining((x)->{ li.add(x); });
        return li;
    }
    
    
    /**
     * from https://docs.mongodb.com/manual/reference/operator/query/all/
     * The $all operator selects the documents where the value of a field is an 
     * array that contains all the specified elements.
     * 
     * metodo que devuelve los documentos que su descripcion contienen todas las
     * palabras separadas por whitespace " " en el String descri
     * 
     * mejoro mucho el codigo al usar directamente el driver en lugar de jongo
     * 
     * @param descri
     * @return 
     */
    public ArrayList<Producto> GetByDescripcion(String descri){
        ArrayList<Bson> lf = new ArrayList(); // lista de filtros
        String[] kw = descri.split(" "); // para cada elemento del split se crea un filtro
        for(String x : kw){
            lf.add(regex("descripcion", ".*${x}.*", "i")); // el filtro es un elemento regex
        } // que comia el "like % %" del sql
        Iterator<Producto> it = getProductosCollection().find(and(lf)).iterator();
        ArrayList<Producto> lp = new ArrayList();
        it.forEachRemaining((x)->{ lp.add(x); }); // se pasan los elemntos del iterador a un arraylist
        return lp;
    }
    
    /**
     * encuentra los productos cuyo codigo de barras termina en
     * los digitos especificados por el sting b
     * @return 
     */
    public ArrayList<Producto> getPrdByLastCod(String b){
        Iterator<Producto> it = getProductosCollection().find(regex("_id",".*${b}$","i")).iterator();
        //String q = "{_id:{$regex:'.*%1s$'}}";//q = String.format(q, b);
        ArrayList<Producto> lp = new ArrayList();
        it.forEachRemaining((x)-> { lp.add(x); } );
        return lp;
    }
    
    
    
    /**
     * crea un respaldo de la base de datos como archivo .json
     */
    public void BackupJson(String dirfol){
        
        ArrayList<Producto> all = this.GetAllProducts();
    }
    
    /**
     * timestamp truncado
     * @return 
     */
    public String now(){
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace("T", " ");
    }
    
    // agregar un arrancador de la base de datos mongo y un ping para mongodb
    // si ocurre un ConectionRefusedException que orondo arranque mongod
    // de manera automatica
    
    
    // ################ Ventas
    
    public void saveVenta(Venta v){
        
    }
    
    
    /**
     * debe asignar el id a la venta ya que este debe ser un concecutivo
     * segun lo dispuesto por la dian para facturacion POS.
     * 
     * @param v 
     */
    public void InsertVenta(Venta v){
        
    }
    
    public final String consecutivoKey = "concecutivo"; 
    public final String Key_id = "_id"; 
    
    /**
     * verifica que existan las colecciones en mongo db que esta aplicacion usa
     */
    public void InitMongo(){
        
        // Se inicializa el valor de inicio de consecutivo de facturacion.
        MongoClient mongoClient = MongoClients.create();
        
        MongoCollection<Document> cln_f = getRangoFCollection();
        
        Iterator<Document> it = cln_f.find(eq(Key_id, consecutivoKey)).iterator();
        
        if(!it.hasNext()){
            System.out.println("Creando colleccion de rangos de facturacion...");
            Document consecutivo = new Document(Key_id, consecutivoKey)
                    .append("valor", "0");
            cln_f.insertOne(consecutivo);
            System.out.println("Rango de facturacion inicializado");
        } else{
            System.out.println("El concecutivo de facturacion ya se inicializo");
        }
        
    }
}
