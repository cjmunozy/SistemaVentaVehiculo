/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Auto;
import ec.edu.espol.model.TipoVehiculo;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
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
public class AutoRegistroController implements Initializable {

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
    private TextField transmision;
    @FXML
    private TextField vidrios;

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

        if (marcaText.isEmpty() || placaText.isEmpty() || recorridoTextStr.isEmpty() ||
            añoTextStr.isEmpty() || motorText.isEmpty() || colorText.isEmpty() ||
            modeloText.isEmpty() || precioTextStr.isEmpty() || combustibleText.isEmpty()) {
            throw new IllegalArgumentException(); 
        }

        double recorridoText = Double.parseDouble(recorridoTextStr);
        int añoText = Integer.parseInt(añoTextStr);
        double precioText = Double.parseDouble(precioTextStr);
        int vidriosText = Integer.parseInt(vidriosTextStr);

        ArrayList<Vehiculo> vehiculos = Vehiculo.cargarVehiculos("vehiculos.ser");
        Auto nuevoVehiculo = Auto.pedirDatosAuto(vehiculos, placaText, marcaText, modeloText, motorText, añoText, recorridoText, colorText, combustibleText, precioText, TipoVehiculo.AUTO, vidriosText, transmisionText);
    if (nuevoVehiculo != null) {
        vehiculos.add(nuevoVehiculo);
        Vehiculo.guardarArchivoVehiculos("vehiculos.ser", vehiculos);
        limpiarCampos();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Vehículo registrado con éxito");
        a.showAndWait();
    }
    } catch (NullPointerException e) {
        limpiarCampos();
        Alert a = new Alert(Alert.AlertType.WARNING, "El vehículo ya existe");
        a.show();
    } catch (NumberFormatException e) {
        Alert b = new Alert(Alert.AlertType.WARNING, "Valores ingresados incorrectos. Intente de nuevo");
        b.show();
    } catch (IllegalArgumentException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR,"Por favor, rellene todos los campos");
        alert.show();
    }
        }

    private void limpiarCampos(){
        placa.setText("");
        recorrido.setText("");
        año.setText("");
        marca.setText("");
        motor.setText("");
        color.setText("");
        modelo.setText("");
        precio.setText("");
        combustible.setText("");
        transmision.setText("");
        vidrios.setText("");
        
}






}

