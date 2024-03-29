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
    requires com.h2database;
    requires PDFViewerFX;
    requires json.simple;


    opens net.golbarg.kankor to javafx.fxml;
    exports net.golbarg.kankor;
    exports net.golbarg.kankor.view;
    opens net.golbarg.kankor.view to javafx.fxml;
    exports net.golbarg.kankor.view.user;
    opens net.golbarg.kankor.view.user to javafx.fxml;
    exports net.golbarg.kankor.view.exam;
    opens net.golbarg.kankor.view.exam to javafx.fxml;
    exports net.golbarg.kankor.view.exam.component;
    opens net.golbarg.kankor.view.exam.component to javafx.fxml;
    exports net.golbarg.kankor.view.exam.report;
    opens net.golbarg.kankor.view.exam.report to javafx.fxml;
}