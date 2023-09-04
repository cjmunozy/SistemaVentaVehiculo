/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
    private static Stage primaryStage;
    private static Stage secondaryStage;
    /**
     * Initializes the controller class.
     */
    
    public static ArrayList<Vehiculo> getVehiculosFiltrados() {
        return vehiculosFiltrados;
        // TODO
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void buscar(MouseEvent event) throws IOException {
        try {
            int tipo = opcionesChk();
            System.out.println("Opcion seleccionada:"+tipo);
            String recoMinString = recoMin.getText();
            String recoMaxString = recoMax.getText();
            String añoMinString = añoMin.getText();
            String añoMaxString = añoMax.getText();
            String precMinString = precMin.getText();
            String precMaxString = precMax.getText();
            vehiculosFiltrados = Usuario.busquedaDeVehiculo(App.getVehiculos(), App.getUsuario(), tipo, recoMinString, recoMaxString, añoMinString, añoMaxString, precMinString, precMaxString);
            
            if (!vehiculosFiltrados.isEmpty()) {
                vehiculosScene(event);
            } else {
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
        } catch (IOException e) {
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
         System.out.println("Seleccionado Auto: " + chkAuto.isSelected());
    System.out.println("Seleccionado Camioneta: " + chkCamioneta.isSelected());
    System.out.println("Seleccionado Motocicleta: " + chkMotocicleta.isSelected());
    

        if((chkAuto.isSelected() && chkCamioneta.isSelected() && chkMotocicleta.isSelected()) || (!chkAuto.isSelected())&&(!chkCamioneta.isSelected())&&(!chkMotocicleta.isSelected()) )
            return 7;
        else if(chkCamioneta.isSelected()&&chkAuto.isSelected())
            return 5;
        else if(chkAuto.isSelected()&&chkMotocicleta.isSelected())
            return 4;
        else if(chkMotocicleta.isSelected()&&chkCamioneta.isSelected())
            return 3;
        else if(chkAuto.isSelected())
            return 2;
        else if(chkCamioneta.isSelected())
            return 6;
        else
            return 1;
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
       App.setRoot("Menu");
    }
    
    private void vehiculosScene(MouseEvent event) throws IOException {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BorderPane newView = FXMLLoader.load(getClass().getResource("/ec/edu/espol/sistemaventavehiculo/VehiculosMuestras.fxml"));
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
