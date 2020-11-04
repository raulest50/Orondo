/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orondo.ventas;

import Orondo.Net.ListaVentaNotifyChangeMsg;
import Orondo.Net.MqttOrondoClient;
import Orondo.OrondoDb.ItemVenta;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Raul Alzate
 */
public class monitorear_retailController {
    
    
    
    
    @FXML
    public ListView ListView_Tablet1;
    
    /**
     * HBox en el que se agregan tableViews por cada nodo punto de venta.
     */
    @FXML
    public HBox HBox_Nodos;
    
    
    /**
     * Key corresponde al id de la tablet en Mqtt y el value al linked list con
     * los itemventas respectivos.
     */
    private HashMap<String, NodoBlock> NodosVentas = new HashMap<>();
    
    
    Consumer<String> cback = msg ->{
        try{this.LV_notifyChange(msg);} 
        catch(Exception e){Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "", e);}
    };
    MqttOrondoClient mqttCli = new MqttOrondoClient(cback);
    
    
    
    
    public void initialize() {
        Executors.newSingleThreadExecutor().execute(()->{
            ConectarAlBroker(); // la conexion puede bloquear la UI
        });
        
    }
    
    public void ConectarAlBroker(){
        try{ // se Conecta al broker para iniciar comunicacion Mqtt
            mqttCli.Conectar();
        } catch(MqttException e){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    
    public void LV_notifyChange(String msg){
        try{
            ListaVentaNotifyChangeMsg lnm = new ListaVentaNotifyChangeMsg(msg);
            if(this.NodosVentas.containsKey(lnm.mqttClientId)){
                NodosVentas.get(lnm.mqttClientId).setLista(lnm.lv);
            } else{
                NodosVentas.put(lnm.mqttClientId, new NodoBlock(lnm.lv));
            }
        } catch(ParseException e){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "LV_notifyChange(msg)", e);
        }
    }
    
    /**
     * crea un table view con 3 columnas. cada tableView permite visualizar en 
     * tiempo real las ventas de cada punto de pago.
     * @return 
     */
    public TableView<ItemVenta> genTableView(){
        TableView tb = new TableView();
        TableColumn<String, ItemVenta> tc_descri = new TableColumn("Descripcion");
        TableColumn<Integer, ItemVenta> tc_N = new TableColumn("N");
        TableColumn<Integer, ItemVenta> tc_subT = new TableColumn("subTotal");
        
        tc_descri.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tc_N.setCellValueFactory(new PropertyValueFactory("cantidad"));
        tc_subT.setCellValueFactory(new PropertyValueFactory("subTotal"));
        
        tb.getColumns().addAll(tc_descri, tc_N, tc_subT);
        return tb;
    }
    
    /**
     * para encapsular varios elemetos en un key del hashmap
     */
    private class NodoBlock{
        LinkedList<ItemVenta> lv;
        TableView<ItemVenta> tb;
        
        VBox vbox;
        Label total_label = new Label();

        public NodoBlock(LinkedList<ItemVenta> lv) {
            this.lv = lv; 
            this.tb = genTableView();
            this.tb.getItems().setAll(this.lv);
            vbox = new VBox(tb, total_label);
            SetStyle();
            Platform.runLater(()->{
                HBox_Nodos.getChildren().add(vbox);
                UpdateLabel();
            });
        }
        
        public void setLista(LinkedList<ItemVenta> lv){
            this.lv = lv;
            this.tb.getItems().setAll(this.lv);
            Platform.runLater(()->{ // GUI Operations
                UpdateLabel();
            });
        }
        
        private void UpdateLabel(){
            int s = 0;
            for(ItemVenta iv:lv){s+=iv.getSubTotal();}
            total_label.setText("${Integer.toString(s)} $");
        }
        
        private void SetStyle(){
            vbox.setPadding(new Insets(0, 15, 0, 0));
            total_label.setFont(new Font(20));
            total_label.setTextFill(Color.DARKGREEN);
        }
    }
}
