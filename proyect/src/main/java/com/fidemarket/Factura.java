package com.fidemarket;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Herencia
public class Factura extends Producto {
    private int cantidad;
    private double total;

    public Factura(int id, String nombre, int precio, String categoria, int cantidad) {
        super(id, nombre, precio, categoria);
        this.cantidad = cantidad;
        this.total = precio * cantidad; // Establecemos el total en base a la cantidad inicial
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setter para cantidad, actualiza la cantidad y recalcula el total
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.total = getPrecio() * cantidad; // Recalculamos el total basado en la cantidad nueva
    }

    public double getTotal() {
        return total;
    }

    // Método para obtener el detalle de la factura en formato legible
    public String obtenerFacturaDetalle() {
        return
                "ID: " + getId() + "\n" +
                "Producto: " + getNombre() + "\n" +
                "Categoría: " + getCategoria() + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "Precio Unitario: $" + getPrecio() + "\n" +
                "Total: $" + total + "\n";
    }

    // Método estático para calcular el total de la factura
    public static int calcularTotalFactura(List<Factura> productosFacturados) {
        int totalFactura = 0;
        for (Factura factura : productosFacturados) {
            totalFactura += factura.getTotal(); // Sumamos los totales de cada producto
        }
        return totalFactura;
    }

    // Método estático para mostrar todas las facturas utilizando JFrame
    public static void mostrarFactura(List<Factura> productosFacturados) {
        String cedula = Login.cedulaUsuario;
        StringBuilder facturaResumen = new StringBuilder();

        // Mostrar los detalles de cada producto facturado
        for (Factura factura : productosFacturados) {
            facturaResumen.append(factura.obtenerFacturaDetalle()).append("\n");
        }

        // Calcular el total general
        int totalFactura = calcularTotalFactura(productosFacturados);

        // Agregar el total 
        facturaResumen.append("Total pagado: $" + totalFactura);

        // Crear una ventana JFrame para mostrar la factura
        JFrame frame = new JFrame("Factura de (" + cedula + ")");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crear un panel para mostrar el contenido de la factura
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

       
        JTextArea textArea = new JTextArea(facturaResumen.toString());
        textArea.setEditable(false); 
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón para cerrar la ventana
        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> frame.dispose());
        panel.add(closeButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
