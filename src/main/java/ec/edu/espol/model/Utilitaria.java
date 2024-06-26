/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author crisj
 */
public class Utilitaria {
    private static Map<String, String> imageMap = new HashMap<>();
    
    private Utilitaria(){
    }
    
    
    public static void relacionarImagen(String placa, String rutaImagen) {
        imageMap.put(placa, rutaImagen);
    }

    public static String obtenerRutaImagen(String placa) {
        return imageMap.get(placa);
    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64){
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    
    public static String claveHash(String clave){
        String hashClave = "";
        try{
            hashClave = Utilitaria.toHexString(getSHA(clave));
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return hashClave;
    }
    
    public static int MenuOpciones(Scanner sc){
        System.out.println("\n----- MENÚ DE OPCIONES -----");
        System.out.println("1. Vendedor");
        System.out.println("2. Comprador");
        System.out.println("3. Salir");
        System.out.println("Digite el número de la sección a la que quiere acceder: ");
        int numEleccion = sc.nextInt();
        return numEleccion;
    }
    
    public static int OpcionesVendedor(ArrayList<Usuario> usuarios, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, Scanner sc){
        Usuario u;
        int numEleccion;
        do{
            System.out.println("\n----- OPCIONES DEL VENDEDOR ----- \n");
            System.out.println("1. Registrar un nuevo vendedor");
            System.out.println("2. Registrar un nuevo vehículo");
            System.out.println("3. Aceptar oferta");
            System.out.println("4. Regresar");
            System.out.println("\n Ingrese el número de la opción que desea escoger: ");
            numEleccion = sc.nextInt();
            sc.nextLine();
            switch(numEleccion){
                case 1:
                    numEleccion = Usuario.registrarUsuario(usuarios, sc);
                    break;
                case 2:
                    u = Usuario.comprobarCyC(usuarios, sc);
                    if(u != null)
                        Usuario.ingresarNuevoVehiculo(vehiculos, u, sc);
                    numEleccion = -1;
                    break;
                case 3:
                    u = Usuario.comprobarCyC(usuarios, sc);
                    if(u != null)
                        Usuario.revisarOfertas(usuarios, ofertas, vehiculos, sc);
                    numEleccion = -1;
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción no encontrada.");
                    System.out.println("");
                    numEleccion = -1;
            }
        }while(numEleccion < 1 || numEleccion > 4);
        System.out.println("");
        return -1;
    }
    
    public static int OpcionesComprador(ArrayList<Usuario> usuarios, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, Scanner sc){
        int numEleccion;
        do{
            System.out.println("\n ----- OPCIONES DEL COMPRADOR ----- \n");
            System.out.println("1. Registrar un nuevo comprador");
            System.out.println("2. Ofertar por un vehículo");
            System.out.println("3. Regresar");
            System.out.println("\n Ingrese el número de la opción que desea escoger: ");
            numEleccion  = sc.nextInt();
            sc.nextLine();
            switch(numEleccion){
                case 1:
                    numEleccion = Usuario.registrarUsuario(usuarios, sc);
                    break;
                case 2:
                    Usuario u = Usuario.comprobarCyC(usuarios, sc);
                    if(!(u == null)){
                        Usuario.ofertarPorVehiculo(vehiculos, ofertas, u, sc);
                    }
                    numEleccion = -1;
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opción no encontrada.");
                    numEleccion = -1;
            }
        }while(numEleccion < 1 || numEleccion > 3);
        return -1;
    }
    
    public static boolean validarCorreo(ArrayList<Usuario> usuarios, String correo){
        for(Usuario u : usuarios){
            if(correo.equals(u.getCorreo())){
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        for(Vehiculo v : vehiculos){
            if(placa.equals(v.placa)){
                System.out.println("NO PUEDE REGISTRAR ESTE VEHICULO PORQUE LA PLACA YA EXISTE EN LA BASE DE DATOS");
                return false;
            }
        }
        return true;
    }
    
    public static boolean enRango(double valor, double minimo, double maximo){
        return valor >= minimo && valor <= maximo;
    }
    
    public static boolean enRango(int valor, int minimo, int maximo){
        return valor >= minimo && valor <= maximo;
    }
    
    public Vehiculo obtenerVehiculo(ArrayList<Vehiculo> vehiculos, String placa){
        Vehiculo vehiculo = null;
        for(Vehiculo v : vehiculos){
            if(v.getPlaca().equals(placa))
                vehiculo = v;
        }
        return vehiculo;
    }

    public static void relacionar(ArrayList<Usuario> usuarios, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas){
        for(Usuario u : usuarios){
                for(Oferta o : ofertas){
                    if(o.getCorreo().equals(u.getCorreo())){
                        o.setUsuario(u);
                        u.agregarOferta(o);
                    }
                }
                for(Vehiculo v : vehiculos){
                    if(v.getCorreo().equals(u.getCorreo())){
                        v.setDueño(u);
                        u.agregarVehiculo(v);
                    }
                }
            }
        for(Vehiculo v : vehiculos){
            for(Oferta o : ofertas){
                if(o.getPlaca().equals(v.getPlaca())){
                    o.setVehiculo(v);
                    v.agregarOferta(o);
                }
            }
        }
    }
}
