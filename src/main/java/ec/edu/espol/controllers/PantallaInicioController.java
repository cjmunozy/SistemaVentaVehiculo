/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.TipoUsuario;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
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
    private void ingresar(MouseEvent event) throws IOException {
        try{
        String correoNuevo = correo.getText();
        String contra= contraseña.getText();
        ArrayList<Usuario> listaU = Usuario.cargarUsuarios("usuarios.ser");
        if (correoNuevo.isEmpty() || contra.isEmpty()) throw new IllegalArgumentException();
        Usuario uValido = Usuario.comprobarCyC(listaU, correoNuevo, contra);
        if (uValido!=null) {
            if(uValido.getTipoUsuario().equals(TipoUsuario.VENDEDOR))
                App.setRoot("MenuVendedor");
            else
                App.setRoot("MenuComprador");
            } else {
                contraseña.setText("");
                correo.setText("");
                Alert a = new Alert(Alert.AlertType.ERROR, "Correo o contraseña incorrectos.. Acceso denegado");
                a.show();    
        }}catch(IllegalArgumentException e) {
            correo.setText("");
            contraseña.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR,"Por favor, rellene todos los campos");
            alert.show();
        }
    }

    @FXML
    private void registroUsuario(MouseEvent event) throws IOException {
        App.setRoot("CompradorRegistro");
    }
    
}
