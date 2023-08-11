/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.modeloventavehiculo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author crisj
 */
public class Autenticador extends Authenticator{
    private final String correo = "ventavehiculo2023@gmail.com";
    private final String clave = "zwhemwtincvklsyc";
    
    @Override
    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(this.correo, this.clave);
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }
}
