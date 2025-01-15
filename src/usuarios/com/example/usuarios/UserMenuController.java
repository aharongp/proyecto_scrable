package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserMenuController {

    @FXML
    protected void onButtonRegistrar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/interfazUsuario/MenuDeRegistro.fxml"));
            Parent root=fxmlLoader.load();
            Stage stage=new Stage();
            stage.setTitle("MenuDeRegistro");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}