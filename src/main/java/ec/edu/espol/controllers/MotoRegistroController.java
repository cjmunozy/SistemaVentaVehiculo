/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Imagen;
import ec.edu.espol.model.TipoVehiculo;
import ec.edu.espol.model.Utilitaria;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class MotoRegistroController implements Initializable {

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
    
    private boolean imagenSeleccionada = false;
    private Imagen imagen;
    private File origen;
    private File destino;

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
        String placaText = placa.getText();
        String marcaText = marca.getText();
        String recorridoText = recorrido.getText();
        String añoText = año.getText();
        String motorText = motor.getText();
        String colorText = color.getText();
        String modeloText = modelo.getText();
        String precioText = precio.getText();
        String combustibleText = combustible.getText();

        if (marcaText.isEmpty() || placaText.isEmpty() || recorridoText.isEmpty() ||
            añoText.isEmpty() || motorText.isEmpty() || colorText.isEmpty() ||
            modeloText.isEmpty() || precioText.isEmpty() || combustibleText.isEmpty()) {
            throw new IllegalArgumentException("Por favor, complete todos los campos.");
        }
        if(imagenSeleccionada){
            double recorridoDouble = Double.parseDouble(recorridoText);
            int añoInt = Integer.parseInt(añoText);
            double precioDouble = Double.parseDouble(precioText);

            Vehiculo nuevaMoto = Vehiculo.pedirDatosVehiculo(App.getVehiculos(), placaText, App.getUsuario(), marcaText, modeloText, motorText, añoInt, recorridoDouble, colorText, combustibleText, precioDouble);
            if (nuevaMoto != null) {
                App.getVehiculos().add(nuevaMoto);
                App.getUsuario().agregarVehiculo(nuevaMoto);
                Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                imagen = new Imagen(placaText, "/Vehiculos_Imagenes/" + placaText + origen.getName().substring(origen.getName().lastIndexOf(".")));
                imagen.setVehiculo(nuevaMoto);
                App.getImagenes().add(imagen);
                limpiarCampos();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Vehículo registrado con éxito");
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Seleccione una imagen del vehículo");
            a.show();
        }
    } catch (NullPointerException e) {
        limpiarCampos();
        Alert a = new Alert(Alert.AlertType.WARNING, "El vehículo ya existe");
        a.show();
        } catch (NumberFormatException e) {
        Alert b = new Alert(Alert.AlertType.WARNING, "Valores ingresados incorrectos. Intente de nuevo.");
        b.show();
    } catch (IllegalArgumentException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR,"Por favor, rellene todos los campos");
        alert.show();
    }
}
    
    private void limpiarCampos() {
    marca.setText("");
    placa.setText("");
    recorrido.setText("");
    año.setText("");
    motor.setText("");
    color.setText("");
    modelo.setText("");
    precio.setText("");
    combustible.setText("");
}

    @FXML
    private void adjuntarFoto(MouseEvent event) {
        if(!placa.getText().isEmpty()){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecciona una imagen");    
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(imageFilter);
            origen = fileChooser.showOpenDialog(null);
         
            if (origen != null) {
                String placa1 = placa.getText();
                String carpetaDestino = "src/main/resources/Vehiculos_Imagenes";
                String nombre = origen.getName();
                String nuevoNombreArchivo = placa1 + nombre.substring(nombre.lastIndexOf("."));
                String rutaDestino = carpetaDestino + File.separator + nuevoNombreArchivo;
                destino = new File(rutaDestino);
                imagenSeleccionada = true;
           }
        } else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Rellene primero los demás campos antes de agregar la foto");
            a.show();
        }
    }
}