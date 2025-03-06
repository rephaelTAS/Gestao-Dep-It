module packt.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.apache.poi.ooxml; // Adicione esta linha se você estiver usando o poi-ooxml
    requires org.apache.xmlbeans;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens controler.main to javafx.fxml;

    exports controler.main;
    exports controler.outher;
    exports controler.login;

    opens templates.main to javafx.fxml;
    opens controler.login to javafx.fxml;
    opens controler.outher to javafx.fxml;
    opens controler.outher.additem to javafx.fxml;

    opens controler.outher.visualizar to javafx.fxml;


    exports app to javafx.graphics;
    opens app to javafx.fxml; // Necessário para o controlador
    opens model to javafx.base; // Adicione esta linha

    opens controler.outher.relatorio to javafx.fxml;
}
