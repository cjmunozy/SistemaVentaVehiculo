/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Imagen;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author hyunj
 */
public class AceptarOfertaController implements Initializable {

    @FXML
    private TableColumn<Contenido, String> tcCorreo;
    @FXML
    private TableColumn<Contenido, Double> tcPrecio;
    @FXML
    private Button btnOfertar;
    @FXML
    private ImageView imgvVehiculo;
    @FXML
    private TableView<Contenido> tablaOfertas;
    @FXML
    private TableColumn<Contenido, String> tcNombres;
    @FXML
    private TableColumn<Contenido, String> tcApellidos;
    @FXML
    private BorderPane mainPane;

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(435);
        stage.setWidth(600);
        App.setRoot("PrevioOferta");
    }

    public class Contenido {
        private String nombre;
        private String apellido;
        private String correo;
        private double precio;

        public Contenido(String nombre, String apellido, String correo, double precio) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.correo = correo;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }
        
}
    
    @FXML
    private void aceptarOferta(MouseEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String placa = PrevioOfertaController.getPlaca();
         for (Vehiculo vehiculo : App.getVehiculos()) { 
            if (vehiculo.getPlaca().equals(placa)) {
                 mostrarImagen(vehiculo);
        }
        }
          tcNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
          tcApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
          tcCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
          tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
          ArrayList<Oferta> listaOfertas = App.getOfertas();
          ArrayList<Contenido> elementos = new ArrayList<>();
          for (Oferta oferta : listaOfertas) {
            if (oferta.getPlaca().equals(placa)) {
                elementos.add(new Contenido(
                        oferta.getUsuario().getNombres(),
                        oferta.getUsuario().getApellidos(),
                        oferta.getUsuario().getCorreo(),
                        oferta.getPrecio()
            ));
        }
        }
          ObservableList<Contenido> listaElementos = FXCollections.observableArrayList(elementos);
          tablaOfertas.setItems(listaElementos);
          
          
        }
     


        private void mostrarImagen(Vehiculo v){
        for(Imagen i : App.getImagenes())
            if(i.getVehiculo().equals(v))
                imgvVehiculo.setImage(new Image(this.getClass().getResource(i.getRuta()).toString()));
    }
}

   