module com.example.hashcheck {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.apache.commons.codec;

    opens com.example.hashcheck to javafx.fxml;
    exports com.example.hashcheck;
}