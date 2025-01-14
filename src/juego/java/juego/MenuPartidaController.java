package juego;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPartidaController {

    @FXML
    private Button nuevaPartidaButton;

    @FXML
    private Button continuarPartidaButton;

    @FXML
    private Button verEstadisticasButton;

    @FXML
    private Button salirButton;

    // Método para inicializar el controlador
    @FXML
    private void initialize() {
        nuevaPartidaButton.setOnAction(event -> handleNuevaPartida());
        continuarPartidaButton.setOnAction(event -> handleContinuarPartida());
        verEstadisticasButton.setOnAction(event -> handleVerEstadisticas());
        salirButton.setOnAction(event -> handleSalir());
    }

    // Manejar la acción del botón "Nueva juego.Partida"
    private void handleNuevaPartida() {
        System.out.println("Nueva juego.Partida iniciada");
        // Aquí puedes añadir la lógica para iniciar una nueva partida
    }

    // Manejar la acción del botón "Continuar juego.Partida"
    private void handleContinuarPartida() {
        System.out.println("Continuando juego.Partida");
        // Aquí puedes añadir la lógica para continuar una partida existente
    }

    // Manejar la acción del botón "Ver Estadísticas"
    private void handleVerEstadisticas() {
        System.out.println("Mostrando Estadísticas");
        // Aquí puedes añadir la lógica para mostrar las estadísticas
    }

    // Manejar la acción del botón "Salir"
    private void handleSalir() {
        System.out.println("Saliendo del juego");
        // Aquí puedes añadir la lógica para salir del juego
    }
}
