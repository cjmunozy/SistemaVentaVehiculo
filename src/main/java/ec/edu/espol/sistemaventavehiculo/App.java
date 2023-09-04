package ec.edu.espol.sistemaventavehiculo;

import ec.edu.espol.model.Imagen;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Utilitaria;
import ec.edu.espol.model.Vehiculo;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javafx.application.Platform;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private static Stage st;
    private static ArrayList<Usuario> usuarios;
    private static ArrayList<Vehiculo> vehiculos;
    private static ArrayList<Oferta> ofertas;
    private static ArrayList<Imagen> imagenes;
    private static Usuario usuario;

    public static Scene getScene() {
        return scene;
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public static ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public static ArrayList<Imagen> getImagenes() {
        return imagenes;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        App.usuario = usuario;
    }
    
    @Override
    public void init(){
        usuarios = Usuario.cargarUsuarios("usuarios.ser");
        vehiculos = Vehiculo.cargarVehiculos("vehiculos.ser");
        ofertas = Oferta.cargarOfertas("ofertas.ser");
        imagenes = Imagen.cargarImagenes("imagenes.ser");
//        usuarios = Usuario.readFile("usuarios.txt");
//        vehiculos = Vehiculo.readFile("vehiculos.txt");
//        ofertas = Oferta.readFile("ofertas.txt");
//        if(!ofertas.isEmpty())
//            Utilitaria.relacionar(usuarios, vehiculos, ofertas);
        imprimirObjetos();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("PantallaInicio").load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Venta Vehículo");
        stage.show();
        scene.getWindow().setOnCloseRequest(e -> Platform.exit());
    }

    @Override
    public void stop() throws Exception {
        Usuario.guardarArchivoUsuarios("usuarios.ser", usuarios);
        Vehiculo.guardarArchivoVehiculos("vehiculos.ser", vehiculos);
        Oferta.guardarArchivoOfertas("ofertas.ser", ofertas);
        Imagen.guardarArchivoImagenes("imagenes.ser", imagenes);
//        Usuario.saveFile(usuarios, "usuarios.txt");
//        Vehiculo.saveFile(vehiculos, "vehiculos.txt");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    public static void setScene(Scene sc) throws IOException {
        st.setScene(sc);
    }
     public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
//        ArrayList<Usuario> usuarios = Usuario.cargarUsuarios("usuarios.ser");
////        ArrayList<Vehiculo> vehiculos = Vehiculo.cargarVehiculos("vehiculos.ser");
//        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("vehiculos.txt");
//        ArrayList<Oferta> ofertas = Oferta.cargarOfertas("ofertas.ser");
//        if(!ofertas.isEmpty())
//            Utilitaria.relacionar(usuarios, vehiculos, ofertas);
//        for(Usuario u : usuarios)
//            System.out.println(u);
//        for(Vehiculo v : vehiculos)
//            System.out.println(v);
//        for(Oferta o : ofertas)
//            System.out.println(o);
//        System.out.println("Bienvenido a VentaVehiculo");
//        Scanner sc = new Scanner(System.in);
//        sc.useDelimiter("\n");
//        sc.useLocale(Locale.US);
//        int opcion;
//        do{
//            opcion = Utilitaria.MenuOpciones(sc);
//            switch(opcion){
//                case 1:
//                    opcion = Utilitaria.OpcionesVendedor(usuarios, vehiculos, ofertas, sc);
//                    break;
//                case 2:
//                    opcion = Utilitaria.OpcionesComprador(usuarios, vehiculos, ofertas, sc);
//                    break;
//                case 3:
//                    System.out.println("\n ¡Gracias por utilizar nuestro sistema, esperamos verte en otra ocasión!");
//                    break;
//                default:
//                    System.out.println("Opción no encontrada.");
//                    System.out.println("");
//                    opcion = -1;
//            }
//        }while(opcion < 1 || opcion > 3);
        launch();
    }
    
    private void imprimirObjetos(){
        for(Usuario u : usuarios){
            System.out.println(u);
            for(Vehiculo v : u.getVehiculos()){
                System.out.println(v);
                for(Oferta o : v.getListaOfertas())
                   System.out.println(o); 
            }
            for(Oferta o : u.getOfertas())
                System.out.println(o);
        }
        for(Vehiculo v : vehiculos)
            System.out.println(v);
        for(Imagen i : imagenes)
            System.out.println(i);
        for(Oferta o : ofertas)
            System.out.println(o);
    }
}