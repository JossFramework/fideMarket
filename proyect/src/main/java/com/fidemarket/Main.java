package com.fidemarket;
import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        // Crear una interfaz de registro o login
        String[] opciones = {"Registrar", "Iniciar sesión"};
        int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Bienvenido",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == 0) {
            // Registrar
            new Register();
        } else {
            // Iniciar sesión
            new Login();
        }
    }
}
