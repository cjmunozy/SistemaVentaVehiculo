package ec.edu.espol.sistemaventavehiculo;

import ec.edu.espol.modeloventavehiculo.Oferta;
import ec.edu.espol.modeloventavehiculo.Usuario;
import ec.edu.espol.modeloventavehiculo.Utilitaria;
import ec.edu.espol.modeloventavehiculo.Vehiculo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = Usuario.readFile("usuarios.txt");
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("vehiculos.txt");
        ArrayList<Oferta> ofertas = Oferta.cargarOfertas("ofertas.txt");
        if(!ofertas.isEmpty())
            Utilitaria.relacionar(usuarios, vehiculos, ofertas);
        System.out.println("Bienvenido a VentaVehiculo");
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        sc.useLocale(Locale.US);
        int opcion;
        do{
            opcion = Utilitaria.MenuOpciones(sc);
            switch(opcion){
                case 1:
                    opcion = Utilitaria.OpcionesVendedor(usuarios, vehiculos, ofertas, sc);
                    break;
                case 2:
                    opcion = Utilitaria.OpcionesComprador(usuarios, vehiculos, ofertas, sc);
                    break;
                case 3:
                    System.out.println("\n ¡Gracias por utilizar nuestro sistema, esperamos verte en otra ocasión!");
                    break;
                default:
                    System.out.println("Opción no encontrada.");
                    System.out.println("");
                    opcion = -1;
            }
        }while(opcion < 1 || opcion > 3);
        launch();
    }

}