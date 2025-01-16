package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuDePartidaController {
    private Jugador jugador1;
    private Jugador jugador2;

    public void setContext(Jugador jugador1,Jugador jugador2){
        this.jugador1=jugador1;
        this.jugador2=jugador2;
    }

    @FXML
    protected void onButtonNuevoJuego(){}
    @FXML
    protected void onButtonContinuarJuego(){}
    @FXML
    protected void onButtonRegresar(){}

    @FXML
    private Button buttonEstadisticas;

    @FXML
    protected void onButtonEstadisticas() {

        try {
            Stage currentStage = (Stage) buttonEstadisticas.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/MenuDePartida/MenuDeEstadisticas.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Estadísticas");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
