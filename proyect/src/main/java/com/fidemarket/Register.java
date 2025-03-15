package com.fidemarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    public Register() {
        JFrame frame = new JFrame("Registro de Usuario");
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

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(100, 100, 100, 25);
        frame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaField.getText();
                String password = new String(passwordField.getPassword());

                if (!Usuario.existeUsuario(cedula)) {
                    Usuario.registrarUsuario(new Usuario(cedula, password));
                    JOptionPane.showMessageDialog(frame, "Usuario registrado con éxito.");
                    // Cerrar la ventana de registro y abrir el login
                    frame.dispose();
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(frame, "El usuario ya está registrado.");
                }
            }
        });

        frame.setVisible(true);
    }

}
