module com.zelda.zelda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.zelda.zelda to javafx.fxml;
    exports com.zelda.zelda;
    exports com.zelda.zelda.controleur;
    opens com.zelda.zelda.controleur to javafx.fxml;
}