module com.zelda.zelda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires org.junit.jupiter.api;

    opens com.zelda.zelda to javafx.fxml;
    exports com.zelda.zelda;
    exports com.zelda.zelda.controleur;
    opens com.zelda.zelda.controleur to javafx.fxml;
    exports com.zelda.zelda.modele;
    opens com.zelda.zelda.modele to javafx.fxml;


    exports com.zelda.zelda.modele.acteur;
    opens com.zelda.zelda.modele.acteur to javafx.fxml;
    exports com.zelda.zelda.modele.Consommable;
    opens com.zelda.zelda.modele.Consommable to javafx.fxml;

}