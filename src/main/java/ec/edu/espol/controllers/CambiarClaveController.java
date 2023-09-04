/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Utilitaria;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class CambiarClaveController implements Initializable {

    @FXML
    private PasswordField actualContra;
    @FXML
    private PasswordField nuevaContra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("PerfilUsuario");
    }

    @FXML
    private void cambiarContraseña(MouseEvent event) {
        String contraActual = actualContra.getText();
        String contraseñaNueva = nuevaContra.getText();
        if (Usuario.cambiarContraseña(contraActual, contraseñaNueva)){
            actualContra.setText("");
            nuevaContra.setText("");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Contraseña cambiada con éxito");
            a.show();
            
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Contraseña actual incorrecta");
            a.show();
    }
}

}
