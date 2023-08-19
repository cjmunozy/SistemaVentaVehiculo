module ec.edu.espol.sistemaventavehiculo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens ec.edu.espol.sistemaventavehiculo to javafx.fxml;
    exports ec.edu.espol.sistemaventavehiculo;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
}
