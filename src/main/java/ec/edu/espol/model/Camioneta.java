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

    public Camioneta(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String transmision, String traccion) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, tipoVehiculo, vidrios, transmision);
        this.traccion = traccion;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public static Camioneta pedirDatosCamioneta(ArrayList<Vehiculo> vehiculos, String placa, String marca, String modelo, String motor, int año, double recorrido, String color, String combustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String trasmision, String traccion){
        Auto a = Auto.pedirDatosAuto(vehiculos,placa, marca, modelo, motor, año, recorrido, color, combustible, precio, tipoVehiculo, vidrios, trasmision);
        
        return new Camioneta(a.placa, a.marca, a.modelo, a.tipoMotor, a.año, a.recorrido, a.color, a.tipoCombustible, a.precio, TipoVehiculo.CAMIONETA, a.getVidrios(), a.getTransmision(), traccion);
    }  
}