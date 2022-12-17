module net.golbarg.kankor.kankor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.h2database;

    opens net.golbarg.kankor.kankor to javafx.fxml;
    exports net.golbarg.kankor.kankor;
    exports net.golbarg.kankor.kankor.controller;
    opens net.golbarg.kankor.kankor.controller to javafx.fxml;
}