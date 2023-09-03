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
public class Auto extends Vehiculo {
    private int vidrios;
    private String transmision;
    private static final long serialVersionUID = 8799656478674716638L;

    public Auto(String placa, Usuario dueño, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String transmision) {
        super(placa, dueño, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, tipoVehiculo);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }
    
    public Auto(String placa, String correo, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String transmision) {
        super(placa, correo, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, tipoVehiculo);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }

    public int getVidrios() {
        return vidrios;
    }

    public void setVidrios(int vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", vidrios=").append(vidrios);
        sb.append(", transmision=").append(transmision);
        return sb.toString();
    }
    
    public static Auto pedirDatosAuto(ArrayList<Vehiculo> vehiculos, Usuario usuario, Scanner sc){
        Vehiculo v = Vehiculo.pedirDatosVehiculo(vehiculos, usuario, sc);
        System.out.println("Ingrese el número de vidrios: ");
        int vidriosU = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el tipo de transmisión: ");
        String transmisionU = sc.nextLine();
        return new Auto(v.placa, v.dueño, v.marca, v.modelo, v.tipoMotor, v.año, v.recorrido, v.color, v.tipoCombustible, v.precio, TipoVehiculo.AUTO, vidriosU, transmisionU);
    }  
    
    public static Auto pedirDatosAuto(ArrayList<Vehiculo> vehiculos, String placa, Usuario dueño, String marca, String modelo, String motor, int año, double recorrido, String color, String combustible, double precio, int vidrios, String trasmision){
        Vehiculo v = Vehiculo.pedirDatosVehiculo(vehiculos, placa, dueño, marca, modelo, motor, año, recorrido, color, combustible, precio);
        return new Auto(v.placa, v.dueño, v.marca, v.modelo, v.tipoMotor, v.año, v.recorrido, v.color, v.tipoCombustible, v.precio, TipoVehiculo.AUTO, vidrios, trasmision);
    }  
}
