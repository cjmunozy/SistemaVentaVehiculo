/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Camioneta;
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

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author crisj
 */
public class BuscarVehiculoController implements Initializable {


    @FXML
    private Button btnBuscar;
    @FXML
    private CheckBox chkAuto;
    @FXML
    private CheckBox chkCamioneta;
    @FXML
    private CheckBox chkMotocicleta;
    @FXML
    private TextField recoMin;
    @FXML
    private TextField añoMin;
    @FXML
    private TextField recoMax;
    @FXML
    private TextField añoMax;
    @FXML
    private TextField precMin;
    @FXML
    private TextField precMax;
    
    private static ArrayList<Vehiculo> vehiculosFiltrados;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void buscar(MouseEvent event) {
        try {
            int tipo = opcionesChk();
            String recoMinString = recoMin.getText();
            String recoMaxString = recoMax.getText();
            String añoMinString = añoMin.getText();
            String añoMaxString = añoMax.getText();
            String precMinString = precMin.getText();
            String precMaxString = precMax.getText();
            vehiculosFiltrados = Usuario.busquedaDeVehiculo(App.getVehiculos(), tipo, recoMinString, recoMaxString, añoMinString, añoMaxString, precMinString, precMaxString);
            if (!vehiculosFiltrados.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Búsqueda realizada con éxito");
                StringBuilder informacion = new StringBuilder();
                informacion.append("");
                for(Vehiculo v : vehiculosFiltrados){
                    v.mostrarDetallesVehiculo();
                    informacion.append(v.reunirDetallesVehiculo());
                    informacion.append("\n");
                }
                a.setContentText(informacion.toString());
                a.showAndWait();
            }
            else{
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            limpiarCampos();
            Alert a = new Alert(Alert.AlertType.WARNING, "Lo sentimos ningún vehículo coincide con sus criterios de búsqueda.");
            a.show();
        } catch (NumberFormatException e) {
            limpiarCampos() ;
            Alert b = new Alert(Alert.AlertType.WARNING, "Valores ingresados incorrectos. Intente de nuevo");
            b.show();
        }
    }
    
    private void limpiarCampos() {
        recoMin.setText("");
        recoMax.setText("");
        añoMin.setText("");
        añoMax.setText("");
        precMin.setText("");
        precMax.setText("");
    }
    
    private int opcionesChk(){
        if(chkAuto.isSelected() && chkCamioneta.isSelected() && chkMotocicleta.isSelected())
            return 4;
        else if(chkCamioneta.isSelected())
            return 3;
        else if(chkAuto.isSelected())
            return 2;
        else
            return 1;
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
       App.setRoot("Menu");
    }
}
