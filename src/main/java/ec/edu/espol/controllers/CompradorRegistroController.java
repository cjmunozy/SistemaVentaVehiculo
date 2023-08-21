/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

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
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class CompradorRegistroController implements Initializable {


    @FXML
    private TextField correo;
    @FXML
    private PasswordField contraseña;
    @FXML
    private TextField apellido;
    @FXML
    private TextField nombre;
    @FXML
    private TextField organizacion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void registrar(MouseEvent event) throws IOException {
        try{
            String correoStr = correo.getText();
            String contraStr = contraseña.getText();
            String apellidoStr = apellido.getText();
            String nombreStr = nombre.getText();
            String organizacionStr = organizacion.getText();
            if (correoStr.isEmpty() || contraStr.isEmpty() || apellidoStr.isEmpty() ||
            nombreStr.isEmpty() || organizacionStr.isEmpty()){ throw new IllegalArgumentException(); 
        }
            ArrayList<Usuario> usuarios = Usuario.cargarUsuarios("usuarios.ser");
            Usuario usuarioNuevo = Usuario.registrarComprador(usuarios, nombreStr, apellidoStr, organizacionStr, correoStr, contraStr);
            if (usuarioNuevo != null) {
                correo.setText("");
                contraseña.setText("");
                organizacion.setText("");
                nombre.setText("");
                apellido.setText("");
                App.setRoot("MenuComprador");
                }
        }catch(IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Por favor, rellene todos los campos");
            alert.show();
        }
    }

}
