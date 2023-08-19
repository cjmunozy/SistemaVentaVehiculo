/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author crisj
 */
public class PantallaInicioController implements Initializable {

    @FXML
    private TextField correo;
    @FXML
    private PasswordField contraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ingresar(MouseEvent event) {
        String correoNuevo = correo.getText();
        String contra= contraseña.getText();
        ArrayList<Usuario> listaU = Usuario.cargarUsuarios("usuarios.ser");
        if (correoNuevo.isEmpty() || contra.isEmpty()) {
            contraseña.setText("");
            correo.setText("");
            Alert b = new Alert(Alert.AlertType.WARNING, "Asegúrese de rellenar todos los campos.");
            b.show();
        } else {
            Usuario uValido = Usuario.comprobarCyC(listaU, correoNuevo, contra);
            if (listaU.contains(uValido)) {
                contraseña.setText("");
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Correo y contraseña correctos. Acceso aprobado");
                a.setGraphic(new ImageView(this.getClass().getResource("/img/checked.png").toString()));
                a.setTitle("Bienvenido!");
                a.show();
            } else {
                contraseña.setText("");
                correo.setText("");
                Alert a = new Alert(Alert.AlertType.ERROR, "Correo o contraseña incorrectos.. Acceso denegado");
                a.show();
            }
        }
    }
    
}
