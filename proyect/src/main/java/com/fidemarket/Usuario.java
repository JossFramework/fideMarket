package com.fidemarket;
import java.util.ArrayList;




public class Usuario {
    private String cedula;
    private String password;

    // Constructor
    public Usuario(String cedula, String password) {
        this.cedula = cedula;
        this.password = password;
    }

    // Métodos getter
    public String getCedula() {
        return cedula;
    }

    public String getPassword() {
        return password;
    }



    // Lista para almacenar los usuarios registrados
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();



    // Método para verificar si el usuario ya está registrado
    public static boolean existeUsuario(String cedula) {
        for (Usuario u : listaUsuarios) {
            if (u.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }


    // Método para agregar un usuario a la lista
    public static void registrarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    
    // Método para verificar el login de un usuario
    public static boolean verificarLogin(String cedula, String password) {
        for (Usuario u : listaUsuarios) {
            if (u.getCedula().equals(cedula) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
