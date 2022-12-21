module net.golbarg.kankor.kankor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.kordamp.ikonli.fontawesome5;
    requires eu.hansolo.tilesfx;
    requires com.h2database;

    opens net.golbarg.kankor to javafx.fxml;
    exports net.golbarg.kankor;
    exports net.golbarg.kankor.controller;
    opens net.golbarg.kankor.controller to javafx.fxml;
}