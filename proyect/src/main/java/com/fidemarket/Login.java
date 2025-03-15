package com.fidemarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    
    // Variable estática para almacenar la cédula
    public static String cedulaUsuario;

    public Login() {
        JFrame frame = new JFrame("Iniciar Sesión");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Centrando la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        JLabel cedulaLabel = new JLabel("Cédula:");
        cedulaLabel.setBounds(20, 20, 80, 25);
        frame.add(cedulaLabel);

        JTextField cedulaField = new JTextField();
        cedulaField.setBounds(100, 20, 165, 25);
        frame.add(cedulaField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(20, 60, 80, 25);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 165, 25);
        frame.add(passwordField);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(100, 100, 120, 25);
        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaField.getText();  // Obtener la cédula del campo
                String password = new String(passwordField.getPassword());

                if (Usuario.verificarLogin(cedula, password)) {
                    JOptionPane.showMessageDialog(frame, "Bienvenido, " + cedula);
                    frame.dispose();  // Cerrar la ventana de login
                    Producto.mostrarProductos();

                    // Almacenar la cédula en la variable estática
                    cedulaUsuario = cedula;
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.");
                }
            }
        });

        frame.setVisible(true);
    }
}
