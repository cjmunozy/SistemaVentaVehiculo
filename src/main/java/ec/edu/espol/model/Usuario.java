/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import ec.edu.espol.controllers.PantallaInicioController;
import ec.edu.espol.sistemaventavehiculo.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

/**
 *
 * @author crisj
 */
public class Usuario implements Serializable{
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo;
    private String clave;
    private TipoUsuario tipoUsuario;
    private ArrayList<Oferta> ofertas;
    private static final long serialVersionUID = 8799656478674716638L;
    
    public Usuario(String nombres, String apellidos, String organizacion, String correo, String clave, TipoUsuario tipoUsuario) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
        if(tipoUsuario.equals(TipoUsuario.COMPRADOR))
            this.ofertas = new ArrayList<>();
    }

    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Usuario other = (Usuario) o;
        return Objects.equals(this.correo, other.correo);
    }
    
    public static void guardarArchivoUsuarios(String nomFile, ArrayList<Usuario> usuarios){
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(nomFile))){
           ous.writeObject(usuarios);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<Usuario> cargarUsuarios(String nomFile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFile))){
            usuarios = (ArrayList<Usuario>) ois.readObject();
        }
        catch(ClassNotFoundException ex1){
            System.out.println(ex1.getMessage());
        }catch(IOException ex2){
            System.out.println(ex2.getMessage());
        }
        return usuarios;
    }
    
    public static ArrayList<Usuario> readFile(String nomFile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                Usuario u;
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                if(tokens[5].equals("VENDEDOR"))
                    u = new Usuario(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], TipoUsuario.VENDEDOR);
                else
                    u = new Usuario(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], TipoUsuario.COMPRADOR);
                usuarios.add(u);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }
    
    public void saveFile(String nomFile,boolean append){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), append)))
        {
           pw.println(this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo+"|"+this.clave+"|"+this.tipoUsuario);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveFile(ArrayList<Usuario> usuarios, String nomFile){
        for (int i = 0; i < usuarios.size(); i++){
            if(i == 0)
                usuarios.get(i).saveFile(nomFile, false);
            else
                usuarios.get(i).saveFile(nomFile, true);
        }
    }
    
    
    public static int registrarComprador(ArrayList<Usuario> usuarios, Scanner sc){
        boolean validacion = false;
        System.out.println("\nPOR FAVOR, COMPLETE LOS SIGUIENTES DATOS PARA REGISTRARSE \n");
        System.out.println("Ingrese sus nombres: ");
        String nombresU = sc.nextLine();
        System.out.println("Ingrese sus apellidos: ");
        String apellidosU = sc.nextLine();
        System.out.println("Ingrese su organización: ");
        String organizacionU = sc.nextLine();
        String correoU;
        do{
            System.out.println("Ingrese el correo electrónico: ");
            correoU = sc.nextLine();
            validacion = Utilitaria.validarCorreo(usuarios, correoU);
        }while(validacion == false);
        System.out.println("Ingrese la clave: ");
        String claveU = sc.nextLine();
        Usuario uNuevo = new Usuario(nombresU, apellidosU, organizacionU, correoU, Utilitaria.claveHash(claveU), TipoUsuario.COMPRADOR);
        usuarios.add(uNuevo);
        Usuario.guardarArchivoUsuarios("usuarios.ser", usuarios);
        System.out.println("Usuario registrado con éxito");
        return -1;
    }
    
    public static ArrayList<Vehiculo> busquedaDeVehiculo(ArrayList<Vehiculo> vehiculos, Scanner sc){
        ArrayList<Vehiculo> vehiculosPorBusqueda = new ArrayList<>();
        System.out.println("Ingrese los criterios de búsqueda del vehiculo que desea:");
        System.out.println("Ingrese el tipo de vehículo : ");
        System.out.println("1. Motocicleta");
        System.out.println("2. Auto");
        System.out.println("3. Camioneta");
        System.out.println("4. Todos los tipos");
        int tipo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el Recorrido mínimo (opcional) : ");
        String recoMinString = sc.nextLine();
        System.out.print("Ingrese el Recorrido máximo (opcional) : ");
        String recoMaxString = sc.nextLine();
        System.out.print("Ingrese el Año mínimo (opcional) : ");
        String añoMinString = sc.nextLine();
        System.out.print("Ingrese el Año máximo (opcional) : ");
        String añoMaxString = sc.nextLine();
        System.out.print("Ingrese el Precio mínimo (opcional) : ");
        String precMinString = sc.nextLine();
        System.out.print("Ingrese el Precio máximo (opcional) : ");
        String precMaxString = sc.nextLine();
        
        double recoMin = 0;
        if (!recoMinString.equals("")) {
            recoMin = Double.parseDouble(recoMinString);
        }

        double recoMax = Double.MAX_VALUE;
        if (!recoMaxString.equals("")) {
            recoMax = Double.parseDouble(recoMaxString);
        }

        int añoMin = 1900;
        if (!añoMinString.equals("")) {
            añoMin = Integer.parseInt(añoMinString);
        }

        int añoMax = LocalDate.now().getYear() + 1;
        if (!añoMaxString.equals("")) {
            añoMax = Integer.parseInt(añoMaxString);
        }

        double precMin = 0;
        if (!precMinString.equals("")) {
            precMin = Double.parseDouble(precMinString);
        }

        double precMax = Double.MAX_VALUE;
        if (!precMaxString.equals("")) {
            precMax = Double.parseDouble(precMaxString);
        }
        
        String tipoVehiculo = "";
        for(Vehiculo v : vehiculos){
            switch(tipo){
                case 1:
                    tipoVehiculo = "MOTOCICLETA";
                    if(v.tipoVehiculo.equals(TipoVehiculo.valueOf(tipoVehiculo)) && encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                        vehiculosPorBusqueda.add(v);
                    break;
                case 2:
                    tipoVehiculo = "AUTO";
                    if(v.tipoVehiculo.equals(TipoVehiculo.valueOf(tipoVehiculo)) && encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                        vehiculosPorBusqueda.add(v);
                    break;
                case 3:
                    tipoVehiculo = "CAMIONETA";
                    if(v.tipoVehiculo.equals(TipoVehiculo.valueOf(tipoVehiculo)) && encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                        vehiculosPorBusqueda.add(v);
                    break;
                default:
                    if(encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                        vehiculosPorBusqueda.add(v);
            }
        }
    return vehiculosPorBusqueda;
    }

    public static boolean encontrarVehiculos(Vehiculo vehiculo,double recoMin,double recoMax,int añoMin,int añoMax,double precMin,double precMax){
        boolean recorrido = Utilitaria.enRango(vehiculo.recorrido, recoMin, recoMax);
        boolean años = Utilitaria.enRango(vehiculo.año, añoMin, añoMax);
        boolean precio = Utilitaria.enRango(vehiculo.precio, precMin, precMax);
        return recorrido && años && precio;
    }
    
    public static void ofertarPorVehiculo(ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, Usuario u, Scanner sc) {
        ArrayList<Vehiculo> vehiculosEncontrados = busquedaDeVehiculo(vehiculos, sc);
        if(!vehiculosEncontrados.isEmpty()){
            Vehiculo vehiculo = null;
            ListIterator<Vehiculo> iterator = vehiculosEncontrados.listIterator();
            int contador = 0;
            int opc;
            while(iterator.hasNext()){
                if(!iterator.hasPrevious()){
                    vehiculo = iterator.next();
                    contador++;
                    if(!iterator.hasNext()){
                        vehiculo.mostrarDetallesVehiculo();
                        System.out.println("1. Hacer Oferta");
                        opc = sc.nextInt();
                        break;
                    }else{
                        do{
                            vehiculo.mostrarDetallesVehiculo();
                            System.out.println("1. Siguiente Vehículo");
                            System.out.println("2. Hacer Oferta");
                            opc = sc.nextInt();
                        }while(opc < 1 || opc > 2);
                        if(opc == 2)
                            break;
                    }
                }else{
                    vehiculo = iterator.next();
                    contador++;
                    if(iterator.hasNext()){
                        do{
                            vehiculo.mostrarDetallesVehiculo();
                            System.out.println("1. Siguiente Vehículo");
                            System.out.println("2. Anterior Vehículo");
                            System.out.println("3. Hacer Oferta");
                            opc = sc.nextInt();
                        }while(opc < 1 || opc > 3);
                        if(opc == 2){
                            iterator.previous();
                            iterator.previous();
                            contador -= 2;
                        }else if(opc == 3)
                            break;
                    }else{
                        do{
                            vehiculo.mostrarDetallesVehiculo();
                            System.out.println("1. Anterior Vehículo");
                            System.out.println("2. Hacer Oferta");
                            opc = sc.nextInt();
                        }while(opc < 1 || opc > 2);
                        if(opc == 1){
                            iterator.previous();
                            iterator.previous();
                            contador -= 2;
                        }else
                            break;
                    } 
                }
            }
            if(vehiculo != null){
                System.out.println("Ingrese el precio ofertado:");
                double precioOferta = sc.nextDouble();
                sc.nextLine();
                Oferta oferta = new Oferta(vehiculo, u, precioOferta);
                vehiculo.agregarOferta(oferta);
                ofertas.add(oferta);
                u.agregarOferta(oferta);
                Oferta.guardarArchivoOfertas("ofertas.ser", ofertas);
                System.out.println("Su oferta ha sido realizada correctamente!");
                System.out.println("");
            }
        }else
            System.out.println("Ningún vehículo coincide con sus criterios de búsqueda");
    }
    
    public static Usuario comprobarCyC(ArrayList<Usuario> usuarios, Scanner sc){
        System.out.println("\nPOR FAVOR, COMPROBEMOS SUS DATOS");
        System.out.println("Ingrese su correo electronico: ");
        String correoE = sc.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseñaE = sc.nextLine();
        for (Usuario u: usuarios){
            if (correoE.equals(u.correo) && Utilitaria.claveHash(contraseñaE).equals(u.clave)){
                System.out.println("Correo y clave válidos");
                System.out.println("");
                return u;
            }
        }
        System.out.println("Correo o clave incorrectos");
        System.out.println("");
        return null;
    }
    public static Usuario registrarComprador(ArrayList<Usuario> usuarios, String nombre, String apellidos, String organizacion, String correo, String contra) throws IOException{
        if(Utilitaria.validarCorreo(usuarios, correo)==false){
            Alert a = new Alert(Alert.AlertType.ERROR,"El usuario ingresado ya existe");
            a.show();
            return null;
        }
            Usuario uNuevo = new Usuario(nombre, apellidos, organizacion, correo, Utilitaria.claveHash(contra),TipoUsuario.COMPRADOR);
            usuarios.add(uNuevo);
            Usuario.guardarArchivoUsuarios("usuarios.ser", usuarios);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Usuario registrado exitosamente.");
            a.show();
            App.setRoot("MenuComprador");
            return uNuevo;
    
    }
    public static Usuario registrarVendedor(ArrayList<Usuario> usuarios, String nombre, String apellidos, String organizacion, String correo, String contra) throws IOException{
        if(Utilitaria.validarCorreo(usuarios, correo)==false){
            Alert a = new Alert(Alert.AlertType.ERROR,"El usuario ya existe");
            a.show();
            return null;
        }
        Usuario uNuevo = new Usuario(nombre, apellidos, organizacion, correo, Utilitaria.claveHash(contra),TipoUsuario.VENDEDOR);
        usuarios.add(uNuevo);
        Usuario.guardarArchivoUsuarios("usuarios.ser", usuarios);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Usuario registrado exitosamente.");
        a.show();
        return uNuevo;
 
    }
    public static Usuario comprobarCyC(ArrayList<Usuario> usuarios, String correoE, String contraseñaE) {
    for (Usuario u: usuarios){
            if (correoE.equals(u.correo) && Utilitaria.claveHash(contraseñaE).equals(u.clave)){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Correo y contraseña correctos. Acceso aprobado");
                a.setGraphic(new ImageView(App.class.getResource("/img/checked.png").toString()));
                a.setTitle("Bienvenido!");
                a.show();
                return u;
            }
        }
        return null;
    }


    public void agregarOferta(Oferta o){
        this.ofertas.add(o);
    }
    
    //Métodos de Vendedor
    public static int registrarVendedor(ArrayList<Usuario> usuarios, Scanner sc){
        boolean validacion = false;
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR AL NUEVO VENDEDOR \n");
        System.out.println("Ingrese los nombres: ");
        String nombresU = sc.nextLine();
        System.out.println("Ingrese los apellidos: ");
        String apellidosU = sc.nextLine();
        System.out.println("Ingrese la organización: ");
        String organizacionU = sc.nextLine();
        String correoU;
        do{
            System.out.println("Ingrese el correo electrónico: ");
            correoU = sc.nextLine();
            validacion = Utilitaria.validarCorreo(usuarios, correoU);
        }while(validacion == false);
        System.out.println("Ingrese la clave:");
        String claveU = sc.nextLine();
        Usuario uNuevo = new Usuario(nombresU, apellidosU, organizacionU, correoU, Utilitaria.claveHash(claveU), TipoUsuario.VENDEDOR);
        usuarios.add(uNuevo);
        Usuario.guardarArchivoUsuarios("usuarios.ser", usuarios);
        System.out.println("Vendedor registrado con éxito");
        return -1;
    }
//    
//    public static void ingresarNuevoVehiculo(ArrayList<Vehiculo> vehiculos, Scanner sc){
//        int numT;
//        Vehiculo v;
//        do{
//            System.out.println("\n--- TIPO DE VEHICULOS DISPONIBLES PARA REGISTRAR ---");
//            System.out.println("1. Motocicleta");
//            System.out.println("2. Auto");
//            System.out.println("3. Camioneta");
//            System.out.println("Ingrese el número del tipo de vehículo que desea registrar: ");
//            numT = sc.nextInt();
//            sc.nextLine();
//            switch(numT){
//                case 1:
//                    v = Vehiculo.pedirDatosVehiculo(vehiculos, sc);
//                    vehiculos.add(v);
//                    Vehiculo.guardarArchivoVehiculos("vehiculos.ser", vehiculos);
//                    break;
//                case 2:
//                    v = Auto.pedirDatosAuto(vehiculos, sc);
//                    vehiculos.add(v);
//                    Vehiculo.guardarArchivoVehiculos("vehiculos.ser", vehiculos);
//                    break;
//                case 3:
//                    v = Camioneta.pedirDatosCamioneta(vehiculos, sc);
//                    vehiculos.add(v);
//                    Vehiculo.guardarArchivoVehiculos("vehiculos.ser", vehiculos);
//            }
//        }while(numT < 1 || numT > 3);
//        System.out.println("Vehículo registrado con éxito");
//    }
    
    public static void revisarOfertas(ArrayList<Usuario> usuarios, ArrayList<Oferta> ofertas, ArrayList<Vehiculo> vehiculos, Scanner sc) {
        System.out.println("Ingrese la placa del vehículo deseado para consultar qué ofertas se han realizado: ");
        String placa = sc.nextLine();
        System.out.println("");
        Vehiculo vehiculo = null;
        for (Vehiculo v : vehiculos) {
            if (placa.equals(v.placa)) 
                vehiculo = v;
        }
        if(!(vehiculo == null))
            vehiculo.mostrarOfertas(usuarios, ofertas, vehiculos, sc);
        else
            System.out.println("El vehículo con dichas placas no se encuentra registrado");
    }

    public static void aceptarOferta(ArrayList<Usuario> usuarios, ArrayList<Oferta> ofertas, ArrayList<Vehiculo> vehiculos, Oferta oferta) {
        Vehiculo vehiculo = oferta.getVehiculo();
        Buzon b = new Buzon();
        b.enviarCorreo(oferta);
        oferta.eliminarOfertas(usuarios, ofertas);
        Oferta.guardarArchivoOfertas("ofertas.ser", ofertas);
        vehiculo.eliminarVehiculo(vehiculos);
        Vehiculo.guardarArchivoVehiculos("vehiculos.ser", vehiculos);
    }
}
