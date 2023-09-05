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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author crisj
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView imgViewRealizarOferta;
    @FXML
    private ImageView imgViewVehiculo;
    @FXML
    private ImageView imgViewAceptarOferta;
    @FXML
    private ImageView imgViewSesion;
    
    private static ArrayList<Vehiculo> vehiculosFiltrados;
    private static Stage primaryStage;
    private static Stage secondaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarVehiculo(MouseEvent event) throws IOException {
        App.setRoot("BuscarVehiculo");
    }

    @FXML
    private void registrarVehiculo(MouseEvent event) throws IOException {
        App.setRoot("VehiculoRegistro");
    }

    @FXML
    private void aceptarOferta(MouseEvent event) throws IOException {
        App.setRoot("PrevioOferta");

    }

    @FXML
    private void cerrarSesion(MouseEvent event) throws IOException {
        App.setUsuario(null);
        App.setRoot("PantallaInicio");
    }

    @FXML
    private void perfilUsuario(MouseEvent event) throws IOException {
        App.setRoot("PerfilUsuario");
    }
     private void vehiculosScene(MouseEvent event) throws IOException {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BorderPane newView = FXMLLoader.load(getClass().getResource("/ec/edu/espol/sistemaventavehiculo/AceptarOferta.fxml"));
        Scene newScene = new Scene(newView);
        secondaryStage = new Stage();
        secondaryStage.setScene(newScene);
        secondaryStage.setMaximized(true);
        secondaryStage.setOnCloseRequest(e -> regresar());
        primaryStage.hide();
        secondaryStage.show();
    }
    
    public void regresar(){
        primaryStage.show();
    }
}

