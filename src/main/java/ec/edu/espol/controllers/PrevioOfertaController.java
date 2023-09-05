/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Utilitaria;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class PrevioOfertaController implements Initializable {

    @FXML
    private TextField placaIngresada;
    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static String placa;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public static String getPlaca() {
        return placa;
        // TODO
    }    
    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.setRoot("Menu");
    }

    @FXML
    private void mostrarOfertas(MouseEvent event) throws IOException {
        try{
            placa = placaIngresada.getText();
            vehiculos = App.getVehiculos();
            if(Utilitaria.validarPlaca(vehiculos, placa)&&placa!=null){
                throw new NullPointerException("La placa ingresada no existe en el sistema");
            }else{
                placaIngresada.setText("");
                Alert a = new Alert(Alert.AlertType.INFORMATION,"La placa ingresa existe en el sistema,\npuede avanzar con la gestión de ofertas");
                a.setGraphic(new ImageView(this.getClass().getResource("/img/checked.png").toString()));
                a.showAndWait();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setWidth(700); 
                stage.setHeight(460);
                App.setRoot("AceptarOferta");
                
            }
                
        }catch(NullPointerException e){
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.show();

        }
    }
    
}
