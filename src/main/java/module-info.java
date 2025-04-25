module packt.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.apache.poi.ooxml;
    requires org.apache.xmlbeans;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    // Abertura de pacotes para FXML
    opens packt.app.MainConfig.modules to javafx.base;
    opens packt.app.MainConfig.controlers.login to javafx.fxml;
    opens packt.app.MainConfig.controlers.outher to javafx.fxml;
    opens packt.app.MainConfig.controlers.main to javafx.fxml;
    opens packt.app.MainConfig.controlers.outher.visualizar to javafx.fxml;
    opens packt.app.MainConfig.controlers.outher.relatorio to javafx.fxml;
    opens packt.app.MainConfig.controlers.outher.additem to javafx.fxml;
    exports packt.app.MainConfig.controlers.outher.additem to javafx.fxml;
    exports packt.app.MainConfig.controlers.outher.relatorio to javafx.fxml;
    exports packt.app.MainConfig.controlers.outher.visualizar to javafx.fxml;
    // Exportação de pacotes


    exports packt.app.MainConfig.controlers.main;
    exports packt.app.MainConfig.controlers.outher;
    exports packt.app.MainConfig.controlers.login;
    exports packt.app; // Se necessário
}