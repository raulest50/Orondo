package Orondo.productos;

import Orondo.OrondoDb.Producto;
import Orondo.OrondoDb.dbMapper;
import Orondo.Styling.Styler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Raul Alzate
 */
public class codificarController {
    
    @FXML
    public TextField TextField_Codigo;
    
    @FXML
    public TextField TextField_Iva;
    
    @FXML
    public TextField TextField_Descripcion;

    @FXML
    public TextField TextField_StockInit;

    @FXML
    public TextField TextField_Costo;

    @FXML
    public TextField TextField_PvMayor;

    @FXML
    public TextField TextField_PvPublico;
    
    @FXML 
    public TextArea TextArea_keywords;

    @FXML
    public Button Button_Codificar;

    @FXML
    public Button Button_Borrar;
    
    /**
     * para importar productos desde un .json file. el boton incia con
     * visible = false. para hacerlo visible se debe escribir importar en
     * el text area keywords la palabra "importar" y luego hacer click derecho
     * en el boton codificar. ver metodo onClickButtonCodificar.
     */
    @FXML
    public Button Button_Importar;
    
    
    public void initialize() {
        // se configuran estos textFields para que solo reciban numeros
        Styler.SetTextFieldAsNumeric(TextField_Costo);
        Styler.SetTextFieldAsNumeric(TextField_PvMayor);
        Styler.SetTextFieldAsNumeric(TextField_PvPublico);
        Styler.SetTextFieldAsNumeric(TextField_StockInit);
        Styler.SetTextFieldAsNumeric(TextField_Iva);
        
        
    }
    
    @FXML
    void onClickButtonCodificar(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY){
            if(TextArea_keywords.getText().equals("importar")){
                Button_Importar.setVisible(true);
                TextArea_keywords.setText("");
            }
        } else{
            String codigo = TextField_Codigo.getText();
            String descripcion = TextField_Descripcion.getText();
            int costo = Integer.parseInt(TextField_Costo.getText());
            int pvmayor = Integer.parseInt(TextField_PvMayor.getText());
            int pvpublico = Integer.parseInt(TextField_PvPublico.getText());
            
            Double stock;// si stock se deja vacio se asume 0
            String strStock = TextField_StockInit.getText();
            if(strStock.isBlank() || strStock.isEmpty()) stock = 0.0;
            else stock = Double.parseDouble(TextField_StockInit.getText());
            
            Double iva = Double.parseDouble(TextField_Iva.getText());
            String last_updt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace("T", " ");
            String keywords = TextArea_keywords.getText();
            
            Producto p = new Producto(codigo, descripcion, costo, pvmayor, pvpublico, iva, last_updt, keywords);
            dbMapper db = new dbMapper();
            db.SaveProduct(p);
        }
    }
    
    @FXML
    void onClickButtonImportar(MouseEvent event){
        // se lee el archivo .json
        FileChooser fileChooser = new FileChooser();
        File jsonFile = fileChooser.showOpenDialog((Stage) Button_Importar.getScene().getWindow());
        
        // dentro del try se toma cada uno de los records del archivo .json y
        // se insertan en la base de datos de productos
        
        JSONParser jsonParser = new JSONParser();
        
        // try with resources. el resourse es reader, y esto indica que se debe 
        // de cerrar despues de terminar el bloque try catch
        try (FileReader reader = new FileReader(jsonFile)){
            
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray listaProductosJson = (JSONArray) obj;
             
            //Iterate over employee array
            listaProductosJson.forEach(jsonObj -> {
                JSONObject pjson = (JSONObject) jsonObj; // producto en formato json
                
                String codigo = (String) pjson.get("codigo");
                String descripcion = (String) pjson.get("descripcion");
                int costo = ((Double) pjson.get("costo")).intValue();
                int pvmayor = ((Double) pjson.get("pvtienda")).intValue();
                int pvpublico = ((Double) pjson.get("pvpublico")).intValue();
                Double iva = (Double) pjson.get("iva");
                String last_updt = (String) pjson.get("ultima_actualizacion");
                String keywords = (String) pjson.get("Familia");
                
                Producto p = new Producto(codigo, descripcion, costo, pvmayor, pvpublico, iva, last_updt, keywords);
                dbMapper db = new dbMapper();
                db.SaveProduct(p);
                });
 
        } catch (FileNotFoundException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
        } catch (IOException | ParseException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    //los siguientes metodos permiten pasar entre los textfield al pulsar enter
    @FXML
    void onActionTF_Codigo(ActionEvent event){
        TextField_Descripcion.requestFocus();
    }
    @FXML
    void onActionTF_Descripcion(ActionEvent event){
        TextField_Costo.requestFocus();
    }
    @FXML
    void onActionTF_Costo(ActionEvent event){
        TextField_PvMayor.requestFocus();
    }
    @FXML
    void onActionTF_PVxMayor(ActionEvent event){
        TextField_PvPublico.requestFocus();
    }
    @FXML
    void onActionTF_PVPublico(ActionEvent event){
        TextField_Iva.requestFocus();
    }
    @FXML
    void onActionTF_IVA(ActionEvent event){
        TextField_StockInit.requestFocus();
    }
    @FXML
    void onActionTF_StockInit(ActionEvent event){
        TextArea_keywords.requestFocus();
    }
    
}
