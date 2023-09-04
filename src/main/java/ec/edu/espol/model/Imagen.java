/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author crisj
 */
public class Imagen implements Serializable{
    private Vehiculo vehiculo;
    private String placa;
    private String ruta;
    private static final long serialVersionUID = 8799656478674716638L;

    public Imagen(Vehiculo vehiculo, String ruta) {
        this(vehiculo.getPlaca(), ruta);
        this.vehiculo = vehiculo;
    }

    public Imagen(String placa, String ruta) {
        this.placa = placa;
        this.ruta = ruta;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getRuta() {
        return ruta;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Imagen{");
        sb.append("vehiculo=").append(vehiculo);
        sb.append(", placa=").append(placa);
        sb.append(", ruta=").append(ruta);
        sb.append('}');
        return sb.toString();
    }
    
    public static void guardarArchivoImagenes(String nomFile, ArrayList<Imagen> imagenes){
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(nomFile))){
           ous.writeObject(imagenes);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static ArrayList<Imagen> cargarImagenes(String nomFile){
        ArrayList<Imagen> imagenes = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFile))){
            imagenes = (ArrayList<Imagen>) ois.readObject();
        }
        catch(ClassNotFoundException ex1){
            System.out.println(ex1.getMessage());
        }catch(IOException ex2){
            System.out.println(ex2.getMessage());
        }
        return imagenes;
    }
}
