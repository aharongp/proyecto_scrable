package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import juego.ButtonInfo;
import juego.Partida;
import juego.Tablero;

import java.util.ArrayList;
import java.util.List;

public class TableroController {
    private Partida partida;

    @FXML
    private GridPane gridpane;

    public List<ButtonInfo> extractButtonData() {
        GridPane gridPane = this.gridpane;
        List<ButtonInfo> buttonInfoList = new ArrayList<>();

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button button) {
                Integer row = GridPane.getRowIndex(node);
                Integer col = GridPane.getColumnIndex(node);

                row = (row == null) ? 0 : row;
                col = (col == null) ? 0 : col;

                buttonInfoList.add(new ButtonInfo(button.getText(), row, col));
            }
        }
        return buttonInfoList;
    }
}
