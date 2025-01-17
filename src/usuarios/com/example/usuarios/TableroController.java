package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import juego.ButtonInfo;
import juego.Jugador;
import juego.Partida;
import juego.Tablero;

import java.util.*;

public class TableroController {
    private Partida partida;

    @FXML
    private HBox playerTiles; // HBox para las fichas del jugador

    @FXML
    private GridPane board; // GridPane para el tablero de juego

    @FXML
    private Button sendButton;

    private Button selectedTileButton = null;
    private String selectedLetter = null; // Letra seleccionada por el jugador

    private Integer columna;
    private Integer fila;
    private boolean horizontal;

    // Mapa para rastrear qué botón del HBox corresponde a cada letra colocada
    private Map<String, Button> usedTilesMap = new HashMap<>();

    public void iniciarPartida(Jugador jugador1, Jugador jugador2){
        this.partida = new Partida(jugador1, jugador2);
    }

    @FXML
    public void initialize() {
        iniciarPartida(new Jugador("aharon", "aharon@gmail.com"), new Jugador("jose", "jose@gmail.com"));
        if (partida.getActualTurn() == 1){
            mostrarFichas(partida.getJugador1());
        }else {
            mostrarFichas(partida.getJugador2());
        }

        // Asignar evento a las fichas del jugador
        for (Node node : playerTiles.getChildren()) {
            if (node instanceof Button button) {
                button.setOnMouseClicked(event -> onTileClick(event, button));
            }
            sendButton.setOnMouseClicked(event -> onSendClick());
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

            if (currentText != null && !currentText.isEmpty()) {
                // Validar si el botón del tablero ya tiene una ficha
                System.out.println("El espacio ya está ocupado. No se puede cambiar la ficha.");
                return;
            }
            // Cambiar el texto del botón del tablero
            boardButton.setText(selectedLetter);

            // Extraer las coordenadas del botón
            Integer row = GridPane.getRowIndex(boardButton);
            Integer col = GridPane.getColumnIndex(boardButton);

            // Manejar índices nulos
            row = (row == null) ? 0 : row;
            col = (col == null) ? 0 : col;

            horizontal = Objects.equals(fila, row);
            System.out.println(horizontal);
            columna = (columna == null) ? col : columna;
            System.out.println(columna);

            fila = (fila == null) ? row : fila;
            System.out.println(fila);

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

    private String[][] getBoardAsMatrix(GridPane board) {
        // Determinar las dimensiones del GridPane
        int rows = board.getRowCount();
        int cols = board.getColumnCount();

        // Crear una matriz para almacenar el contenido del tablero
        String[][] matrix = new String[rows][cols];

        // Recorrer los hijos del GridPane
        for (Node node : board.getChildren()) {
            if (node instanceof Button button) {
                // Obtener la posición del botón
                Integer row = GridPane.getRowIndex(node);
                Integer col = GridPane.getColumnIndex(node);

                // Manejar índices nulos (por defecto fila/columna es 0)
                row = (row == null) ? 0 : row;
                col = (col == null) ? 0 : col;

                // Asignar el texto del botón a la posición correspondiente en la matriz
                matrix[row][col] = button.getText();
            }
        }

        // Rellenar celdas vacías con un valor predeterminado (por ejemplo, "")
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = ""; // Dejar vacío si no hay texto
                }
            }
        }

        return matrix;
    }

    private String getWordFromMatrix(String[][] board, int startRow, int startCol, boolean isHorizontal) {
        StringBuilder word = new StringBuilder();

        // Validar las dimensiones de la matriz
        int rows = board.length;
        int cols = (rows > 0) ? board[0].length : 0;

        // Recorrer en la dirección especificada
        if (isHorizontal) {
            for (int col = startCol; col < cols; col++) {
                String cell = board[startRow][col];
                if (cell == null || cell.isEmpty()) {
                    break; // Detenerse si se encuentra una celda vacía
                }
                word.append(cell);
            }
        } else { // Dirección vertical
            for (int row = startRow; row < rows; row++) {
                String cell = board[row][startCol];
                if (cell == null || cell.isEmpty()) {
                    break; // Detenerse si se encuentra una celda vacía
                }
                word.append(cell);
            }
        }
        return word.toString();
    }

    private void onSendClick(){
        String[][] tablero = getBoardAsMatrix(board);
        String palabra = getWordFromMatrix(tablero, fila, columna, horizontal);
        if (partida.getActualTurn() == 1){
            partida.ubicarPalabra(palabra, fila, columna, horizontal, partida.getJugador1());
        } else {
            partida.ubicarPalabra(palabra, fila, columna, horizontal, partida.getJugador2());
        }
        partida.getTablero().mostrarTablero();
        System.out.println(palabra);
        partida.alternarTurno();
        if (partida.getActualTurn() == 1){
            mostrarFichas(partida.getJugador1());
        }else {
            mostrarFichas(partida.getJugador2());
        }

        for (Node node : playerTiles.getChildren()) {
            if (node instanceof Button button) {
                button.setDisable(false);
            }
        }
        usedTilesMap = new HashMap<>();
    }

    private void mostrarFichas(Jugador jugador){
        int index=0;
        juego.FichasJugador fichasJugador = jugador.getPlayerCharacters();
        ArrayList<juego.Character> fichas = fichasJugador.getFichas();
            for (Node node : playerTiles.getChildren()) {
               if (node instanceof Button button){
                   button.setText(fichas.get(index).getSymbol());
               }
               if (index < 6) index +=1;
            }
    }


}
