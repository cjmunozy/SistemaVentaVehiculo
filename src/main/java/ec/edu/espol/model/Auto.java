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

    public Auto(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String transmision) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, tipoVehiculo);
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
    
    public static Auto pedirDatosAuto(ArrayList<Vehiculo> vehiculos, Scanner sc){
        Vehiculo v = Vehiculo.pedirDatosVehiculo(vehiculos, sc);
        System.out.println("Ingrese el número de vidrios: ");
        int vidriosU = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el tipo de transmisión: ");
        String transmisionU = sc.nextLine();
        return new Auto(v.placa, v.marca, v.modelo, v.tipoMotor, v.año, v.recorrido, v.color, v.tipoCombustible, v.precio, TipoVehiculo.AUTO, vidriosU, transmisionU);
    }  
    
    public static Auto pedirDatosAuto(ArrayList<Vehiculo> vehiculos, String placa, String marca, String modelo, String motor, int año, double recorrido, String color, String combustible, double precio, int vidrios, String trasmision){
        Vehiculo v = Vehiculo.pedirDatosVehiculo(vehiculos, placa, marca, modelo, motor, año, recorrido, color, combustible, precio);
        return new Auto(v.placa, v.marca, v.modelo, v.tipoMotor, v.año, v.recorrido, v.color, v.tipoCombustible, v.precio, TipoVehiculo.AUTO, vidrios, trasmision);
    }  
}
