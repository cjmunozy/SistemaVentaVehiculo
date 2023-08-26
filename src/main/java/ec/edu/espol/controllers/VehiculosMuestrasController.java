/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class VehiculosMuestrasController implements Initializable {

    @FXML
    private TextArea txtArea;
    @FXML
    private ImageView imgv;
    
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    
    private int indice = 0;
    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void regresar(MouseEvent event) {
    indice--;
        if (indice < 0) {
            indice = vehiculos.size() - 1;
            mostrarVehiculo(indice);
        } else {
            mostrarVehiculo(indice);
        }
        btnSiguiente.setVisible(true);
    
}

    @FXML
    private void avanzar() {
    indice++;
        if (indice >= vehiculos.size()) {
            indice = 0;
            mostrarVehiculo(indice);
        } else {
            mostrarVehiculo(indice);
        }
        btnAnterior.setVisible(true);
    }


    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void mostrarVehiculo(int i) {
        if (!vehiculos.isEmpty() && i >= 0 && i < vehiculos.size()) {
            Vehiculo v = vehiculos.get(i);
            StringBuilder informacion = new StringBuilder();
            informacion.append(v.reunirDetallesVehiculo());
            informacion.append("\n");

            txtArea.setText(informacion.toString());
            System.out.println("Mostrando vehículo en índice: " + i);
        }
        btnAnterior.setVisible(indice > 0);
        btnSiguiente.setVisible(indice < vehiculos.size() - 1);
    }

    @FXML
    private void regresarPagina(MouseEvent event) throws IOException {
        App.setRoot("BuscarVehiculo");
    }
}