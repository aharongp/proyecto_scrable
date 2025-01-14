package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserMenuController {

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnModificarDatos;

    @FXML
    private Button btnMostrarEstadisticas;

    @FXML
    private Button btnMostrarUsuarios;

    @FXML
    private Button btnSalir;

    @FXML
    public void initialize() {
        btnRegistrarse.setOnAction(event -> {
            System.out.println("Registrarse clickeado");
        });

        btnModificarDatos.setOnAction(event -> {
            System.out.println("Modificar Datos clickeado");
        });

        btnMostrarEstadisticas.setOnAction(event -> {
            System.out.println("Mostrar Estadisticas clickeado");
        });

        btnMostrarUsuarios.setOnAction(event -> {
            System.out.println("Mostrar Usuarios clickeado");
        });

        btnSalir.setOnAction(event -> {
            System.out.println("Salir clickeado");
            System.exit(0);
        });
    }
}