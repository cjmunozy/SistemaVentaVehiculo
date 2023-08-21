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
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author crisj
 */
public class MenuVendedorController implements Initializable {

    @FXML
    private ImageView imgViewVendedor;
    @FXML
    private ImageView imgViewVehiculo;
    @FXML
    private ImageView imgViewOferta;
    @FXML
    private ImageView imgViewSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrarVendedor(MouseEvent event) throws IOException {
        
        App.setRoot("UsuarioRegistro");
    }

    @FXML
    private void registrarVehiculo(MouseEvent event) throws IOException {
        App.setRoot("VehiculoRegistro");
    }

    @FXML
    private void aceptarOferta(MouseEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Nada por ahora");
        a.show();
    }

    @FXML
    private void cerrarSesion(MouseEvent event) throws IOException {
        App.setUsuario(null);
        App.setRoot("PantallaInicio");
    }
    
}
