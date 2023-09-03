/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author crisj
 */
public class Camioneta extends Auto{
    private String traccion;
    private static final long serialVersionUID = 8799656478674716638L;

    public Camioneta(String placa, Usuario dueño, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String transmision, String traccion) {
        super(placa, dueño, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, tipoVehiculo, vidrios, transmision);
        this.traccion = traccion;
    }
    
    public Camioneta(String placa, String correo, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String transmision, String traccion) {
        super(placa, correo, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, tipoVehiculo, vidrios, transmision);
        this.traccion = traccion;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", traccion=").append(traccion);
        return sb.toString();
    }
    
    public static Camioneta pedirDatosCamioneta(ArrayList<Vehiculo> vehiculos, Usuario usuario, Scanner sc){
        Auto a = Auto.pedirDatosAuto(vehiculos, usuario, sc);
        System.out.println("Ingrese la tracción: ");
        String traccionU = sc.nextLine();
        return new Camioneta(a.placa, a.dueño, a.marca, a.modelo, a.tipoMotor, a.año, a.recorrido, a.color, a.tipoCombustible, a.precio, TipoVehiculo.CAMIONETA, a.getVidrios(), a.getTransmision(), traccionU);
    }  

    public static Camioneta pedirDatosCamioneta(ArrayList<Vehiculo> vehiculos, String placa, Usuario dueño, String marca, String modelo, String motor, int año, double recorrido, String color, String combustible, double precio, int vidrios, String trasmision, String traccion){
        Auto a = Auto.pedirDatosAuto(vehiculos, placa, dueño, marca, modelo, motor, año, recorrido, color, combustible, precio, vidrios, trasmision);
        return new Camioneta(a.placa, a.dueño, a.marca, a.modelo, a.tipoMotor, a.año, a.recorrido, a.color, a.tipoCombustible, a.precio, TipoVehiculo.CAMIONETA, a.getVidrios(), a.getTransmision(), traccion);
    }  
}