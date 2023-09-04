/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class PerfilUsuarioController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Label organizacion;
    @FXML
    private Label correo;
    @FXML
    private Label apellido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nombre.setText(App.getUsuario().getNombres());
       apellido.setText(App.getUsuario().getApellidos());
       organizacion.setText(App.getUsuario().getOrganizacion());
       correo.setText(App.getUsuario().getCorreo());

    }    

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("Menu");

    }

    @FXML
    private void cambiarContraseña(MouseEvent event) throws IOException {
        App.setRoot("CambiarClave");
    }
    
}
