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
    
    
    
    //*********MORPHIA**********
    
    // conector de mongodb y morphia
    requires mongo.java.driver;
    
    // lo empezo a pedir despues de usar @validations 
    // haciendo import dev.morphia.annotations.Validation parece que tambien
    // hace parte de morphia
    requires io.github.classgraph;
    // tambien parecen que son de morphia
    requires core;
    requires proxytoys;
    requires slf4j.api;
    // quito el warning de slf4j
    // pero se genera error ERROR StatusLogger No Log4j 2 configuration file found
    //requires org.apache.logging.log4j;
    
    requires json.simple;
    
    
    // si no se agrega este modulo ocurre una excepcion cuando se instancia un
    // objeto de la clase Morphia con new Morphia()
    requires java.sql;
    //***************
    requires java.prefs; // java preferences
    requires java.logging;
    
    
    opens Orondo.productos to javafx.fxml;
    opens Orondo.inicio to javafx.fxml;
    exports Orondo.inicio;
    exports Orondo.productos;
    exports Orondo.OrondoDb;
    exports Orondo.Styling;
    
    // informacion practica y teorica sobre javamodules
    // https://livebook.manning.com/book/the-java-module-system/about-this-book/
}