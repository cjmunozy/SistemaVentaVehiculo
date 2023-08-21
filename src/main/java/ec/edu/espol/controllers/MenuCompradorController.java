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

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author crisj
 */
public class MenuCompradorController implements Initializable {


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
    private void realizarOferta(MouseEvent event) {
    }

    @FXML
    private void cerrarSesion(MouseEvent event) throws IOException {
        App.setUsuario(null);
        App.setRoot("PantallaInicio");
    }

}
