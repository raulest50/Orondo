module Orondo {

    //  javaFX
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    

    // ikonli. para usar fontawesome en javaFX
    //requires org.kordamp.iconli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.metrizeicons;
    requires org.kordamp.ikonli.entypo;
    requires org.kordamp.iconli.core;
    
    
    // quito el warning de slf4j
    // pero se genera error ERROR StatusLogger No Log4j 2 configuration file found
    // requires org.apache.logging.log4j;
    
    
    // CONECTOR MONGO DB
    requires json.simple;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    
    // se debe agregar porque lo usa el conector de mongodb.
    // de lo contrario ocurre este error
    // exception={com.mongodb.MongoException: java.lang.NoClassDefFoundError: jdk/net/ExtendedSocketOptions}
    requires jdk.net;
    
    // si no se agrega este modulo ocurre una excepcion cuando se instancia un
    // objeto de la clase Morphia con new Morphia()
    // requires java.sql;
    
    
    requires java.prefs; // java preferences
    requires java.logging;
    
    requires better.strings; // para poder hacer string interpolation en java
    
    //The standard Java libraries fail to provide enough methods for manipulation of its core classes.
    //Apache Commons Lang provides these extra methods.
    requires org.apache.commons.lang3;
    
    requires org.eclipse.paho.client.mqttv3; // mqtt client only
    
    requires nv.websocket.client; // websocket server-client
    
    requires com.google.gson; // best suited than simplejson for certain tasks
    
    opens Orondo.productos to javafx.fxml;
    opens Orondo.ventas to javafx.fxml;
    opens Orondo.inicio to javafx.fxml;
    
    exports Orondo.inicio;
    exports Orondo.productos;
    exports Orondo.OrondoDb;
    exports Orondo.Styling;
    
    
    // informacion practica y teorica sobre javamodules
    // https://livebook.manning.com/book/the-java-module-system/about-this-book/
}