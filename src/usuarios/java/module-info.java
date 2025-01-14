module com.example.proyecto_poo_interfaz {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.proyecto_poo_interfaz to javafx.fxml;
    exports com.example.proyecto_poo_interfaz;
}