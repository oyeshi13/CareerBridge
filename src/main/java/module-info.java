module com.oyeshi_fabiha.careerbridge2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.oyeshi_fabiha.careerbridge2 to javafx.fxml;
    exports com.oyeshi_fabiha.careerbridge2;
}