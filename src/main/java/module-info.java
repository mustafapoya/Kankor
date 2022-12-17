module net.golbarg.kankor.kankor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    opens net.golbarg.kankor.kankor to javafx.fxml;
    exports net.golbarg.kankor.kankor;
}