package com.example.usuarios;

import javafx.fxml.FXML;
import juego.Jugador;

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
    protected void onButtonEstadisticas(){}
    @FXML
    protected void onButtonRegresar(){}

}
