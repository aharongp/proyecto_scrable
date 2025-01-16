package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import juego.ButtonInfo;
import juego.Partida;
import juego.Tablero;

import java.util.ArrayList;
import java.util.List;

public class TableroController {
    private Partida partida;

    @FXML
    private HBox playerTiles; // HBox para las fichas del jugador

    @FXML
    private GridPane board; // GridPane para el tablero de juego

    private String selectedLetter = null; // Letra seleccionada por el jugador

    @FXML
    public void initialize() {
        // Asignar evento a las fichas del jugador
        for (Node node : playerTiles.getChildren()) {
            if (node instanceof Button button) {
                button.setOnMouseClicked(event -> onTileClick(event, button));
            }
        }

        // Asignar evento a los botones del tablero
        for (Node node : board.getChildren()) {
            if (node instanceof Button button) {
                button.setOnMouseClicked(event -> onBoardClick(event, button, board));
            }
        }
    }

    // Evento al hacer clic en una ficha del jugador
    private void onTileClick(MouseEvent event, Button tileButton) {
        selectedLetter = tileButton.getText(); // Guardar la letra seleccionada
        System.out.println("Ficha seleccionada: " + selectedLetter);
    }

    // Evento al hacer clic en el tablero
    private void onBoardClick(MouseEvent event, Button boardButton, GridPane board) {
        if (selectedLetter != null) {
            // Cambiar el texto del botón del tablero
            boardButton.setText(selectedLetter);

            // Extraer las coordenadas del botón
            Integer row = GridPane.getRowIndex(boardButton);
            Integer col = GridPane.getColumnIndex(boardButton);

            // Manejar índices nulos
            row = (row == null) ? 0 : row;
            col = (col == null) ? 0 : col;

            System.out.println("Letra '" + selectedLetter + "' colocada en fila: " + row + ", columna: " + col);

            // Desmarcar la ficha seleccionada
            selectedLetter = null;
        } else {
            System.out.println("No hay ficha seleccionada.");
        }
    }
}
