module Orondo.main {

    //  javaFX
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    
    opens Orondo.main to javafx.fxml;
    opens Orondo.productos to javafx.fxml;

    // ikonli. para usar fontawesome en javaFX
    //requires org.kordamp.iconli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.metrizeicons;
    requires org.kordamp.ikonli.entypo;
    requires org.kordamp.iconli.core;
    
    // conector de mongodb y morphia
    requires mongo.java.driver;
    /*
    requires core;
    requires proxytoys;
    requires io.github.classgraph;
    requires slf4j.api;
    */
    
    // si no se agrega este modulo ocurre una excepcion cuando se instancia un
    // objeto de la clase Morphia con new Morphia()
    requires java.sql;

    exports Orondo.main;
    exports Orondo.productos;
    exports Orondo.OrondoDb;
}