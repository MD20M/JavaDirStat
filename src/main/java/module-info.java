module com.md20m.javadirstat.javadirstat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;

    opens com.md20m.javadirstat.javadirstat to javafx.fxml;
    exports com.md20m.javadirstat.javadirstat;
}