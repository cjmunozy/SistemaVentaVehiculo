/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.modeloventavehiculo;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author crisj
 */
public class Buzon {
    private final String smtpsHost = "smtp.gmail.com";
    private Properties props = System.getProperties();
    private Authenticator auth = new Autenticador();
    private Session session;
    private final String correo = ((Autenticador) this.auth).getCorreo();

    public Buzon() {
        props.put("mail.smtps.host", this.smtpsHost);
        props.put("mail.from", this.correo);
        props.put("mail.smtps.ssl", "true");
        props.put("mail.smtps.port", "465");
        props.put("mail.smtps.user", this.correo);
        props.put("mail.smtps.clave", ((Autenticador) this.auth).getClave());
        props.put("mail.smtps.auth", "true");
        session = Session.getDefaultInstance(props, auth);
    }
    
    public void enviarCorreo(Oferta o){
        Usuario u = o.getUsuario();
        Vehiculo v = o.getVehiculo();
        Message message = new MimeMessage(this.session);
        try {
            message.setFrom(new InternetAddress(this.correo));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(u.getCorreo()));
            message.setSubject("Oferta Aceptada");
            message.setText("¡Felicidades!\nSu oferta de $" + o.getPrecio() + " por el vehículo " + v.getMarca() + " " + v.getModelo() + " fue aceptada.\nPuede acercarse a la agencia para finalizar el proceso de adquisición.");
            Transport transport = this.session.getTransport("smtps");
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
