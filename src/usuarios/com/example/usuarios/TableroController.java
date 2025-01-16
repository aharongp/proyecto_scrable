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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableroController {
    private Partida partida;

    @FXML
    private HBox playerTiles; // HBox para las fichas del jugador

    @FXML
    private GridPane board; // GridPane para el tablero de juego

    private Button selectedTileButton = null;
    private String selectedLetter = null; // Letra seleccionada por el jugador

    // Mapa para rastrear qué botón del HBox corresponde a cada letra colocada
    private final Map<String, Button> usedTilesMap = new HashMap<>();

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
        if (!tileButton.isDisable()) { // Solo permitir seleccionar fichas activas
            selectedLetter = tileButton.getText(); // Guardar la letra seleccionada
            selectedTileButton = tileButton; // Guardar el botón seleccionado
            System.out.println("Ficha seleccionada: " + selectedLetter);
        } else {
            System.out.println("Esta ficha ya fue usada.");
        }
    }

    // Evento al hacer clic en el tablero
    private void onBoardClick(MouseEvent event, Button boardButton, GridPane board) {
        String currentText = boardButton.getText();

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

            // Deshabilitar la ficha seleccionada para que no pueda usarse de nuevo
            if (selectedTileButton != null) {
                usedTilesMap.put(selectedLetter, selectedTileButton); // Asociar letra con el botón del HBox
                selectedTileButton.setDisable(true);
                selectedTileButton = null; // Limpiar la referencia al botón seleccionado
            }

            // Desmarcar la ficha seleccionada
            selectedLetter = null;

        } else if (currentText != null && !currentText.isEmpty()) {
            // Restaurar una ficha colocada
            Button originalTileButton = usedTilesMap.get(currentText);
            if (originalTileButton != null) {
                originalTileButton.setDisable(false); // Habilitar el botón original
                usedTilesMap.remove(currentText); // Quitar del mapa de fichas usadas
                boardButton.setText(""); // Limpiar el texto del tablero
                System.out.println("Ficha '" + currentText + "' restaurada al HBox.");
            }
        } else {
            System.out.println("No hay ficha seleccionada y el botón está vacío.");
        }
    }
}
