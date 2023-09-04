/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Imagen;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author crisj
 */
public class VehiculosMuestrasController implements Initializable {

    @FXML
    private Button btnOfertar;
    @FXML
    private TableView<Vehiculo> tbViewInformacion;
    @FXML
    private TableColumn<Vehiculo, String> tcPlaca;
    @FXML
    private TableColumn<Vehiculo, String> tcMarca;
    @FXML
    private TableColumn<Vehiculo, String> tcModelo;
    @FXML
    private TableColumn<Vehiculo, String> tcTipoMotor;
    @FXML
    private TableColumn<Vehiculo, Integer> tcAño;
    @FXML
    private TableColumn<Vehiculo, Double> tcRecorrido;
    @FXML
    private TableColumn<Vehiculo, String> tcColor;
    @FXML
    private TableColumn<Vehiculo, String> tcTipoCombustible;
    @FXML
    private TableColumn<Vehiculo, Double> tcPrecio;
    @FXML
    private TableColumn<Vehiculo, Integer> tcVidrios;
    @FXML
    private TableColumn<Vehiculo, String> tcTransmision;
    @FXML
    private TableColumn<Vehiculo, String> tcTraccion;
    @FXML
    private ImageView imgvVehiculo;
    
    
    private static Vehiculo vehiculo;

    /**
     * Initializes the controller class.
     */
    
    public static Vehiculo getVehiculo() {
        return vehiculo; 
    }    
    
    public static void setVehiculo(Vehiculo vehiculo) {
        VehiculosMuestrasController.vehiculo = vehiculo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            ObservableList<Vehiculo> olv = FXCollections.observableArrayList(BuscarVehiculoController.getVehiculosFiltrados());
            tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
            tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            tcTipoMotor.setCellValueFactory(new PropertyValueFactory<>("tipoMotor"));
            tcAño.setCellValueFactory(new PropertyValueFactory<>("año"));
            tcRecorrido.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
            tcColor.setCellValueFactory(new PropertyValueFactory<>("color"));
            tcTipoCombustible.setCellValueFactory(new PropertyValueFactory<>("tipoCombustible"));
            tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            tcVidrios.setCellValueFactory(new PropertyValueFactory<>("vidrios"));
            tcTransmision.setCellValueFactory(new PropertyValueFactory<>("transmision"));
            tcTraccion.setCellValueFactory(new PropertyValueFactory<>("traccion"));
            tbViewInformacion.setItems(olv);
            tbViewInformacion.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            tbViewInformacion.setOnMouseClicked(e -> obtenerVehiculoSeleccionado(e));
        } catch(IllegalStateException ex){
        }
    }    

    @FXML
    private void ofertar(MouseEvent event) {
    }
    
    private void obtenerVehiculoSeleccionado(MouseEvent e){
        Vehiculo v;
        if(e.getClickCount() == 1){
            v = tbViewInformacion.getSelectionModel().getSelectedItem();
            if(v != null){
                setVehiculo(v);
                mostrarImagen(v);
            }
        }
    }
    
    private void mostrarImagen(Vehiculo v){
        for(Imagen i : App.getImagenes())
            if(i.getVehiculo().equals(v))
                imgvVehiculo.setImage(new Image(this.getClass().getResource(i.getRuta()).toString()));
    }
}
