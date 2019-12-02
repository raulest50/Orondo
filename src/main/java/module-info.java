module Orondo.main {

    //  javaFX
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    opens Orondo.main to javafx.fxml;

    // ikonli. para usar fontawesome en javaFX
    //requires org.kordamp.iconli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    exports Orondo.main;
}