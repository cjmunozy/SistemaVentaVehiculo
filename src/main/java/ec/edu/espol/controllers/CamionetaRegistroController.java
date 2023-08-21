/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Camioneta;
import ec.edu.espol.model.TipoVehiculo;
import ec.edu.espol.model.Vehiculo;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
public class CamionetaRegistroController implements Initializable {

    @FXML
    private TextField marca;
    @FXML
    private TextField placa;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField año;
    @FXML
    private TextField motor;
    @FXML
    private TextField color;
    @FXML
    private TextField modelo;
    @FXML
    private TextField precio;
    @FXML
    private TextField combustible;
    @FXML
    private TextField traccion;
    @FXML
    private TextField vidrios;
    @FXML
    private TextField transmision;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrar(MouseEvent event) {
        try {
            String marcaText = marca.getText();
            String placaText = placa.getText();
            String recorridoTextStr = recorrido.getText();
            String añoTextStr = año.getText();
            String motorText = motor.getText();
            String colorText = color.getText();
            String modeloText = modelo.getText();
            String precioTextStr = precio.getText();
            String combustibleText = combustible.getText();
            String transmisionText = transmision.getText();
            String vidriosTextStr = vidrios.getText();
            String traccionText = traccion.getText(); // Nuevo campo para camionetas

            if (marcaText.isEmpty() || placaText.isEmpty() || recorridoTextStr.isEmpty() ||
                añoTextStr.isEmpty() || motorText.isEmpty() || colorText.isEmpty() ||
                modeloText.isEmpty() || precioTextStr.isEmpty() || combustibleText.isEmpty() ||
                traccionText.isEmpty()) {
                throw new IllegalArgumentException("Por favor, complete todos los campos.");
            }

            double recorridoText = Double.parseDouble(recorridoTextStr);
            int añoText = Integer.parseInt(añoTextStr);
            double precioText = Double.parseDouble(precioTextStr);
            int vidriosText = Integer.parseInt(vidriosTextStr);

            ArrayList<Vehiculo> vehiculos = Vehiculo.cargarVehiculos("vehiculos.ser");
            Camioneta nuevaCamioneta = Camioneta.pedirDatosCamioneta(vehiculos, placaText, marcaText, modeloText, motorText, añoText, recorridoText, colorText, combustibleText, precioText, TipoVehiculo.CAMIONETA, vidriosText, transmisionText, traccionText);
            if (nuevaCamioneta != null) {
                vehiculos.add(nuevaCamioneta);
                Vehiculo.guardarArchivoVehiculos("vehiculos.ser", vehiculos);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Vehículo registrado con éxito");
                a.show();
            }
        } catch (NumberFormatException e) {
        Alert b = new Alert(Alert.AlertType.WARNING, "Por favor, ingrese valores numéricos válidos en los campos numéricos.");
        b.show();
    } catch (IllegalArgumentException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR,"Rellena los campos");
        alert.show();
    }
        }
}