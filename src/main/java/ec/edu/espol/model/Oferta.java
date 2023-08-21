/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author crisj
 */
public class Oferta implements Serializable{
    private String placa;
    private Vehiculo vehiculo;
    private String correo;
    private Usuario usuario;
    private double precio;
    private static final long serialVersionUID = 8799656478674716638L;

    public Oferta(Vehiculo vehiculo, Usuario usuario, double precio) {
        this(vehiculo.getPlaca(), usuario.getCorreo(), precio);
        this.vehiculo = vehiculo;
        this.usuario = usuario;
    }

    public Oferta(String placa, String correo, double precio) {
        this.placa = placa;
        this.correo = correo;
        this.precio = precio;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo tipoVehiculo) {
        this.vehiculo = tipoVehiculo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
        Oferta other = (Oferta) o;
        return Objects.equals(this.placa, other.placa) && Objects.equals(this.correo, other.correo);
    }
    
    public static void guardarArchivoOfertas(String nomFile, ArrayList<Oferta> ofertas){
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(nomFile))){
           ous.writeObject(ofertas);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<Oferta> cargarOfertas(String nomFile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFile))){
            ofertas = (ArrayList<Oferta>) ois.readObject();
        }
        catch(ClassNotFoundException ex1){
            System.out.println(ex1.getMessage());
        }catch(IOException ex2){
            System.out.println(ex2.getMessage());
        }
        return ofertas;
    }

    public static ArrayList<Oferta> readFile(String nomFile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                Oferta o;
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                o = new Oferta(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
                ofertas.add(o);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return ofertas;
    }
        
    public void saveFile(String nomFile, boolean append){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), append)))
        {
           pw.println(this.placa+"|"+this.correo+"|"+this.precio);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveFile(ArrayList<Oferta> ofertas, String nomFile){
        for (int i = 0; i < ofertas.size(); i++){
            if(i == 0)
                ofertas.get(i).saveFile(nomFile, false);
            else
                ofertas.get(i).saveFile(nomFile, true);
        }
    }
    
    public void eliminarOfertas(ArrayList<Usuario> usuarios, ArrayList<Oferta> ofertas) {
        if(ofertas.contains(this)){
            ofertas.remove(this);
            for(int i = 0; i < ofertas.size(); i++){
                if(ofertas.get(i).getPlaca().equals(this.placa))
                    ofertas.remove(i);
            }
        }
        for(Usuario u : usuarios){
                if(u.getOfertas().contains(this))
                    u.getOfertas().remove(this);
            }
        }
    }

