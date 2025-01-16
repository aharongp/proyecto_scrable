package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.*;

public class InicioDeSesionController {

    @FXML
    private TextField textJugador1;

    @FXML
    private TextField textJugador2;

    @FXML

    protected void onButtonIniciar(ActionEvent event) {
        try {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/interfazUsuario/MenuDePartida.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu De Registro");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onButtonSalir(){}
}
