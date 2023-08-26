/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class VehiculoRegistroController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private Button auto;
    @FXML
    private Button camioneta;
    @FXML
    private Button moto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAuto(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(600);
        BorderPane view = FXMLLoader.load(getClass().getResource("/ec/edu/espol/sistemaventavehiculo/AutoRegistro.fxml"));
        mainPane.setCenter(view);
    }
    

    @FXML
    private void btnCamioneta(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(600);
        BorderPane view = FXMLLoader.load(getClass().getResource("/ec/edu/espol/sistemaventavehiculo/CamionetaRegistro.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    private void btnMoto(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(600);
        BorderPane view = FXMLLoader.load(getClass().getResource("/ec/edu/espol/sistemaventavehiculo/MotoRegistro.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(435);
        App.setRoot("Menu");

    }
    
}
