/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class HacerOfertaController implements Initializable {

    @FXML
    private TextField precioOferta;
    private Vehiculo vehiculo = VehiculosMuestrasController.getVehiculo();

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("VehiculosMuestras");
    }


    @FXML
    private void realizarOferta(MouseEvent event) {
       String precio = precioOferta.getText();
        if (vehiculo != null) {
            double oferta = Double.parseDouble(precio);
            Oferta of = new Oferta(vehiculo,App.getUsuario(),oferta);
            vehiculo.getListaOfertas().add(of);
            App.getOfertas().add(of);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "La oferta se realizo perfectamente");
            a.show();
        }

    }
    
}
